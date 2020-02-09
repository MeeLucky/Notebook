package com.example.notesmesenpai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowNoteList();
    }

    private void ShowNoteList() {
        LinearLayout noteList = findViewById(R.id.NoteList);

        String[] files = fileList();
        int filesCount = files.length;

        for(int i = 0; i < filesCount; i++) {
            Button btn = new Button(this);
            btn.setText(files[i]);
            btn.setTag(0);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    NewNote(v);
                }
            });

            btn.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            noteList.addView(btn);
        }
    }

    public void NewNote(View view) {
        Button btn = (Button) view;
        String tag = btn.getTag().toString();
        String name = (tag.equals("1") ? "" : btn.getText().toString());

        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        this.finish();
    }
}
