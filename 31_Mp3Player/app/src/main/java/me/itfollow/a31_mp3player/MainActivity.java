package me.itfollow.a31_mp3player;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private ListView listView;

    private List<Map<String,Object>> list;

    private SimpleAdapter adapter;

    private Intent intent;

    private IMusicService musicService;


    private ImageButton mIBPlayOrPause; //播放或者暂停的按钮

    private TextView mTVStatusTitle;
    private TextView mTVStatusArtist;
    private TextView mTVStatusDuration;
    private ProgressBar mPBDuration;

    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
               // Log.i("itfollow.me","播放:"+musicService.getDuration());
                mPBDuration.setProgress(musicService.getDuration());
            }
        };

        mIBPlayOrPause = (ImageButton) findViewById(R.id.ib_playOrPause);
        mTVStatusTitle = (TextView) findViewById(R.id.tv_status_title);
        mTVStatusArtist = (TextView) findViewById(R.id.tv_status_artist);
        mTVStatusDuration = (TextView) findViewById(R.id.tv_status_duration);
        mPBDuration = (ProgressBar) findViewById(R.id.pb_duration);

        listView = (ListView) findViewById(R.id.lv_songs);

        //
        list = new ArrayList<>();
        initSongList();

        String[] from = {"title","artist"};
        int[] to = {R.id.tv_title,R.id.tv_artist};
        adapter = new SimpleAdapter(this,list,R.layout.item_layout,from,to);

        listView.setAdapter(adapter);
        //当我们点击歌曲列表的单项的时候，开始播放
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Map<String,Object> map = list.get(i);

                play((String)map.get("url"));

                mTVStatusTitle.setText((String)map.get("title"));
                mTVStatusArtist.setText((String)map.get("artist"));
                int duration = (Integer) map.get("duration");
                mPBDuration.setMax(duration);
                SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。

                String ms = formatter.format(duration);
                mTVStatusDuration.setText(ms);

                timer.schedule(timerTask,0,500);

            }
        });

        intent = new Intent(this,MusicService.class);

        startService(intent);
        bindService(intent,new MusicServiceConnection(),BIND_AUTO_CREATE);
    }

    public void playOrPause(View view) {
        Log.i("itfollow.me","当前播放器状态："+musicService.getState());
        //如果当前的播放装填是正在播放，那么点击以后变成暂停状态
        if(musicService.getState() == IMusicService.PLAY){

            mIBPlayOrPause.setImageResource(R.drawable.inc_start);
            musicService.pause();
        }else if(musicService.getState() == IMusicService.PAUSE){
            mIBPlayOrPause.setImageResource(R.drawable.inc_pause);
            musicService.play();
        }
        //如果是暂停状态，则变成播放状态

    }


    private class MusicServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicService = (IMusicService) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    private void initSongList() {

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        //遍历媒体数据库
        while (cursor.moveToNext()) {


            //歌曲编号
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            //歌曲标题
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            //歌曲的专辑名：MediaStore.Audio.Media.ALBUM
            String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            //歌曲的歌手名： MediaStore.Audio.Media.ARTIST
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            //歌曲文件的路径 ：MediaStore.Audio.Media.DATA
            String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            //歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            //歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
            Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

            if (size > 1024 * 800) {//大于800K
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);
                map.put("title",title);
                map.put("album",album);
                map.put("artist",artist);
                map.put("url",url);
                map.put("duration",duration);
                map.put("size",size);
                list.add(map);
                Log.i("itfollow.me",map.toString());
            }
        }
    }
    //1. 将可播放的音乐都列出来

    public void play(String url){
        musicService.play(url);

        //将下面按钮的图标变成暂停图标
        mIBPlayOrPause.setImageResource(R.drawable.inc_pause);
    }

}
