package com.itfollowme.lesson08_2;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ContentResolver resolver = getContentResolver();
    Cursor cursor = resolver.query(Uri.parse("content://cn.edu.njit.lesson08"),null,null,null,null);

    while(cursor.moveToNext()){
      Log.i("lesson08_2",cursor.getString(0) + " "+       cursor.getString(1));
    }
    cursor.close();
  }
}

