package com.bfcai.islamy.Screens.Notes.Components;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bfcai.islamy.Database.NoteDatabase;
import com.bfcai.islamy.R;
import com.bfcai.islamy.Screens.Notes.NoteHomeActivity;

public class UpdateNotesActivity extends AppCompatActivity {
    EditText title , description;
    Button updateNotes;
    String id;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);
        toolbar = findViewById(R.id.updateNote_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("تحديث حمد");
        title = findViewById(R.id.update_notes_et_title);
        description = findViewById(R.id.update_notes_et_description);

        updateNotes = findViewById(R.id.update_notes_btn);

        Intent intent=getIntent();
        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        id = intent.getStringExtra("id");

        //we will only update notes when both fields contains text
        updateNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())){

                    NoteDatabase db = new NoteDatabase(UpdateNotesActivity.this);
                    db.updateNotes(title.getText().toString(),description.getText().toString(),id);

                    Intent i =new Intent(UpdateNotesActivity.this, NoteHomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
                else //is empty
                {
                    Toast.makeText(UpdateNotesActivity.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}