package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersServices;

@Controller
public class HomeController {
	@Autowired
	private UsersServices usersServices;

	@GetMapping(value = "/home")
	public ModelAndView index(Authentication authentication, Model model) {
		String username = (String) authentication.getPrincipal();
		Users users = usersServices.getByUsername(username);
		String a = "xxx";
//        Users loginUser = (Users) authentication.getPrincipal();
//        Integer usersId = loginUser.getUsersId();
		return new ModelAndView("home");
	}
}
