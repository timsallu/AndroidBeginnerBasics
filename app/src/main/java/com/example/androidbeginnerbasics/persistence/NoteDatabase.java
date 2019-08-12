package com.example.androidbeginnerbasics.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.androidbeginnerbasics.model.Notes;


/*
  How Create Room Database
  1.Create Model class with Entity Annotation(@Entity (tableName = "notes")) its like one table refer Notes.java class

   eg:
   @PrimaryKey (autoGenerate = true) primary key it aut increments
    private int id;

    @NonNull if u dont want to keep it null same like TEXT NOT NULL

    @ColumnInfo(name = "title")  have to put this for all members of class
     private  String title;

   2.Create Dao interface under persistence package
    here write all the quries like insert delete update refer NoteDao.java
     put @Dao annotation top of class
     @Dao
    public interface NoteDao

    3. finally create data base and shemas in database class should extend RoomDatabase
     look down

    4.if u mant back up of ur database when u incremnet db versions ,u can create shemas
     just have to  include this code in gradle

     javaCompileOptions {
            annotationProcessorOptions {
                arguments = ["room.schemaLocation":
                                     "$projectDir/schemas".toString()]
            }
        }



* */

@Database(entities = {Notes.class},version = 1) // entities with all table name and version is database version
public abstract class NoteDatabase extends RoomDatabase {

    public static final  String Database_Name="notes_db";//db name

    private static NoteDatabase instance; //singleton instance for our DB

    static NoteDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(), //room builder same like getwritabledatabase
                    NoteDatabase.class,
                    Database_Name).build();
        }
        return instance;
    }

    public abstract NoteDao getNoteDao();

}
