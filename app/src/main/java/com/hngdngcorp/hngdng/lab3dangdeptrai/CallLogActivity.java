package com.hngdngcorp.hngdng.lab3dangdeptrai;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CallLogActivity extends AppCompatActivity {
    TextView tvCallLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        tvCallLog = (TextView) findViewById(R.id.tvCallLog);
        if (ContextCompat.checkSelfPermission(CallLogActivity.this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(CallLogActivity.this,
                    new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.WRITE_CALL_LOG},
                    999);
        }else {

        }
        showCallog();
    }

    private void showCallog() {
        Uri uri = Uri.parse("content://contacts/people");
        String calllog = "";
        Cursor cursor= getContentResolver().query(uri,null,null,null,null);

        if ( cursor != null) {
            if (cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    String phonenumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    calllog = calllog + name +" "+ phonenumber +"\n" ;
                    cursor.moveToNext();
                }
                cursor.close();
                tvCallLog.setText(calllog);
            }else {
                // Thong bao khong co danh ba
                Toast.makeText(this,"khong co danh ba dien thoai",Toast.LENGTH_LONG).show();
            }
        }
    }
}
