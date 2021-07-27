package com.example.minigame1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView timeText, scoreText;
    private final String TIMETEXT = "Time Left:";
    private final String SCORETEXT = "Score: ";
    private final String FINISHTEXT = "Time is Up!";
    private ImageView img10, img11, img12, img20, img21, img22, img30, img31, img32, img40, img41, img42;
    private int score = 0;
    private ImageView[] imageViews;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.textView);
        scoreText = findViewById(R.id.textView2);

        img10 = findViewById(R.id.imageView10);
        img11 = findViewById(R.id.imageView11);
        img12 = findViewById(R.id.imageView12);

        img20 = findViewById(R.id.imageView20);
        img21 = findViewById(R.id.imageView21);
        img22 = findViewById(R.id.imageView22);

        img30 = findViewById(R.id.imageView30);
        img31 = findViewById(R.id.imageView31);
        img32 = findViewById(R.id.imageView32);

        img40 = findViewById(R.id.imageView40);
        img41 = findViewById(R.id.imageView41);
        img42 = findViewById(R.id.imageView42);

        imageViews = new ImageView[] {img10, img11, img12, img20, img21, img22, img30, img31, img32, img40, img41, img42};
        hideImages();

        new CountDownTimer(10000,1000) {

            @Override
            public void onTick(long l) {
                timeText.setText(TIMETEXT + l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText(FINISHTEXT);
                handler.removeCallbacks(runnable);
                for( ImageView image : imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Restart");
                alertBuilder.setMessage("Are you Sure?");
                alertBuilder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Alright!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertBuilder.show();
            }
        }.start();

        scoreText.setText(SCORETEXT + score);
    }

    public void calcScore (View view){

        score++;
        scoreText.setText(SCORETEXT + score);
    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                for( ImageView image : imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int rand = random.nextInt(12);

                imageViews[rand].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
            }
        };

        handler.post(runnable);
    }
}