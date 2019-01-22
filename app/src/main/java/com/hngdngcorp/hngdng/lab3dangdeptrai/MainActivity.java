package com.hngdngcorp.hngdng.lab3dangdeptrai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void contactActivity(View view) {
        Intent intent = new Intent(MainActivity.this,ContactActivity.class);
        startActivity(intent);
        finish();
    }
    public void callLogActivity(View view){
        Intent intent = new Intent(MainActivity.this,CallLogActivity.class);
        startActivity(intent);
        finish();
    }
    public void mediaStorageActivity(View view){
        Intent intent = new Intent(MainActivity.this,MediaStorageActivity.class);
        startActivity(intent);
        finish();
    }
}
