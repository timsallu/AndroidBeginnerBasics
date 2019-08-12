package com.example.androidbeginnerbasics;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.androidbeginnerbasics.Util.VerticalSpacingItemDecorator;
import com.example.androidbeginnerbasics.adapters.NoteRecycleAdapters;
import com.example.androidbeginnerbasics.model.Notes;
import com.example.androidbeginnerbasics.persistence.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity implements
        NoteRecycleAdapters.onNoteListener,
        NoteRecycleAdapters.OnItemLongclickListener, View.OnClickListener {


    //Ui components
    private RecyclerView mRecyclerView;


    //varx
    ArrayList<Notes> mNotesArrayList=new ArrayList<>();
    NoteRecycleAdapters noteRecycleAdapters;
    NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteRepository=new NoteRepository(this);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");
        mRecyclerView=findViewById(R.id.recycleview);
        initRecycleview();
       // insertFakenotes();
        retrieveNotes();
        //retriveNotesbasedOnIDs();

        findViewById(R.id.fab).setOnClickListener(this);
    }

    private void retrieveNotes()
    {

        mNoteRepository.getNotesTask().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(@Nullable List<Notes> notes) {

                if(mNotesArrayList.size()>0)
                {
                    mNotesArrayList.clear();
                }

                if(notes !=null)
                {
                    mNotesArrayList.addAll(notes);
                }

                noteRecycleAdapters.notifyDataSetChanged();
            }
        });

    }

    private void retriveNotesbasedOnIDs()
    {
        mNoteRepository.getNotestaskOnIDs(1).observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(@Nullable List<Notes> notes) {

                if(mNotesArrayList.size()>0)
                {
                    mNotesArrayList.clear();
                }

                if(notes!=null)
                {
                    mNotesArrayList.addAll(notes);
                }
                noteRecycleAdapters.notifyDataSetChanged();
            }
        });
    }

    private void insertFakenotes()
    {
        for(int i=0;i<1000;i++)
        {
            mNotesArrayList.add(new Notes("Title "+i,"Content abcd","Jan 201"+i));

        }
        noteRecycleAdapters.notifyDataSetChanged();
    }

    private void initRecycleview()
    {
        /*
        * 1.Decide LAyout manager
        * 2.set layout manager
        * 3.intialize recycle view adapeter
         *4.set adapter*/

        /*to set space right left top bottom to recycle items
        * extend item decoration class and add item decoration using addItemDecoration*/

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator verticalSpacingItemDecorator=new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(verticalSpacingItemDecorator);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(mRecyclerView);
        noteRecycleAdapters=new NoteRecycleAdapters(mNotesArrayList,this,this);
        mRecyclerView.setAdapter(noteRecycleAdapters);

    }

    @Override
    public void OnNoteClick(int pos) {

        Log.d("Salman",""+pos);

        Intent intent=new Intent(this,NoteActivity.class);
        intent.putExtra("selected_note",mNotesArrayList.get(pos));
        startActivity(intent);
    }

    @Override
    public void OnLongClick(int pos) {

        Log.d("Salman OnLongClick",""+pos);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,NoteActivity.class);
        startActivity(intent);
    }

    private void deleteNote(Notes notes)
    {
        mNotesArrayList.remove(notes);
        mNoteRepository.deleteNoteTask(notes);
        noteRecycleAdapters.notifyDataSetChanged();
    }
    private ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            deleteNote(mNotesArrayList.get(viewHolder.getAdapterPosition()));
        }
    };
}
