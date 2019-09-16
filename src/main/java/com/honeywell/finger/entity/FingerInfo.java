package com.honeywell.finger.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Kris
 * @Date: 2019-09-04  10:58
 */
@Table
@Data
@Entity(name = "f_finger_info")
public class FingerInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //对应user的pin
    private String pin;
    private Integer size;
    //指纹
    private String tmp;

    //0 无效模版 1 正常模版
    private Integer valid = 1;
    //手指编号
    private Integer fid;
    //手指编号
    private Integer index;

}
