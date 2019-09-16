package com.honeywell.finger.mqtt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeywell.finger.mqtt.bean.MqttProcessor;
import com.honeywell.finger.mqtt.bean.MqttResponseMessage;
import com.honeywell.finger.mqtt.bean.MqttSendMessage;
import com.honeywell.finger.mqtt.configuration.MqttPublishGateway;
import com.honeywell.finger.mqtt.configuration.MqttPublishProperties;
import com.honeywell.finger.mqtt.service.MqttMessageService;
import com.honeywell.finger.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author: xiaomingCao
 * @date: 2019/4/4
 */
@Slf4j
@Component
public class MessageServiceImpl implements MqttMessageService {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MqttPublishGateway mqttPublishGateway;

  @Autowired
  private MqttPublishProperties mqttPublishProperties;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  @Qualifier("fingerExecutors")
  private ExecutorService executor;

  private static final String ALL = "+";

  @Override
  public void sendCmd(MqttResponseMessage mqttCmd) {

    Assert.notNull(mqttCmd, "cmd is null");

    Assert.hasText(mqttCmd.getMessageId(), "messageId missed");

    String body = null;
    try {
      body = objectMapper.writeValueAsString(mqttCmd);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }

    String topics = mqttPublishProperties.getTopic();

    if (!Objects.equals(mqttCmd.getTo(), ALL) && Objects.nonNull(mqttCmd.getTo())) {
      topics = topics.replace(ALL, mqttCmd.getTo());
    }

    mqttPublishGateway.send(
        MessageBuilder.withPayload(body)
            .setHeader(MqttHeaders.TOPIC, topics)
            .setHeader(MqttHeaders.QOS, mqttPublishProperties.getQos())
            .build());
  }

  @Override
  public void sendCmd(String clientId, String msg) {

    Assert.hasText(clientId, "clientId is missing");

    Assert.hasText(msg, "message is missing");

    String topics = mqttPublishProperties.getTopic();

    topics = topics.replace(ALL, clientId);

    mqttPublishGateway.send(
        MessageBuilder.withPayload(msg)
            .setHeader(MqttHeaders.TOPIC, topics)
            .setHeader(MqttHeaders.QOS, mqttPublishProperties.getQos())
            .build());
  }

  @Override
  public Long sendCmd(MqttSendMessage message) {
    String topic = mqttPublishProperties.getTopic();

    if (message.getTo() != null) {
      topic = topic.replace(ALL, message.getTo());
    } else {
      topic = topic.replace(ALL, MqttProcessor.SEND_TO_ALL_CLIENT_ID);
    }

    String body = JsonUtil.toJsonStr(message);

    log.info("MQTT body: {}", body);
    log.info("MQTT topic: {}", topic);
    log.info("MQTT QOS: {}", mqttPublishProperties.getQos());
    mqttPublishGateway.send(
        MessageBuilder.withPayload(body)
            .setHeader(MqttHeaders.TOPIC, topic)
            .setHeader(MqttHeaders.QOS, mqttPublishProperties.getQos())
            .build());

   // return commandHistoryService.create(message);
    return 1L;
  }

  /** 重发指令 */
  @Override
  public void resend(Long commandHistoryId) {
   /* CommandHistory commandHistory = commandHistoryService.get(commandHistoryId);
    String sendJson = commandHistory.getSendData();
    MqttSendMessage mqttSendMessage = JsonUtil.fromJsonStr(sendJson, MqttSendMessage.class);
    sendCmd(mqttSendMessage);*/
  }

  @Override
  public MqttResponseMessage sendCmdSync(MqttSendMessage message) {
    sendCmd(message);
    Future<MqttResponseMessage> response =
        executor.submit(
            () -> {
              long timeout = System.currentTimeMillis() + 1000 * 10;
              while (true) {
                if (System.currentTimeMillis() > timeout) {
                  return null;
                }
              /* Optional<MqttResponseMessage> op = responseCacheService.get(message.getMessageId());

                if (op.isPresent()) {
                  log.info(JsonUtil.toJsonStr(op.get()));
                  return op.get();
                }*/
              }
            });
    try {
      return response.get();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }
}
