package com.honeywell.finger.mqtt.service;


import com.honeywell.finger.mqtt.bean.MqttResponseMessage;
import com.honeywell.finger.mqtt.bean.MqttSendMessage;

/**
 * @author: xiaomingCao
 * @date: 2019/4/4
 */
public interface MqttMessageService {

  /**
   * 下发mqtt命令
   *
   * @param mqttResponseMessage
   */
  void sendCmd(MqttResponseMessage mqttResponseMessage);

  /**
   * 下发mqtt命令
   *
   * @param clientId
   * @param msg
   */
  void sendCmd(String clientId, String msg);

  /**
   * 下发mqtt命令
   *
   * @param message
   */
  Long sendCmd(MqttSendMessage message);

  /**
   * 重发指令
   *
   * @param commandHistoryId
   */
  void resend(Long commandHistoryId);

  /**
   * 同步发送
   *
   * @param message
   * @return
   */
  MqttResponseMessage sendCmdSync(MqttSendMessage message);
}
