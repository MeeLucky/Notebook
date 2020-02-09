package com.example.notesmesenpai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class EditActivity extends AppCompatActivity {

    static String OldFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        OldFileName = getIntent().getExtras().get("name").toString();

        if(!Objects.equals(OldFileName, "")) {
            EditText editor = findViewById(R.id.TextEditor);
            editor.setText(openFile(OldFileName));
        }
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void Save(View view) {
        EditText editor = findViewById(R.id.TextEditor);
        String text = editor.getText().toString();

        //if text null don't save
        if(Objects.equals(text, "")) Back(null);
        else {
            deleteFile(OldFileName);

            String newFileName = text.substring(0, (text.length() > 100) ? 100 : text.length());

            saveFile(newFileName, text);


            Back(null);
        }

    }

    // сохранение файла
    public void saveFile(String FILE_NAME, String text){

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // открытие файла
    public String openFile(String FILE_NAME){

        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            return text;
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return "ERROR: Not Found...";
    }
}
