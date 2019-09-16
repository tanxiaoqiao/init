package com.honeywell.finger.mqtt.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WsBlackListEvent {
  private String name;
  private String personType;
  private String idCardNo;
  private String location;
  private Integer eventType;
  private Long happenTime;
  private Boolean inBlackList;
  private String irImg;
  private String rgbImg;
}
