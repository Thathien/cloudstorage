package com.udacity.jwdnd.course1.cloudstorage.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer usersId;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;
}
