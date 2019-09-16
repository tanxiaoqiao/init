package com.honeywell.finger.mqtt.constant;

/**
 * @author: xiaomingCao
 * @date: 2019/4/8
 */
public interface DeviceMessageResponseType {

  /** success */
  String SUCCESS = "0";

  /** 数据格式错误 */
  String GET_FORMAT_ERROR = "1";

  /** 人员不存在 */
  String PERSON_NOT_EXITS = "2";

  /** 获取人员特征值和圆形PNG图片失败 */
  String GET_PERSON_FEATURE_FAIL = "3";

  /** 设置设备参数异常 */
  String DEVICE_PARAMS_ERROR = "4";

  /** 获取设备信息和状态异常 */
  String DEVICE_INFO_AND_STATUS_ERROR = "5";

  /** 添加人员失败 */
  String ADD_PERSON_FAIL = "6";

  /** 激活继电器输出失败 */
  String SET_RELAY_OUTPUT_ERROR = "7";

  /** 触发韦根卡号失败 */
  String TRIGGER_WIEGAND_ERROR = "8";

  String CONNECT_SFTP_SUCCESS = "9";

  String SPACE_NOTE_ENOUGH = "10";

  String ROM_DIGEST_ERROR = "11";

  /** ROM文件下载 * */
  String ROM_UPGRADE_DOWNLOADING = "12";

  /** ROM文件下载成功 * */
  String ROM_UPGRADE_FINISH_DOWNLOAD = "13";

  /** 开始升级 * */
  String ROM_UPGRADE_START = "14";

  String SFTP_ERROR = "15";
}
