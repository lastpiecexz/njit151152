package com.itfollowme.lesson08;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  private EditText mEtTitle;
  private EditText mEtPrice;
  private EditText mEtISBN;

  SQLiteDatabase db;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DemoSQLiteHelper helper = new DemoSQLiteHelper(this, "bookstore.db", null, 2);
    db = helper.getWritableDatabase();

    mEtTitle = findViewById(R.id.et_title);
    mEtPrice = findViewById(R.id.et_price);
    mEtISBN = findViewById(R.id.et_isbn);
  }

  public void addBook(View view) {
    String title = mEtTitle.getText().toString();
    int price = Integer.parseInt(mEtPrice.getText().toString());
    String isbn = mEtISBN.getText().toString();

    ContentValues cv = new ContentValues();
    cv.put("title",title);
    cv.put("price",price);
    cv.put("isbn",isbn);

    long rowId = db.insert("book", null,cv);
    Log.i("lesson08",""+rowId);

    Intent intent  =  new Intent(this,ListBookActivity.class);
    startActivity(intent);

  }
}
