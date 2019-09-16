package com.honeywell.finger.mqtt.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @project: face
 * @name: MqttSubscribeProperties
 * @author: H198180
 * @create: 2018-09-25 18:35
 * @description:
 */
@Data
@ConfigurationProperties("mqtt.subscribe")
public class MqttSubscribeProperties {

  private String clientId = "stream.client.mqtt.input";

  private String[] topics = new String[] {"device/data"};

  private int[] qos = new int[] {0};

  private boolean binary = false;

  private String charset = "UTF-8";
}
