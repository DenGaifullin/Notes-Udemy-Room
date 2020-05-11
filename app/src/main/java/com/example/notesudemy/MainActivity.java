package com.example.notesudemy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private NotesDatabase notesDatabase;

    private List<Note> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
        // FAB
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(MainActivity.this, DetailActivity.class));
                finish();
            }
        });
        notesDatabase = AccessNotesDatabase.getInstance().getNotesDatabase();
        getData();

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new MyAdapter(arrayList);
        adapter.setClickListenerAdapter(new MyAdapter.ClickListenerAdapter() {
            @Override
            public void onClickListener(int position) {
                Toast.makeText(MainActivity.this, "Кликните и удерживайте для удаления эллемента", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickListener(int position) {
                remove(position);
//                Toast.makeText(MainActivity.this, "Кликните и удерживайте для удаления эллемента" + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Stetho
//        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
//        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        // Use the InitializerBuilder to generate an Initializer
//        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
//        Stetho.initialize(initializer);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void remove(int position){
        String[] args = new String[] {"remove", Integer.toString(position)};
        new DownloadTable().execute(args);

    }

    private void getData(){
        String[] args = new String[] {"getData"};
        new DownloadTable().execute(args);
    }

    private class DownloadTable extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            NotesDao notesDao = notesDatabase.getNotesDao();
            if(strings != null){
                if(strings[0].equals("remove")){
                    Note note = adapter.getList() .get( Integer.parseInt( strings[1]));
                    notesDao.delete(note);
                } else if(strings[0].equals("getData")){
                    List<Note> list = notesDao.getAll();
                    if( list != null){
                        arrayList.addAll(list);
                    }
                }
            }
            if (strings.length == 2)
                return strings[1];
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null) {
                adapter.getList().remove(Integer.parseInt(s));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
