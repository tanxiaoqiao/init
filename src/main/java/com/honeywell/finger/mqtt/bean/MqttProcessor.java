package com.honeywell.finger.mqtt.bean;

/**
 * @author: xiaomingCao
 * @date: 2019/4/4
 */
public interface MqttProcessor {

  String SUB = "mqttSubscribeChannel";

  String PUB = "mqttPublishChannel";

  String SEND_TO_ALL_CLIENT_ID = "all";

  String SERVER = "server";

  String MESSAGE_VERSION_1_0 = "1.0";
}
