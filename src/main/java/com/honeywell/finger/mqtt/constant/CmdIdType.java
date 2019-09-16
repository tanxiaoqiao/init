package com.honeywell.finger.mqtt.constant;

/**
 * @author: xiaomingCao
 * @date: 2019/4/8
 */
public interface CmdIdType {

  /** 添加人员 */
  String ADD = "1";

  /** 删除人员 */
  String DELETE = "2";

  /** 获取人脸特征值及圆形照片 */
  String GET = "3";

  /** 配置人脸设备参数 */
  String DEVICE_PARAMS = "4";

  /** 获取设备信息和状态 */
  String DEVICE_STATUS = "5";

  /** 主机授权 */
  String SERVER_AUTHORIZE = "6";

  /** ROM升级 */
  String ROM_UPGRADE = "7";

  /** 设置网络 */
  String NETWORK = "8";

  /** 批量人员更新 * */
  String PERSON_BATCH_UPDATE = "9";

  /** 全量人员更新 * */
  String PERSON_ALL_UPDATE = "10";

  String VERIFY_TEMPLATE_UPDATE = "11";
}
