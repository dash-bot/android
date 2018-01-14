package dashbot.teamcraps.com.dashbot;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Rhys on 2018-01-13.
 */

public class Splash extends AppCompatActivity{
    public int DELAY = 4000; // Milliseconds

    private AnimationDrawable animationDrawable;
    private ImageView mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Set-up anything needs to be set up before running MainActivity here.
        mProgressBar = findViewById(R.id.main_progress);
        mProgressBar.setBackgroundResource(R.drawable.progress);
        animationDrawable = (AnimationDrawable)mProgressBar.getBackground();
        mProgressBar.setVisibility(View.VISIBLE);
        animationDrawable.start();

        setTimer(DELAY);
    }

    private void setTimer(int time){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Stop the animation
                mProgressBar.setVisibility(View.GONE);
                animationDrawable.stop();

                // Run the next activity
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }
        }, time);
    }
}
