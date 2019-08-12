package com.example.androidbeginnerbasics;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidbeginnerbasics.Util.Utility;
import com.example.androidbeginnerbasics.model.Notes;
import com.example.androidbeginnerbasics.persistence.NoteRepository;

public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener, TextWatcher {

    private static final int EDIT_MODE_ENABLED=1;
    private static final int EDIT_MODE_DISABLED=0;
    //ui component
    EditText mnoteEditext, mtoolbaret;
    TextView mtoolbartv;
    RelativeLayout mrCheck,mrBack;
    ImageButton mbCheck,mbBack;

    //vars
    private boolean mIsnote;
    Notes notes;
    Notes finalnotes;
    private int mMode;

    private GestureDetector gestureDetector;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mnoteEditext=findViewById(R.id.note_editext);
        mtoolbaret=findViewById(R.id.note_et_text_title);
        mtoolbartv=findViewById(R.id.note_text_title);
        mrCheck=findViewById(R.id.check_container);
        mrBack=findViewById(R.id.back_arrow_container);
        mbCheck=findViewById(R.id.toolbar_check);
        mbBack=findViewById(R.id.toolbar_back_arrow);

        mNoteRepository=new NoteRepository(this);

        if(getnotestatus())
        {
            setNewnoteproperties();
            editModeEnabled();
        }
        else
        {
            setnoteproperties();

            editModeDisabled();
        }
        setLisner();
    }

    private void editModeEnabled()
    {
        mrBack.setVisibility(View.GONE);
        mrCheck.setVisibility(View.VISIBLE);
        mtoolbartv.setVisibility(View.GONE);
        mtoolbaret.setVisibility(View.VISIBLE);

        mMode=EDIT_MODE_ENABLED;
    }

    private void editModeDisabled()
    {
        mrBack.setVisibility(View.VISIBLE);
        mrCheck.setVisibility(View.GONE);
        mtoolbartv.setVisibility(View.VISIBLE);
        mtoolbaret.setVisibility(View.GONE);

        mMode=EDIT_MODE_DISABLED;

        String temp=mtoolbaret.getText().toString().trim();
        temp=temp.replace("\n","");
        temp=temp.replace(" ","");

        if(temp.length()>0)
        {
            finalnotes.setTitle(mtoolbaret.getText().toString());
            finalnotes.setContent(mnoteEditext.getText().toString());
            String timestamp= Utility.getCurrentTimeStamp();
            finalnotes.setTimestamp(timestamp);

            Log.d("Salman","Title:"+finalnotes.getTitle()+","+(notes.getTitle()) );
            Log.d("Salman","Content:"+finalnotes.getContent()+","+(notes.getContent()) );

            if(!finalnotes.getTitle().equals(notes.getTitle()) || !finalnotes.getContent().equals(notes.getContent()))
            {
                saveChanges();
            }
        }


    }

    private void setLisner()
    {
        mnoteEditext.setOnTouchListener(this);
        gestureDetector=new GestureDetector(this,this);
        mtoolbartv.setOnClickListener(this);
        mbCheck.setOnClickListener(this);
        mbBack.setOnClickListener(this);
        mtoolbaret.addTextChangedListener(this);
    }

    private boolean getnotestatus()
    {
        if (getIntent().hasExtra("selected_note")) {
            notes=getIntent().getParcelableExtra("selected_note");
          //  finalnotes=getIntent().getParcelableExtra("selected_note");
            finalnotes=new Notes();
            finalnotes.setTimestamp(notes.getTimestamp());
            finalnotes.setContent(notes.getContent());
            finalnotes.setTitle(notes.getTitle());
            finalnotes.setId(notes.getId());
            mIsnote=false;
            Log.d("Salman",""+notes.getTitle());
            return false;
        }

        Log.d("Salman","Doest have a Note");
        mIsnote =true;
        return true;
    }

    private void saveChanges()
    {
        if(mIsnote)
        {
         saveNewNote();
        }
        else
        {
            updateNote();
        }

    }

    private void updateNote()
    {
        mNoteRepository.updateNoteTask(finalnotes);
    }

    private void saveNewNote()
    {
        mNoteRepository.insertNoteTask(finalnotes);
    }

    private void setNewnoteproperties()
    {
        mtoolbaret.setText("Note Title");
        mtoolbartv.setText("Note Title");

        notes =new Notes();
        finalnotes =new Notes();

        notes.setTitle("Note Title");
        finalnotes.setTitle("Note Title");
        notes.setContent("");
        finalnotes.setContent("");
    }

    private void setnoteproperties()
    {
        Log.d("Salman","note: "+notes.getTitle());
        Log.d("Salman","note content: "+notes.getContent());
        mtoolbaret.setText(notes.getTitle());
        mtoolbartv.setText(notes.getTitle());
        mnoteEditext.setText(notes.getContent());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d("Salman","On double tap");
        editModeEnabled();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.toolbar_check: {
                editModeDisabled();
                break;
            }

            case R.id.note_text_title: {
                editModeEnabled();
                mtoolbaret.requestFocus();
                mtoolbaret.setSelection(mtoolbaret.length());
                break;
            }

            case R.id.toolbar_back_arrow:{

                finish();
                break;
            }

        }
    }

    @Override
    public void onBackPressed() {

        if(mMode==EDIT_MODE_ENABLED)
        {
            onClick(mbCheck);
        }
        else
        super.onBackPressed();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mode",mMode);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMode=savedInstanceState.getInt("mode");

        if(mMode==EDIT_MODE_ENABLED)
        {
            editModeEnabled();
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        mtoolbartv.setText(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
