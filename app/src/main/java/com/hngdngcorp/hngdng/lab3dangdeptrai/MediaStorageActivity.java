package com.hngdngcorp.hngdng.lab3dangdeptrai;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MediaStorageActivity extends AppCompatActivity {
    private EditText mEdtText;
    private Button mBtnRead;
    private Button mBtnWrite;
    private TextView mTvResult;
    String filename = "file.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_storage);

        mEdtText = (EditText) findViewById(R.id.edtText);
        mBtnRead = (Button) findViewById(R.id.btnRead);
        mBtnWrite = (Button) findViewById(R.id.btnWrite);

        mBtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvResult = (TextView)findViewById(R.id.tvResult);
                mTvResult.setText(readFile(filename));
            }
        });
        mBtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile(filename,mEdtText.getText().toString());
            }
        });
    }
    public void saveFile(String file, String text){
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(MediaStorageActivity.this,"Saved!",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(MediaStorageActivity.this,"FileNotFound!",Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MediaStorageActivity.this, e.toString() ,Toast.LENGTH_LONG).show();
        }
    }
    public String readFile(String file){
     String s = "";
     try {
         FileInputStream fis = openFileInput(file);
         int size = fis.available();
         byte[] buffer = new byte[size];
         fis.read(buffer);
         fis.close();
         s = new String(buffer);
         Toast.makeText(this,s,Toast.LENGTH_LONG).show();
     } catch (FileNotFoundException e) {
         e.printStackTrace();
         Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
     } catch (IOException e) {
         e.printStackTrace();
         Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
     }
        return s;
    }
}
