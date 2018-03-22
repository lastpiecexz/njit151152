package me.itfollow.a04_view;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SecondActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        imageView = (ImageView) findViewById(R.id.imageView);
        final Matrix matrix = imageView.getImageMatrix();
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("itfollowme","seekbar:"+i);
                matrix.setRotate(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               // seekBar.setProgress(50);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //seekBar.setProgress(0);
            }
        });
    }


}
