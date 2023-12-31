package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;

@Service
public class NotesServices {

	@Autowired
	private NotesMapper notesMapper;

	public List<Notes> getAllListNotes(Integer userId) {
		return notesMapper.getAllListNotes(userId);
	}

	public Notes getNoteById(Integer noteId, Integer userId) {
		return notesMapper.getNoteById(noteId, userId);
	}

	public int editNoteById(Notes note) {
		return notesMapper.editNoteById(note);
	}

	public int addNote(Notes note) {
		return notesMapper.addNote(note);
	}

	public int delNoteById(Integer noteId, Integer userId) {
		return notesMapper.delNoteById(noteId, userId);
	}
}
