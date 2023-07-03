package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.udacity.jwdnd.course1.cloudstorage.entity.FilesUpload;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsServices;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesUploadServices;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServices;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersServices;

@Controller
public class HomeController {
	@Autowired
	private UsersServices usersServices;
	
	@Autowired
	private NotesServices notesServices;
	
	@Autowired
	private CredentialsServices credentialsServices;
	
	@Autowired
	private FilesUploadServices filesUploadServices;

	@GetMapping(value = {"/home","/"})
	public ModelAndView index(Authentication authentication, Model model) {
		String username = (String) authentication.getPrincipal();
		Users users = usersServices.getByUsername(username);
		List<FilesUpload> filesUploads = filesUploadServices.findAllFiles(users.getUserId());
		model.addAttribute("listFilesUploads", filesUploads);

		return new ModelAndView("home");
	}
}
