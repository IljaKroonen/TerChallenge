package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m2dl.toulousependu.R;
import com.m2dl.toulousependu.utils.DrawingView;
import com.m2dl.toulousependu.var.GlobalVars;

import org.w3c.dom.Text;

/**
 * Created by Romain on 22/01/2015.
 */
public class PenduActivity extends Activity{


    private DrawingView pendu;
    private int difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);
        difficulty = getIntent().getIntExtra(DifficultyActivity.TAG_DIFFICULTE,1);
        pendu = (DrawingView) findViewById(R.id.pendu);
        pendu.setOnTouchListener(pendu);
        affichageLettre();
    }

    public void letter(View v) {
        Button button = (Button) v;
        Log.d("LETTER",button.getText()+"");
    }


    public String recupGlobalvar(){
       return GlobalVars.csvWordProvider.getWord(difficulty);
    }

    public void affichageLettre(){
        String mot = recupGlobalvar();
        char lettre;


        for (int i = 0 ; i < mot.length() ;i++)
           lettre=mot.charAt(i);
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.letter_pendu);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(60);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(5, 5, 5, 5); // llp.setMargins(left, top, right, bottom);
            textView.setLayoutParams(llp);
            LinearLayout result = (LinearLayout) findViewById(R.id.layout_result);
            result.addView(textView);

    }



}
