package com.honeywell.finger.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @Author: Kris
 * @Date: 2019-09-02  14:25
 */
@Data
@Entity
@Table(name = "f_operate_log")
public class OperateLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    //工号
    private String code;

    private String time;

    //操作类型
    private Integer OpType;


}
