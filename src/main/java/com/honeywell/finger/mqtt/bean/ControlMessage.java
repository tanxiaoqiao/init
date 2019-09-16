package com.honeywell.finger.mqtt.bean;

import lombok.Data;

@Data
public class ControlMessage {
 private String operator = "";
  private String picture = "";
  private String name = "";
  private String cardNo = "";
  private String type = "";
  private String time = "";
}
