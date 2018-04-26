package com.itfollowme.lesson07;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  private EditText mEtContent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEtContent = findViewById(R.id.et_content);
  }

  @Override
  protected void onPause() {
    super.onPause();
    //将内容保存
    SharedPreferences sp = getSharedPreferences("lesson07",MODE_PRIVATE);
    Editor editor = sp.edit();
    editor.putString("content",mEtContent.getText().toString());
    editor.commit();
  }

  @Override
  protected void onResume() {
    super.onResume();
    //将内容从sp中读出
    SharedPreferences sp = getSharedPreferences("lesson07",MODE_PRIVATE);
    String content = sp.getString("content","");
    mEtContent.setText(content);
  }

  public void saveExternalPrivate(View view) {
    if(isExternalStorageWritable()){
      File dir = getExternalFilesDir("null");
      Log.i("lesson07","外部私有路径："+dir.getAbsolutePath());
      File file  = new File(dir,"excontentprivate.txt");
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(mEtContent.getText().toString());
        bw.flush();
        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean isExternalStorageWritable(){
    Log.i("lesson07",Environment.getExternalStorageState());
    if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
      return true;
    }
    return false;
  }

  public void saveInternal(View view) {
    Log.i("Internal cache Dir",getCacheDir().getAbsolutePath());
    Log.i("Internal file Dir",getFilesDir().getAbsolutePath());

    File file = new File(getFilesDir(),"content.txt");
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(mEtContent.getText().toString());
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveExternalPublic(View view) {
    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    Log.i("lesson07","共有的文档路径:"+dir.getAbsolutePath());
    File file = new File(dir,"PublicContent.txt");
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(mEtContent.getText().toString());
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
