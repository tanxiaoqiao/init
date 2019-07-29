package com.init.entity;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;

/**
 * @Author: Kris
 * @Date: 2019-07-29  15:17
 */
@Table
@Entity(name = "se_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
