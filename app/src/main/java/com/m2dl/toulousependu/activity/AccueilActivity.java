package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.m2dl.toulousependu.R;

/**
 * Created by Romain on 22/01/2015.
 */
public class AccueilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button scoresButton = (Button) findViewById(R.id.scoresButton);
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, ScoresActivity.class);
                startActivity(intent);
            }
        });
    }

    public void newGame(View v) {
        Intent intent = new Intent(this,DifficultyActivity.class);
        startActivity(intent);
    }

    public void toggleSound(View v) {
    }

}
