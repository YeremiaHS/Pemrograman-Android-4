package com.example.intentscore_yeremia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private TextView textHome;

    private TextView scoreHome;

    private ImageView homeLogo;

    private TextView textAway;

    private TextView scoreAway;

    private ImageView awayLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        textHome = findViewById(R.id.home_team);

        scoreHome = findViewById(R.id.score_home);

        homeLogo = findViewById(R.id.home_logo);

        textAway = findViewById(R.id.away_team);

        scoreAway = findViewById(R.id.score_away);

        awayLogo = findViewById(R.id.away_logo);

        Intent intent = getIntent();
        String home = intent.getStringExtra("home_team");
        textHome.setText(home);
        Bitmap imgHome = (Bitmap) intent.getParcelableExtra("image_home_bitmap");
        homeLogo.setImageBitmap(imgHome);


        String away = intent.getStringExtra("away_team");
        textAway.setText(away);
        Bitmap imgAway = (Bitmap) intent.getParcelableExtra("image_away_bitmap");
        awayLogo.setImageBitmap(imgAway);
    }

    public void handlerNext(View view) {

        startActivity(new Intent(MatchActivity.this, ResultActivity.class));
    }

    public void handlerAddHome(View view) {
        int lastScore = Integer.parseInt(scoreHome.getText().toString());
        String score = String.valueOf(lastScore + 1);
        scoreHome.setText(score);
    }

    public void handlerAddAway(View view) {
        int lastScore = Integer.parseInt(scoreAway.getText().toString());
        String score = String.valueOf(lastScore + 1);
        scoreAway.setText(score);
    }
}