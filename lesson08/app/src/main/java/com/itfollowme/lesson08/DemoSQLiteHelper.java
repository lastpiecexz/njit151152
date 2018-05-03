package com.itfollowme.lesson08;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by notre on 2018/5/3.
 */

public class DemoSQLiteHelper extends SQLiteOpenHelper {

  public static final String CREATE_DB_SQL = "create table book "
      + "(_id integer primary key,"
      + "title text,"
      + "price integer"
      + " );";

  public DemoSQLiteHelper(Context context, String name,
        CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  public DemoSQLiteHelper(Context context, String name,
      CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
    super(context, name, factory, version, errorHandler);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_DB_SQL);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("alter table book add column isbn text;");
  }
}
