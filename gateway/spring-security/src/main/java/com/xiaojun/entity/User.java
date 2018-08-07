package com.xiaojun.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 实体类User
 *
 * @author long.luo
 * @date 2018-07-31
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
