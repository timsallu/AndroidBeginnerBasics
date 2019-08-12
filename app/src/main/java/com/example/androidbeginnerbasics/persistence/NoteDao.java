package com.example.androidbeginnerbasics.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.androidbeginnerbasics.model.Notes;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    long[] insertNotes(Notes... notes);

    @Query("Select * From notes")
    LiveData<List<Notes>> getNotes();

    @Query("Select * From notes where id =:id")
    List<Notes> getNotesCustomQuery(int id);

    @Query("Select * From notes where id =:id And title='dsfs'")
    LiveData<List<Notes>> getNotesCustomQueryTitle(int id);

    @Delete
    int deleteNotes(Notes... notes);

    @Update
    int updateNotes(Notes... notes);

}
