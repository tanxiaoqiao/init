package com.honeywell.finger.mqtt.configuration;

import com.honeywell.finger.mqtt.bean.MqttProcessor;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

/**
 * @project: face
 * @name: MqttPublishGateway
 * @author: H198180
 * @create: 2018-10-08 13:14
 * @description:
 */
@MessagingGateway(defaultRequestChannel = MqttProcessor.PUB)
public interface MqttPublishGateway {

  /**
   * send message
   *
   * @param message
   */
  void send(Message message);
}
