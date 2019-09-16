package com.honeywell.finger.mqtt.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @project: face
 * @name: MqttPublishProperties
 * @author: H198180
 * @create: 2018-09-25 11:59
 * @description:
 */
@Data
@ConfigurationProperties("mqtt.publish")
public class MqttPublishProperties {

  private String clientId = "stream.client.mqtt.output";

  private String topic = "face/HSAC-RDFR3000-MC-MANAGE/+/tx";

  private int qos = 0;

  private boolean retained = false;

  private String charset = "UTF-8";

  private boolean async = false;

  private int completionTimeout = 3000;
}
