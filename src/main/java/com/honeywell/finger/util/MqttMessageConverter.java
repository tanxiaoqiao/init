package com.honeywell.finger.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeywell.finger.mqtt.bean.MqttResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @project: face
 * @name: MqttMessageConverter
 * @author: H198180
 * @create: 2018-09-26 19:12
 * @description:
 */
@Slf4j
public class MqttMessageConverter extends DefaultPahoMessageConverter {

  private ObjectMapper objectMapper;

  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public MqttMessageConverter(int defaultQos, boolean defaultRetained, String charset) {
    super(defaultQos, defaultRetained, charset);
  }

  @Override
  protected Object mqttBytesToPayload(MqttMessage mqttMessage) throws Exception {
    log.info("Receive body: " + new String(mqttMessage.getPayload()));
    JsonNode jsonNode = objectMapper.readTree(mqttMessage.getPayload());
    JsonNode dataJSON = jsonNode.get("data");
    JsonNode timestamp = jsonNode.get("timestamp");
    if (Objects.isNull(timestamp)) {}

    Map<String, Object> data;
    if (Objects.isNull(dataJSON)) {
      data = Collections.emptyMap();
    } else {
      data = objectMapper.treeToValue(dataJSON, HashMap.class);
    }

    MqttResponseMessage message = objectMapper.treeToValue(jsonNode, MqttResponseMessage.class);
    message.setData(data);
    if (Objects.isNull(message.getTimestamp())) {
      message.setCreateTime(LocalDateTime.now());
    } else {
      long ts = Long.parseLong(message.getTimestamp());
      LocalDateTime localDateTime =
          LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.systemDefault());
      message.setCreateTime(localDateTime);
    }
    return message;
  }
}
