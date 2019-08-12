package com.example.androidbeginnerbasics.async;

import android.os.AsyncTask;

import com.example.androidbeginnerbasics.model.Notes;
import com.example.androidbeginnerbasics.persistence.NoteDao;


public class DeleteAsyncTask extends AsyncTask<Notes,Void,Void> {

    NoteDao noteDao;
    private static final String TAG="InsertAsyncTask";

    public DeleteAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Notes... notes) {

        noteDao.deleteNotes(notes);

        return null;
    }
}
