package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entity.FilesUpload;
import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsServices;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesUploadServices;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServices;

@Controller
public class HomeController {

	@Autowired
	private NotesServices notesServices;

	@Autowired
	private CredentialsServices credentialsServices;

	@Autowired
	private FilesUploadServices filesUploadServices;

	@GetMapping(value = { "/home", "/" })
	public ModelAndView index(Authentication authentication, Model model) {
		Users users = (Users) authentication.getPrincipal();
		List<FilesUpload> filesUploads = filesUploadServices.findAllFiles(users.getUserId());
		model.addAttribute("listFilesUploads", filesUploads);

		List<Notes> listNotes = notesServices.getAllListNotes(users.getUserId());
		model.addAttribute("listNotes", listNotes);

		List<Credentials> listCredentials = credentialsServices.getAllListCredentials(users.getUserId());
		model.addAttribute("listCredentials", listCredentials);

		return new ModelAndView("home");
	}
}
