package com.itfollowme.baidufanyi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.itfollowme.baidufanyi.model.FanyiResponse;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void translate(View view) {
    String jsonString = "{\n"
        + "from: \"en\",\n"
        + "to: \"zh\",\n"
        + "trans_result: [\n"
        + "{\n"
        + "src: \"apple\",\n"
        + "dst: \"苹果\"\n"
        + "}\n"
        + "]\n"
        + "}";
    FanyiResponse response = JSON.parseObject(jsonString, FanyiResponse.class);
    Log.i("Fanyi",response.getTransResult().get(0).getDst());
  }
}
