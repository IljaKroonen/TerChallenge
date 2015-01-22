package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.m2dl.toulousependu.R;

import org.w3c.dom.Text;

/**
 * Created by Romain on 22/01/2015.
 */
public class PenduActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);
    }

    public void letter(View v) {
        Button button = (Button) v;
        Log.d("LETTER",button.getText()+"");
    }

}
