package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.m2dl.toulousependu.R;
import com.m2dl.toulousependu.utils.Config;

public class ScoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoresActivity.this.startActivity(new Intent(ScoresActivity.this, AccueilActivity.class));
            }
        });

        Config config = new Config(this);

        String easyStr;
        int easyHs = config.getEasyHighScore();
        if (easyHs == -1) {
            easyStr = "Facile: pas de meilleur score";
        } else {
            easyStr = "Facile: " + easyHs;
        }
        ((TextView) findViewById(R.id.easyTextView)).setText(easyStr);

        String mediumStr;
        int mediumHs = config.getMediumHighScore();
        if (mediumHs == -1) {
            mediumStr = "Moyen: pas de meilleur score";
        } else {
            mediumStr = "Moyen: " + mediumHs;
        }
        ((TextView) findViewById(R.id.mediumTextView)).setText(mediumStr);

        String hardStr;
        int hardHs = config.getEasyHighScore();
        if (hardHs == -1) {
            hardStr = "Difficile: pas de meilleur score";
        } else {
            hardStr = "Difficile: " + hardHs;
        }
        ((TextView) findViewById(R.id.hardTextView)).setText(hardStr);

        String extremeStr;
        int extremeHs = config.getEasyHighScore();
        if (extremeHs == -1) {
            extremeStr = "Extrême: pas de meilleur score";
        } else {
            extremeStr = "Extrême: " + extremeHs;
        }
        ((TextView) findViewById(R.id.extremeTextView)).setText(extremeStr);
    }
}
