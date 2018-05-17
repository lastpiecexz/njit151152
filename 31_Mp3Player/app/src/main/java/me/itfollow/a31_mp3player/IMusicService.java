package me.itfollow.a31_mp3player;

/**
 * Created by notre on 2016/7/22.
 */
public interface IMusicService {
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int STOP = 3;
    void play(String url);
    void play();
    void pause();
    void stop();
    void next();
    int getDuration();
    int getState();
}
