package com.itfollowme.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

  private boolean flag = true;

  public MyService() {
    Log.i("ServiceDemo", "Construct");
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.i("ServiceDemo", "onCreate");
    flag = true;
    while (flag) {
      Log.i("ServiceDemo", String.valueOf(System.currentTimeMillis()));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }


  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i("ServiceDemo", "onDestory");

  }
}
