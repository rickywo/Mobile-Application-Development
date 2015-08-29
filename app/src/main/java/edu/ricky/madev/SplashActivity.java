package edu.ricky.madev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * Created by Ricky Wu on 2015/8/6.
 */

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        animation1.setDuration(1000);
        animation1.setStartOffset(3000);
        animation1.setFillAfter(true);

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    int logoTimer = 0;
                    while(logoTimer < 1000) {
                        // pause for 0.1 sec
                        sleep(100);
                        logoTimer += 100;
                    }
                    startActivity(new Intent("edu.ricky.madev.MOVIELIST"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Finish this class Splash
                    finish();
                }
            }
        };
        logoTimer.start();
        iv.startAnimation(animation1);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

