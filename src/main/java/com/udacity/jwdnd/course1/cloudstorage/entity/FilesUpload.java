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
public class FilesUpload {
    private Integer filesId;
    private String filesName;
    private String contentType;
    private String filesSize;
    private Integer userId;
    private byte[] fileData;
}
