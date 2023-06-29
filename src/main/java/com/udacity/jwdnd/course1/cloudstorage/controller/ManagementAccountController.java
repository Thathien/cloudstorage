package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.common.CommonUltis;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersServices;

@Controller
public class ManagementAccountController {

	@Autowired
	private UsersServices usersServices;

	@GetMapping("/signup")
	public String getViewSignup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String doSignup(Users users, RedirectAttributes redirectAttributes, Model model) {
		String error = "";
		if (CommonUltis.isValidString(users.getFirstName()) && CommonUltis.isValidString(users.getLastName())
				&& CommonUltis.isValidString(users.getPassword()) && CommonUltis.isValidString(users.getUsername())) {
			error = usersServices.validationSignup(users.getUsername());

			if (error.isBlank()) {
				int results = usersServices.addUsers(users);
				if (results == 0) {
					error = "An error occurred, please try again";
				}
			}
		} else {
			error = "Please input valid First name, Last name, username, password";
		}

		if (!error.isBlank()) {
			model.addAttribute("errorSignup", error);
			return "signup";
		}
		redirectAttributes.addFlashAttribute("signupSuccess", true);
		return "redirect:/signup?success";
	}
}
