package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.m2dl.toulousependu.R;
import com.m2dl.toulousependu.resourcehelpers.ToulouseDataDownloader;
import com.m2dl.toulousependu.utils.Config;

/**
 * Created by Romain on 22/01/2015.
 */
public class AccueilActivity extends Activity {
    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        config = new Config(this);

        Button scoresButton = (Button) findViewById(R.id.scoresButton);
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, ScoresActivity.class);
                startActivity(intent);
            }
        });

        ImageButton soundButton = (ImageButton) findViewById(R.id.muteButton);


        if(config.getSoundSetting()) {
            soundButton.setImageResource(R.drawable.mute);
        } else {
            soundButton.setImageResource(R.drawable.unmute);
        }
    }

    public void newGame(View v) {
        Intent intent = new Intent(this,DifficultyActivity.class);
        startActivity(intent);
    }

    public void toggleSound(View v) {
        boolean silent = !config.getSoundSetting();
        config.setSilent(silent);
        ImageButton soundButton = (ImageButton) findViewById(R.id.muteButton);

        if(silent) {
            soundButton.setImageResource(R.drawable.mute);
        } else {
            soundButton.setImageResource(R.drawable.unmute);
        }
    }

    public void downloadData(View v) {
        new ToulouseDataDownloader().execute(this);
    }

}
