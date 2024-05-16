package com.sg.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextNote;
    Button buttonAdd;
    ListView listViewNotes;
    ArrayList<String> notesList;
    ArrayAdapter<String> adapter;
    int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewNotes = findViewById(R.id.listViewNotes);

        notesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(adapter);

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editTextNote.setText(notesList.get(position));
                currentPosition = position;
                buttonAdd.setText("Update");
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                notesList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = editTextNote.getText().toString().trim();
                if (!note.isEmpty()) {
                    if (currentPosition == -1) {
                        notesList.add(note);
                    } else {
                        notesList.set(currentPosition, note);
                        currentPosition = -1;
                        buttonAdd.setText("Add");
                    }
                    editTextNote.setText("");
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
