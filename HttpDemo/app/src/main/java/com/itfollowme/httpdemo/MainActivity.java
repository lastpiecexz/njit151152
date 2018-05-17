package com.itfollowme.httpdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private DailyAdapter dailyAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.rv_results);


  }

  private class ZhihuTask extends AsyncTask<URL, Integer, String> {

    @Override
    protected String doInBackground(URL... urls) {
      try {
        URL url = urls[0];
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        InputStream inputStream = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        char[] buf = new char[1];
        StringBuilder stringBuilder = new StringBuilder();
        while (br.read(buf)!=-1) {
          stringBuilder.append(buf);
        }
        Log.i("HttpDemo",stringBuilder.toString());


        br.close();
        conn.disconnect();
        return stringBuilder.toString();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      return null;
    }
  }



//
//  public Handler handler = new Handler(new Callback() {
//    @Override
//    public boolean handleMessage(Message msg) {
//      Bundle bundle = msg.getData();
//      String result = bundle.getString("result");
////      mTvResult.setText(result);
//      return true;
//    }
//  }) ;

  public void loadData(View view) {
//
//    new Thread(){
//      @Override
//      public void run() {
//        try {
//          URL url = new URL("https://news-at.zhihu.com/api/4/news/latest");
//          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//          conn.connect();
//          InputStream inputStream = conn.getInputStream();
//          BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//          char[] buf = new char[1];
//          StringBuilder stringBuilder = new StringBuilder();
//          while (br.read(buf)!=-1) {
//            stringBuilder.append(buf);
//          }
//          Log.i("HttpDemo",stringBuilder.toString());
//
//          Message message = new Message();
//          Bundle bundle = new Bundle();
//          bundle.putString("result",stringBuilder.toString());
//          message.setData(bundle);
//          handler.sendMessage(message);
//          br.close();
//          conn.disconnect();
//        } catch (MalformedURLException e) {
//          e.printStackTrace();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }try {
//          URL url = new URL("https://news-at.zhihu.com/api/4/news/latest");
//          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//          conn.connect();
//          InputStream inputStream = conn.getInputStream();
//          BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//          char[] buf = new char[1];
//          StringBuilder stringBuilder = new StringBuilder();
//          while (br.read(buf)!=-1) {
//            stringBuilder.append(buf);
//          }
//          Log.i("HttpDemo",stringBuilder.toString());
//
//          Message message = new Message();
//          Bundle bundle = new Bundle();
//          bundle.putString("result",stringBuilder.toString());
//          message.setData(bundle);
//          handler.sendMessage(message);
//          br.close();
//          conn.disconnect();
//        } catch (MalformedURLException e) {
//          e.printStackTrace();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//    }.start();
    try {
      URL url = new URL("https://news-at.zhihu.com/api/4/news/latest");
      ZhihuTask zhihuTask = new ZhihuTask();
      zhihuTask.execute(url);
      String result = zhihuTask.get();

      DailyListBean dailyListBean = JSON.parseObject(result, DailyListBean.class);
      dailyAdapter = new DailyAdapter(dailyListBean,this);
      recyclerView.setLayoutManager(
          new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      recyclerView.setAdapter(dailyAdapter);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }


  }
}
