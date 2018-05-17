package com.itfollowme.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.itfollowme.servicedemo.LocalService.LocalBinder;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }



  public void startService(View view) {
    Intent intent = new Intent(this,LocalService.class);
    startService(intent);
  }

  public void stopService(View view) {
    Intent intent = new Intent(this,LocalService.class);
    stopService(intent);
  }

  private ServiceConnection conn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      LocalService ls = ((LocalBinder)service).getService();
      ls.print();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
  };

  public void bindService(View view) {
    Intent intent = new Intent(this,LocalService.class);
    bindService(intent, conn, BIND_AUTO_CREATE);
  }

  public void unBindService(View view) {
    unbindService(conn);
  }
}
