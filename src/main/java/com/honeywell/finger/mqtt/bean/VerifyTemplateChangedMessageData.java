package com.honeywell.finger.mqtt.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class VerifyTemplateChangedMessageData {
  private Long verifyTemplateId;
  private Boolean isDefaultTemplate;
  List<VerifyMethodInfo> verifyTemplate;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class VerifyMethodInfo {
    private Long personGroupId;
    private String personGroupName;
    private List<Integer> verifyMethod;
  }
}
