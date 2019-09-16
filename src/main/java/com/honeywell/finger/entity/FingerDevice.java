package com.honeywell.finger.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @Author: Kris
 * @Date: 2019-09-02  14:25
 */
@Data
@Entity
@Table(name = "f_device")
@NoArgsConstructor
@AllArgsConstructor
public class FingerDevice implements Serializable {
    private static final long serialVersionUID = 1L;


    // 客户端的序列号
    @Id
    private String serialNumber;

    //固件版本号
    private String version;
    //登记用户数
    private Integer empNum;
    //登记指纹数
    private Integer fingerNum;
    //考勤记录数
    private Long attendanceNum;
    //设备IP地址
    private String ipAddress;
    //指纹算法版本
    private String fingerVersion;
    //设备支持功能标示
    private Integer support;
    //设备组
    private Integer grp = 0;
    //todo 设备状态
/*    //设备状态 0启用 1禁用 3脱机
    private String status;
    //本机地址
    private String IPAddress;
     //最大用户数
    private String maxUserCount;

    //最大指纹数
    private String MaxFingerCount;
*/
}
