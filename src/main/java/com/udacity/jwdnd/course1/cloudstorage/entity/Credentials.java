package com.udacity.jwdnd.course1.cloudstorage.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
	private Integer credentialsId;
	private String url;
	private String username;
	@JsonIgnore
	private String key;
	private String password;
	private Integer usersId;
}
