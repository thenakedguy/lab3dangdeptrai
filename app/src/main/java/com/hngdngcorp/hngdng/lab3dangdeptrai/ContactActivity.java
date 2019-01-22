package com.hngdngcorp.hngdng.lab3dangdeptrai;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    TextView tvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        tvContact = (TextView) findViewById(R.id.tvContact);
        if (ContextCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(ContactActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},
                    999);
        }else {

        }
        showContact();
    }

    private void showContact() {
        Uri uri = Uri.parse("content://contacts/people");
        String contact = "";
        Cursor cursor= getContentResolver().query(uri,null,null,null,null);

        if ( cursor != null) {
            if (cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contact = contact + name + id +"\n" ;
                    cursor.moveToNext();
                }
                cursor.close();
                tvContact.setText(contact);
            }else {
                // Thong bao khong co danh ba
                Toast.makeText(this,"khong co danh ba dien thoai",Toast.LENGTH_LONG).show();
            }
        }
    }

}
