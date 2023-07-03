package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.entity.FilesUpload;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesUploadServices;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersServices;

@RequestMapping("/fileManagement")
@Controller
public class FilesUploadController {

	@Autowired
	private FilesUploadServices filesUploadServices;
	
	@Autowired
	private UsersServices usersServices;

	@PostMapping("/saveFile")
	public String saveFile(Authentication authentication, MultipartFile fileUpload, RedirectAttributes redirectAttributes) throws IOException {
		String username = (String) authentication.getPrincipal();
		Users users = usersServices.getByUsername(username);
		String errorFilesUpload = "";
		
		if (fileUpload.isEmpty()) {
			errorFilesUpload = "File upload is empty";
		}
		
		FilesUpload filesExits = filesUploadServices.findOneFile(fileUpload, users.getUserId());
		if (filesExits!=null){
			errorFilesUpload = " File has been exits.";
		}
		
		if(!errorFilesUpload.isBlank()) {
			redirectAttributes.addAttribute("errorSaveFiles", errorFilesUpload);
			return "redirect:/result?error";
		}
		
		int saveFile  = filesUploadServices.addFiles(fileUpload,users.getUserId());
		if (saveFile == 0) {
			redirectAttributes.addFlashAttribute("errorSaveFiles", "Upload failed, Upload file again");
			return "redirect:/results?error";
		}
		return "redirect:/result?success";
	}
}
