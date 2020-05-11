package com.example.notesudemy;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Note> list;
    private ClickListenerAdapter clickListenerAdapter;

    public List<Note> getList() {
        return list;
    }

    public interface ClickListenerAdapter{
        void onClickListener(int position);
        void onLongClickListener(int position);
    }

    void setClickListenerAdapter(ClickListenerAdapter clickListenerAdapter) {
        this.clickListenerAdapter = clickListenerAdapter;
    }

    MyAdapter(List<Note> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = list.get(position);
        holder.header.setText( note.getHeader());
        holder.header.setBackgroundColor(note.getColorPriority());
        holder.desc.setText(note.getDescription());
        holder.dayOfWeek.setText( String.format("День недели: %s",  note.getDayOfWeekAsString()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView desc;
        TextView dayOfWeek;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header_textView);
            desc = itemView.findViewById(R.id.description_textView);
            dayOfWeek = itemView.findViewById(R.id.day_of_week_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListenerAdapter.onClickListener(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListenerAdapter.onLongClickListener(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}