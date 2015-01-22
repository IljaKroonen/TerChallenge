package com.m2dl.toulousependu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.m2dl.toulousependu.R;
import com.m2dl.toulousependu.game.Game;
import com.m2dl.toulousependu.utils.DrawingView;
import com.m2dl.toulousependu.var.GlobalVars;

import org.w3c.dom.Text;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by Romain on 22/01/2015.
 */
public class PenduActivity extends Activity{

    private DrawingView penduTete;
    private DrawingView penduCorps;
    private DrawingView penduBrasG;
    private DrawingView penduBradD;
    private DrawingView penduJambeG;
    private DrawingView penduJambeD;
    private TextView textScore;
    private int difficulty;
    private Game game;
    private String mot ="";
    private ArrayList<TextView> textResult;
    private Dialog dialog;

    private Handler myHandler;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            final int score = game.getScore();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textScore.setText(score+"");
                }
            });
            myHandler.postDelayed(this,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);
        initDrawingView();
        textScore = (TextView) findViewById(R.id.scoreText);
        difficulty = getIntent().getIntExtra(DifficultyActivity.TAG_DIFFICULTE,1);
        affichageLettre();
        createDialog();
        myHandler = new Handler();
        myHandler.postDelayed(myRunnable,1000);
    }

    public void onPause() {
        super.onPause();
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable); // On arrete le callback
    }

    private void initDrawingView() {
        GlobalVars.isDraw = true;
        penduTete = (DrawingView) findViewById(R.id.pendu_tete);
        penduTete.setOnTouchListener(penduTete);
        penduCorps = (DrawingView) findViewById(R.id.pendu_corps);
        penduCorps.setOnTouchListener(penduCorps);
        penduBrasG = (DrawingView) findViewById(R.id.pendu_bras_g);
        penduBrasG.setOnTouchListener(penduBrasG);
        penduBradD = (DrawingView) findViewById(R.id.pendu_bras_d);
        penduBradD.setOnTouchListener(penduBradD);
        penduJambeG = (DrawingView) findViewById(R.id.pendu_jambe_g);
        penduJambeG.setOnTouchListener(penduJambeG);
        penduJambeD = (DrawingView) findViewById(R.id.pendu_jambe_d);
        penduJambeD.setOnTouchListener(penduJambeD);
        disableDrawingView();
    }

    private void disableDrawingView() {
        penduTete.setEnabled(false);
        penduCorps.setEnabled(false);
        penduBrasG.setEnabled(false);
        penduBradD.setEnabled(false);
        penduJambeG.setEnabled(false);
        penduJambeD.setEnabled(false);
    }

    public void letter(View v) {
        if (!GlobalVars.isDraw) {
            Toast.makeText(this,"Veuillez dessiner dans la zone définie avant de continuer à jouer",Toast.LENGTH_SHORT).show();
            return;
        }
        Button button = (Button) v;
        char lettre = button.getText().charAt(0);
        if (game.inputLetter(lettre)) {
            button.setTextColor(Color.GREEN);
            button.setEnabled(false);
            int i = 0;
            for (char c:game.getCurrentWord()) {
                if(c != 0) {
                    textResult.get(i).setText(c+"");
                }
                i++;
            }
        } else {
            GlobalVars.isDraw = false;
            button.setTextColor(Color.RED);
            button.setEnabled(false);
            displayDrawZone();
        }
        resultatGame();
    }

    public void displayDrawZone() {
        switch (game.getRemainingLives()) {
            case 5 :
                disableDrawingView();
                penduTete.setEnabled(true);
                break;
            case 4 :
                disableDrawingView();
                penduCorps.setEnabled(true);
                break;
            case 3 :
                disableDrawingView();
                penduBrasG.setEnabled(true);
                break;
            case 2 :
                disableDrawingView();
                penduBradD.setEnabled(true);
                break;
            case 1 :
                disableDrawingView();
                penduJambeG.setEnabled(true);
                break;
            case 0 :
                disableDrawingView();
                penduJambeD.setEnabled(true);
                break;
        }
    }


    public String recupGlobalvar(){
       return GlobalVars.csvWordProvider.getWord(difficulty);
    }

    public void affichageLettre(){
        textResult = new ArrayList<TextView>();
        mot = recupGlobalvar();
        game = new Game(mot);

        for (int i = 0 ; i < mot.length() ;i++) {
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.letter_pendu);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(60);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(5, 5, 5, 5); // llp.setMargins(left, top, right, bottom);
            textView.setLayoutParams(llp);
            LinearLayout result = (LinearLayout) findViewById(R.id.layout_result);
            textResult.add(textView);
            result.addView(textView);
        }

    }


    private void gameOver(boolean res) {
        MediaPlayer.create(getApplicationContext(), R.raw.whoosh).start();


        dialog.show();
        dialog.setCancelable(false);

        TextView best = (TextView) dialog.findViewById(R.id.meilleurScore);
        TextView scoreTexte = (TextView) dialog.findViewById(R.id.textScore);
        TextView scoreDialog = (TextView) dialog.findViewById(R.id.score);
        Button play = (Button) dialog.findViewById(R.id.buttonStart);
        Button leaderboard = (Button) dialog.findViewById(R.id.buttonScore);
        TextView result = (TextView) dialog.findViewById(R.id.result);
        if (res == true){
           result.setText("VICTOIRE !");
        }
        else{
            result.setText("DEFAITE !");
            scoreDialog.setVisibility(View.GONE);
            scoreTexte.setVisibility(View.GONE);
            int i=0;
            for (char c:game.getFinishedWord()) {

                if(c != 0) {
                    textResult.get(i).setText(c+"");
                }
                i++;
            }
        }
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }

        });

        leaderboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });



    }

    public void createDialog() {
        dialog = new Dialog(this,R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_finish);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

    }


    public void resultatGame(){
        boolean res=true;
        if (game.isFinished() == true) {

            if (game.isVictory() == true) {
                gameOver(true);
            }
            else {
                findViewById(R.id.layout_clavier).setVisibility(View.GONE);
                findViewById(R.id.layout_next).setVisibility(View.VISIBLE);
            }

        }
    }

    public void suivant(View v){
        gameOver(false);
    }

}
