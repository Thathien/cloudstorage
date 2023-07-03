package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.common.CommonUltis;
import com.udacity.jwdnd.course1.cloudstorage.entity.FilesUpload;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesUploadMapper;

@Service
public class FilesUploadServices {

	@Autowired
	FilesUploadMapper filesSUploadMapper;

	List<FilesUpload> findAllFiles(Integer userId) {
		return filesSUploadMapper.findAllFiles(userId);
	}

	public int addFiles(MultipartFile file, Integer userId) throws IOException {
		String filesName = CommonUltis.getURLFiles(file);
		String contentType = file.getContentType();
		String filesSize = String.valueOf(file.getSize());
		byte[] fileData = file.getBytes();
		return filesSUploadMapper.addFiles(filesName, contentType, filesSize, userId, fileData);
	}

	public int deleteFileById(Integer fileId, Integer userId) {
		return filesSUploadMapper.deleteById(fileId, userId);
	}

	public FilesUpload findOneFile(MultipartFile files, Integer userId) {
		return filesSUploadMapper.findOneFile(CommonUltis.getURLFiles(files), userId);
	}

}
