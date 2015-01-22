package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.m2dl.toulousependu.R;

public class DifficultyActivity extends Activity {

    public static String TAG_DIFFICULTE = "DIFFICULTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }

    public void facile(View v){
        startGame(1);
    }

    public void moyen(View v){
        startGame(2);
    }

    public void difficile(View v){
        startGame(3);
    }

    public void extreme(View v){
        startGame(4);
    }

    private void startGame(int difficulty) {
        Intent intent = new Intent(this,PenduActivity.class);
        intent.putExtra(TAG_DIFFICULTE,difficulty);
        startActivity(intent);

    }
}
