package com.itfollowme.lesson08;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ContactActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact);

    Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
        null, null, null, null);
    while (cursor.moveToNext()) {
      String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
      String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
      Log.i("lesson08",name);

    }
  }
}
