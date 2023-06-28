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
public class Notes {
	private Integer notesId;
	private String notesTitle;
	private String notesDescription;
	private Integer userId;

	public Notes(String notesTitle, String notesDescription, Integer userId) {
		super();
		this.notesTitle = notesTitle;
		this.notesDescription = notesDescription;
		this.userId = userId;
	}

}
