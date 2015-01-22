package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.m2dl.toulousependu.R;

public class DifficultyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }

    public void facile(View v){
        startGame();
    }

    public void moyen(View v){
        startGame();
    }

    public void difficile(View v){
        startGame();
    }

    public void extreme(View v){
        startGame();
    }

    private void startGame() {
        Intent intent = new Intent(this,PenduActivity.class);
        startActivity(intent);
        finish();
    }
}
