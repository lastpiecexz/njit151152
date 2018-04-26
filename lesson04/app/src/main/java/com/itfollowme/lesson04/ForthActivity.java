package com.itfollowme.lesson04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ForthActivity extends AppCompatActivity {

  private EditText editText;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forth);
    editText = findViewById(R.id.et_result);
    setResult(200);
    Intent intent = getIntent();
    intent.putExtra("result", "result1111");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

  }
}
