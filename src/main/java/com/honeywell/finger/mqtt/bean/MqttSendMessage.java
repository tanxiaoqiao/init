package com.honeywell.finger.mqtt.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: xiaomingCao
 * @date: 2019/4/4
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttSendMessage<T> {

  private String source;
  private String to;
  private String messageId;
  private String version;
  private String type;
  private String cmdId;
  private Long timestamp;
  private T data;
  private String reserve;
}
