package com.itfollowme.lesson08;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListBookActivity extends AppCompatActivity {
  //view
  private ListView mLvBooks;
  //controller
  private SimpleCursorAdapter adapter;
  private SQLiteDatabase db;
  //数据库列的数组
  String[] columns = {"_id","title","price","isbn"};
  int[] views = {R.id.tv_item_id,R.id.tv_item_title,R.id.tv_item_price,R.id.tv_item_isbn};


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_book);

    DemoSQLiteHelper helper = new DemoSQLiteHelper(this, "bookstore.db", null, 2);
    db = helper.getWritableDatabase();

    mLvBooks = findViewById(R.id.lv_books);

    Cursor c = db.query("book",columns,null,null,null,null,null);

    //adapter = new SimpleCursorAdapter(this,R.layout.book_item,c,columns,views, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

    //mLvBooks.setAdapter(adapter);

    while(c.moveToNext()){
      int id = c.getInt(0);
      String title = c.getString(1);
      int price = c.getInt(2);
      String isbn = c.getString(3);
      Log.i("lesson08",id+" "+title + " "+price+"  "+isbn);
    }
  }
}
