package com.example.androidbeginnerbasics.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.androidbeginnerbasics.async.DeleteAsyncTask;
import com.example.androidbeginnerbasics.async.InsertAsyncTask;
import com.example.androidbeginnerbasics.async.UpdateAsyncTask;
import com.example.androidbeginnerbasics.model.Notes;

import java.util.List;
import java.util.Observable;

public class NoteRepository {

    NoteDatabase noteDatabase;

    public NoteRepository(Context context) {

        noteDatabase=NoteDatabase.getInstance(context);

    }

     public void insertNoteTask(Notes notes)
     {
         new InsertAsyncTask(noteDatabase.getNoteDao()).execute(notes);
     }

     public void updateNoteTask(Notes notes)
     {

         new UpdateAsyncTask(noteDatabase.getNoteDao()).execute(notes);
     }

     public void deleteNoteTask(Notes notes)
     {
         new DeleteAsyncTask(noteDatabase.getNoteDao()).execute(notes);
     }

     public LiveData<List<Notes>> getNotesTask()
     {
         return noteDatabase.getNoteDao().getNotes();
     }

     public LiveData<List<Notes>> getNotestaskOnIDs(int id)
     {
         return noteDatabase.getNoteDao().getNotesCustomQueryTitle(id);
     }



}
