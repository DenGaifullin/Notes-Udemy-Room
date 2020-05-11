package com.example.notesudemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    NotesDao notesDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detale);
        final Spinner spinner = findViewById(R.id.spinner);
        final EditText editText = findViewById(R.id.header_editText);
        final EditText editText2 = findViewById(R.id.desc_editText);
        final RadioGroup group = findViewById(R.id.radioGroup);

        NotesDatabase notesDatabase = AccessNotesDatabase.getInstance().getNotesDatabase();
        notesDao = notesDatabase.getNotesDao();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
                if(!editText.getText().toString().equals("") ){
                    Note note = new Note(editText.getText().toString(), editText2.getText().toString(),
                            spinner.getSelectedItemPosition(), Integer.parseInt(radioButton.getText().toString()));
                    new InsertData().execute(note);
                    startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    finish();
                } else Toast.makeText(DetailActivity.this, "Введите заголовок", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private class InsertData extends AsyncTask<Note, Void, Void>{
        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.insert(notes[0]);
            return null;
        }
    }
}