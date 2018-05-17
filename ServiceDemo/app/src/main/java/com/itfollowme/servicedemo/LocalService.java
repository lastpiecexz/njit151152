package com.itfollowme.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {

  public class LocalBinder extends Binder {
    LocalService getService() {
      return LocalService.this;
    }
  }

  public void print(){
    Log.i("LocalService","服务被调用拉！！！！");
  }

  @Override
  public void onCreate() {
    Log.i("LocalService","OnCreate");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.i("LocalService", "Received start id " + startId + ": " + intent);
    return START_NOT_STICKY;
  }

  @Override
  public void onDestroy() {
    Log.i("LocalService","OnDestory");
  }

  @Override
  public IBinder onBind(Intent intent) {
    Log.i("LocalService","onBind");
    return mBinder;
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.i("LocalService","onUnBind");
    return super.onUnbind(intent);
  }

  private final IBinder mBinder = new LocalBinder();

}
