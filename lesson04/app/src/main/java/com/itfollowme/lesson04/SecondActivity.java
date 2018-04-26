package com.itfollowme.lesson04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

  private TextView mTvContent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    mTvContent = findViewById(R.id.tv_content);

    Intent intent = getIntent();
    Bundle content = intent.getBundleExtra("content");
//    Person person  = (Person) content.getSerializable("p1");
//    mTvContent.setText(person.id+" "+person.name+" "+person.birth);
    Animal animal = content.getParcelable("a1");
    mTvContent.setText(animal.age+" "+animal.name);
  }
}
