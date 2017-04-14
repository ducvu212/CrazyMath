package com.example.vuminhduc.crazymath;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class playActivity extends AppCompatActivity implements View.OnClickListener{

    private Random rand;
    private int num1;
    private int num2;
    private TextView tv_showScore, tv_showTime, tv_ques;
    private int ans = 0, ans1;
    private int score = 0, time = 10;
    private int rad;
    private task Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullAct();
        setContentView(R.layout.activity_play);
        findViewByIds();
        initsComponent();
        setControls();
        setEvents();

    }

    private void setEvents() {

        findViewById(R.id.btn_true).setOnClickListener(this);
        findViewById(R.id.btn_false).setOnClickListener(this);

    }

    private void initsComponent() {
        tv_ques.setText("");
        Task = new task();

    }

    private void findViewByIds() {

        tv_showScore = (TextView) findViewById(R.id.tv_Showscore);
        tv_showTime = (TextView) findViewById(R.id.tv_Showtime);
        tv_ques = (TextView) findViewById(R.id.tv_ques);

    }

    private void setControls() {
        Task = new task();
        Task.execute(time) ;

        rand = new Random();
        num1 = rand.nextInt(1000);
        num2 = rand.nextInt(1000);
        ans = num1 + num2;
        rad = rand.nextInt(15);
        if (num1 % 2 == 0) {
            ans1 = ans;
            tv_ques.setText(num1 + " + " + num2 + " = " + ans + "");

        } else {
            ans1 = ans + rad;
            tv_ques.setText(num1 + " + " + num2 + " = " + ans1 + "");

        }

    }

    private void fullAct() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_true:
                if (ans1 == ans) {
                    Toast.makeText(this, "Bạn đã trả lời đúng!", Toast.LENGTH_SHORT).show();
                    score += 1;
                    tv_showScore.setText(score + "");
                    Task.cancel(true);
                    setControls();
                    level();

                } else {
                    Toast.makeText(this, "Bạn đã trả lời sai!", Toast.LENGTH_SHORT).show();
                    if (score <= 0) {
                        score = 0;
                        dialog();

                    } else {
                        setControls();
                        score -= 1;
                        tv_showScore.setText(score + "");
                        level();
                    }
                }
                break;

            case R.id.btn_false:

                if (ans1 == ans) {
                    Toast.makeText(this, "Bạn đã trả lời sai!", Toast.LENGTH_SHORT).show();

                    if (score <= 0) {
                        score = 0;
                        dialog();

                    } else {
                        score -= 1;
                        tv_showScore.setText(score + "");
                        setControls();
                        level();
                    }

                } else {
                    Toast.makeText(this, "Bạn đã trả lời đúng!", Toast.LENGTH_SHORT).show();
                    score += 1;
                    tv_showScore.setText(score + "");
                    Task.cancel(true);
                    setControls();
                    level();
                }

                break;

            default:
                break;

        }


    }

    private void playAgain() {

        Intent intent = new Intent(this, playActivity.class);
        startActivity(intent);

    }

    private void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("GAME OVER");
        dialog.setMessage("Do you want play again ?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playAgain();
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        dialog();
    }


    private void level() {
        if (score < 10) {
            time = 10;
        } else if (score >= 10 && score < 30) {
            time = 8;
        } else if (score >= 30 && score < 60) {
            time = 5;
        } else time = 3;
    }

    class task extends AsyncTask<Integer, String, Void>{
        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = integers[0]; i >= 0; i--) {
                publishProgress(i+"", "helo");
                SystemClock.sleep(1000);
                if(isCancelled()) {
                    break;
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv_showTime.setText(values[0] + "") ;
        }
    }

}
