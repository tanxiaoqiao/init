package com.honeywell.finger.mqtt.bean;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author: xiaomingCao
 * @date: 2019/4/8
 */
@Data
//@Entity
public class MqttResponseMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  private String source;

  private String to;

  private String messageId;

  private String version;

  private String type;

  private String cmdId;

  private String timestamp;

  LocalDateTime createTime;

  private int status = 0;

  private String description;

  private Map<String, Object> data;

  private String reserve;
}
