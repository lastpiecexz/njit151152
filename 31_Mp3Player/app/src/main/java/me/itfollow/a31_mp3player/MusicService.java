package me.itfollow.a31_mp3player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {

    private String url;
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();


    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    private class MusicBinder extends Binder implements IMusicService{

        private int state = 3;

        @Override
        public void play(String url) {
            MusicService.this.url = url;
            if(!mediaPlayer.isPlaying()){
                try {
                    mediaPlayer.setDataSource(url);

                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                            state = PLAY;
                        }
                    });
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void play() {
            mediaPlayer.start();
            state = PLAY;
        }

        @Override
        public void pause() {
            mediaPlayer.pause();
            state = PAUSE;
        }

        @Override
        public void stop() {

        }

        @Override
        public void next() {

        }

        @Override
        public int getDuration() {

            return mediaPlayer.getCurrentPosition();
        }

        @Override
        public int getState() {
            return state;
        }
    }
}
