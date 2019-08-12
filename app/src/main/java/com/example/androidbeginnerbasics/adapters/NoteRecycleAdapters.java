package com.example.androidbeginnerbasics.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidbeginnerbasics.R;
import com.example.androidbeginnerbasics.Util.Utility;
import com.example.androidbeginnerbasics.model.Notes;

import java.util.ArrayList;

/*//Notes:
 *  1. first cretae java class
  * 2. create view holder class extend Recycleview,viewholder also add all xml views over here
  * 3. extend java class with recycle adapter with created view holder
  * 4. implement all the methods of recycle view adapter*/

public class NoteRecycleAdapters extends RecyclerView.Adapter<NoteRecycleAdapters.Viewholder>{

    private ArrayList<Notes> notesArrayList=new ArrayList<>();
    onNoteListener onNoteListener;
    OnItemLongclickListener onItemLongclickListener;

    public NoteRecycleAdapters(ArrayList<Notes> notesArrayList,onNoteListener onNoteListener,OnItemLongclickListener onItemLongclickListener) {
        Log.d("Salman","ghcitysirrts111");
        this.notesArrayList = notesArrayList;
        this.onNoteListener=onNoteListener;
        this.onItemLongclickListener=onItemLongclickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Log.d("Salman","ghcitysirrts");
       /* View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_list_item,viewGroup);


        return new Viewholder(view);*/

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_list_item, viewGroup, false);
        Viewholder holder = new Viewholder(view,onNoteListener,onItemLongclickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {

        String timestamp= Utility.getmonthinwords(notesArrayList.get(i).getTimestamp().substring(0,2)
                +" "+notesArrayList.get(i).getTimestamp().substring(3));

        viewholder.timestamp.setText(notesArrayList.get(i).getTimestamp());
        viewholder.title.setText(notesArrayList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView title,timestamp;
        onNoteListener onNoteListener;
        OnItemLongclickListener onItemLongclickListener;

        public Viewholder(@NonNull View itemView,onNoteListener onNoteClick,OnItemLongclickListener onItemLongclickListener) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            timestamp=itemView.findViewById(R.id.temestamp);
            this.onNoteListener=onNoteClick;
            this.onItemLongclickListener=onItemLongclickListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onNoteListener.OnNoteClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            onItemLongclickListener.OnLongClick(getAdapterPosition());
            return false;
        }
    }

    public interface onNoteListener
    {
        void OnNoteClick(int pos);
    }

    public interface OnItemLongclickListener
    {
        void OnLongClick(int pos);
    }
}
