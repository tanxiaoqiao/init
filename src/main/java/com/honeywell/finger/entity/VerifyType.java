package com.honeywell.finger.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Kris
 * @Date: 2019-09-16  10:09
 */
@Data
@Entity(name = "f_verify_type")
@Table
public class VerifyType {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //验证方式 默认组验证 也可以是密码
            /*
            | 0 | 指静脉或人脸或指纹或卡或密码 (自动识别)|
            | 1 | 仅指纹 |
            | 2 | 工号验证 |
            | 3 | 仅密码 |
            | 4 | 仅卡 |
            |5| 指纹或密码 |
            | 6 | 指纹或卡 |
            | 7 | 卡或密码 |
            |8| 工号加指纹 |
            |9| 指纹加密码 |
            |10| 卡加指纹 |
            | 11 | 卡加密码 |
            | 12 | 指纹加密码加卡 |
            | 13 | 工号加指纹加密码 |
            |14| 工号加指纹或卡加指纹 |
            | 200 | 其他 |
            */
    private Integer verify = -1;
    //工作组
    private Integer grp = 0;
}
