package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.m2dl.toulousependu.R;
import com.m2dl.toulousependu.resourcehelpers.CsvWordProvider;
import com.m2dl.toulousependu.var.GlobalVars;

import java.io.IOException;

/**
 * Created by Romain on 22/01/2015.
 */
public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    private Activity activity;
    private boolean csvReady;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = this;
        context = this;
        csvReady = false;
        try {
            CsvWordProvider csv = new CsvWordProvider(this,R.raw.prenomsopentoulouse);
            GlobalVars.csvWordProvider = csv;
            csvReady = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                if(csvReady) {
                    Intent i = new Intent(activity, AccueilActivity.class);
                    startActivity(i);
                    finish();
                    return;
                }
                else {
                    Toast.makeText(context,R.string.csv_error,Toast.LENGTH_SHORT).show();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
