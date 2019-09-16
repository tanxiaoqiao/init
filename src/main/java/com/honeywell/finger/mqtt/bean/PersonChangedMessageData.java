package com.honeywell.finger.mqtt.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonChangedMessageData {

  private String lastPersonVersion;
  private String currentPersonVersion;
  private List<UpdatedPerson> newPersons = new ArrayList<>();
  private List<UpdatedPerson> updatedPersons;
  private List<DeletedPerson> deletedPersons;
  private boolean rebase = false;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdatedPerson {
    private String serverPersonId;
    private String picture;
    private String name;
    private String cardNo;
    private Long activeDate;
    private Long expiredDate;
    private Long updateDate;
    private Long personGroupId;
    private String pin = "000000";
    private String type;
    private List<ImageFeature> features;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ImageFeature {
    private List<Float> feat;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeletedPerson {
    private String serverPersonId;
  }
}
