package com.example.androidbeginnerbasics.async;

import android.os.AsyncTask;

import com.example.androidbeginnerbasics.model.Notes;
import com.example.androidbeginnerbasics.persistence.NoteDao;


public class UpdateAsyncTask extends AsyncTask<Notes,Void,Void> {

    NoteDao noteDao;
    private static final String TAG="InsertAsyncTask";

    public UpdateAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Notes... notes) {

        noteDao.updateNotes(notes);

        return null;
    }
}
