package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.m2dl.toulousependu.R;

/**
 * Created by Romain on 22/01/2015.
 */
public class AccueilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }

    public void newGame(View v) {
        Intent intent = new Intent(this,DifficultyActivity.class);
        startActivity(intent);
    }



}
