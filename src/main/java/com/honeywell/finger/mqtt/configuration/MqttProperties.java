package com.honeywell.finger.mqtt.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @project: face
 * @name: MqttProperties
 * @author: H198180
 * @create: 2018-09-25 11:24
 * @description:
 */
@Data
@ConfigurationProperties("mqtt")
public class MqttProperties {

  private String serviceName = "mqtt";

  private String[] url = new String[] {"tcp://localhost:1883"};

  private String username = "admin";

  private String password = "password";

  private boolean cleanSession = true;

  private int connectionTimeout = 30;

  private int keepAliveInterval = 60;
}
