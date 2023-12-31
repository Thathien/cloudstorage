package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.common.CommonUltis;
import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServices;

@Controller
public class NotesController {

	@Autowired
	private NotesServices notesServices;

	@PostMapping("/saveNotes")
	public String saveNotes(Authentication authentication, Notes notesInput, Model model) {
		Users users = (Users) authentication.getPrincipal();
		String error = "";
		if (!CommonUltis.isValidString(notesInput.getNoteTitle())
				|| !CommonUltis.isValidString(notesInput.getNoteDescription())) {
			error = "Input notes invalid";
		}
		if (!error.isBlank()) {
			model.addAttribute("errorSaveFiles", error);
			return "result";
		}
		notesInput.setUserId(users.getUserId());
		Integer noteId = notesInput.getNoteId();
		int saveNotes = 0;
		if (noteId == null) {
			saveNotes = notesServices.addNote(notesInput);
		} else {
			if (notesServices.getNoteById(notesInput.getNoteId(), users.getUserId()) != null) {
				saveNotes = notesServices.editNoteById(notesInput);
			}
		}

		if (saveNotes == 0) {
			model.addAttribute("errorSaveFiles", "Save note error");
			return "result";
		}
		model.addAttribute("resultOK", "Save note successfully");
		return "result";
	}
	@GetMapping("/deleteNotes")
	public String deleteNote(@RequestParam("noteId")Integer noteId, Authentication authentication, Model model) {
		Users users = (Users) authentication.getPrincipal();
		if (noteId != null) {
			int results = notesServices.delNoteById(noteId, users.getUserId());
			if (results == 0) {
				model.addAttribute("errorSaveFiles", "Detele note error");
				return "result";
			}
			model.addAttribute("resultOK", "Delete file successfully");
			return "result";
		}
		return "error";
	}
}
