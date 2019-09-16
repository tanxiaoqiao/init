package com.honeywell.finger.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Kris
 * @Date: 2019-09-04  10:47
 */
@Data
@Entity(name = "f_user")
@Table
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //对应设备pin
    private String pin;
    private String name;

    private String password;
    private String cardNo;
    //验证方式todo 去除只根据工作组确认
    private Integer verify = -1;
    //工作组
    private Integer grp = 1;

    //0 普通用户 2 登记员 6 管理员 10自定义 14超级管理员
    private Integer permission = 0;
    //工作组时间
    private String tx = "0001000100000000";
}
