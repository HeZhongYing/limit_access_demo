package com.hezy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hezhongying
 * @create 2024/9/29 10:29
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;
}
