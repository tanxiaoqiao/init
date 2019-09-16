package com.honeywell.finger.mqtt.constant;

/**
 * @project: face
 * @name: MessageTypes
 * @author: H198180
 * @create: 2018-09-27 16:13
 * @description:
 */
public interface MessageTypes {

  String EVENT = "event";

  String CONTROL = "control";

  String HEARTBEAT = "heartbeat";

  String ACK_CONTROL = "ack_control";

  String ACK_EVENT = "ack_event";

  String RESPONSE = "response";

  String WILL = "will";
}
