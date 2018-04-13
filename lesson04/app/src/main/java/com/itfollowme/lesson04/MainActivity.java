package com.itfollowme.lesson04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  private EditText mEtContent;

  private boolean flag = true;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEtContent = findViewById(R.id.et_content);
    Log.i("lifecycle","onCreate");
//    new Thread(){
//      @Override
//      public void run() {
//        int i = 1;
//        while(flag){
//          Log.i("lifecycle",""+i++);
//          try {
//            Thread.sleep(2000);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    }.start();



  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.i("lifecycle","onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();

    Log.i("lifecycle","onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.i("lifecycle","onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    flag = false;
    Log.i("lifecycle","onStop");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.i("lifecycle","onRestart");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.i("lifecycle","onDestroy");
  }

  public void jump2Second(View view) {
    Intent intent = new Intent(this,SecondActivity.class);
    Bundle bundle = new Bundle();
 /*   Person person = new Person();
    person.id = 10086;
    person.name = mEtContent.getText().toString();
    person.birth = new Date();

    bundle.putSerializable("p1",person);
    intent.putExtra("content",bundle);*/
    Animal animal = new Animal();
    animal.age = 18;
    animal.name = mEtContent.getText().toString();
    bundle.putParcelable("a1",animal);
    intent.putExtra("content",bundle);
    startActivity(intent);
    finish();
  }

  public void jump2Third(View view) {
    Intent intent = new Intent(this,ThirdActivity.class);
    startActivity(intent);
  }

  public void jump2Forth(View view) {
    Intent intent = new Intent(this,ForthActivity.class);
    startActivityForResult(intent,100);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.i("lifecycle",requestCode+" "+resultCode);
  }
}
