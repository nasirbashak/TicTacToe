package com.nasirbashak007.tictacto;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SinglePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    Button B1, B2, B3, B4, B5, B6, B7, B8, B9;
    int clicks = 0;
    TextView T1, T2, T3;
    Button Continue, Home;
    int P1, P2, Draw;
    String MODE;
    Boolean flag = false;
    Random r = new Random();
    int[] buttonClicks = {0, 0, 0, 0, 0, 0, 0, 0, 0};


    private char[][] boardMatrix = {{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        init();

        Bundle bundle = getIntent().getExtras();
        MODE = bundle.getString("two").toString();


        Toast.makeText(getApplicationContext(), MODE, Toast.LENGTH_SHORT).show();
    }

    public void init() {
        B1 = (Button) findViewById(R.id.button1);
        B2 = (Button) findViewById(R.id.button2);
        B3 = (Button) findViewById(R.id.button3);
        B4 = (Button) findViewById(R.id.button4);
        B5 = (Button) findViewById(R.id.button5);
        B6 = (Button) findViewById(R.id.button6);
        B7 = (Button) findViewById(R.id.button7);
        B8 = (Button) findViewById(R.id.button8);
        B9 = (Button) findViewById(R.id.button9);

        Continue = (Button) findViewById(R.id.btn_continue);
        Home = (Button) findViewById(R.id.btn_home);

        T1 = (TextView) findViewById(R.id.player1_view);
        T2 = (TextView) findViewById(R.id.draw_view);
        T3 = (TextView) findViewById(R.id.player2_view);

        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);

    }

    public void rotate(Button b) {

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setDuration(200);
        b.startAnimation(rotateAnimation);
    }

    public boolean test(char c) {

        if (boardMatrix[0][0] == c && boardMatrix[0][1] == c && boardMatrix[0][2] == c) {
            return true;
        } else if (boardMatrix[1][0] == c && boardMatrix[1][1] == c && boardMatrix[1][2] == c) {
            return true;
        } else if (boardMatrix[2][0] == c && boardMatrix[2][1] == c && boardMatrix[2][2] == c) {
            return true;
        } else if (boardMatrix[0][0] == c && boardMatrix[1][0] == c && boardMatrix[2][0] == c) {
            return true;
        } else if (boardMatrix[0][1] == c && boardMatrix[1][1] == c && boardMatrix[2][1] == c) {
            return true;
        } else if (boardMatrix[0][2] == c && boardMatrix[1][2] == c && boardMatrix[2][2] == c) {
            return true;
        } else if (boardMatrix[0][0] == c && boardMatrix[1][1] == c && boardMatrix[2][2] == c) {
            return true;
        } else if (boardMatrix[2][0] == c && boardMatrix[1][1] == c && boardMatrix[0][2] == c) {
            return true;
        }


        return false;
    }

    public void checkForVictory() {

        if (test('X')) {
            Toast.makeText(getApplicationContext(), "Player 1 (X) won", Toast.LENGTH_LONG).show();
            P1++;
            T1.setText("P - 1 : " + P1);
            flag = true;
            buttonsStatus(false);

        } else if (test('O')) {
            Toast.makeText(getApplicationContext(), "Player 2 (O) won", Toast.LENGTH_LONG).show();
            P2++;
            T3.setText("P - 2 : " + P2);
            flag = true;
            buttonsStatus(false);
        }
    }

    public void buttonsStatus(Boolean b) {

        B1.setEnabled(b);
        B2.setEnabled(b);
        B3.setEnabled(b);
        B4.setEnabled(b);
        B5.setEnabled(b);
        B6.setEnabled(b);
        B7.setEnabled(b);
        B8.setEnabled(b);
        B9.setEnabled(b);
        boolean c = !b;//see this later

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B1.setText("X");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'X';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B1);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button2: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B2.setText("X");
                    B2.setEnabled(false);
                    boardMatrix[0][1] = 'X';
                    buttonClicks[1] = 1;
                    B2.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B2);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button3: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B3.setText("X");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'X';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B3);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button4: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B4.setText("X");
                    B4.setEnabled(false);
                    boardMatrix[1][0] = 'X';
                    buttonClicks[3] = 1;
                    B4.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B4);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button5: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B5.setText("X");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'X';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B5);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button6: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B6.setText("X");
                    B6.setEnabled(false);
                    boardMatrix[1][2] = 'X';
                    buttonClicks[5] = 1;
                    B6.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B6);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button7: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B7.setText("X");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'X';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B7);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button8: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B8.setText("X");
                    B8.setEnabled(false);
                    boardMatrix[2][1] = 'X';
                    buttonClicks[7] = 1;
                    B8.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B8);
                    computer();
                    //setRandomly();
                }
            }
            break;

            case R.id.button9: {
                ++clicks;
                if (clicks % 2 != 0) {
                    B9.setText("X");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'X';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#0b48d8"));
                    rotate(B9);
                    computer();
                    //setRandomly();
                }
            }
            break;
        }
        checkForVictory();

        if (clicks >= 9 && !flag) {
            Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_LONG).show();
            Draw++;
            T2.setText("Draw : " + Draw);
            buttonsStatus(false);
        }


    }

    private void computer() {
        if (clicks < 9) {
            int num = gen();

            switch (num) {

                case 1: {
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);
                }
                break;

                case 2: {
                    ++clicks;
                    B2.setText("O");
                    B2.setEnabled(false);
                    boardMatrix[0][1] = 'O';
                    buttonClicks[1] = 1;
                    B2.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B2);
                }
                break;

                case 3: {
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);
                }
                break;

                case 4: {
                    ++clicks;
                    B4.setText("O");
                    B4.setEnabled(false);
                    boardMatrix[1][0] = 'O';
                    buttonClicks[3] = 1;
                    B4.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B4);
                }
                break;

                case 5: {
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);
                }
                break;

                case 6: {
                    ++clicks;
                    B6.setText("O");
                    B6.setEnabled(false);
                    boardMatrix[1][2] = 'O';
                    buttonClicks[5] = 1;
                    B6.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B6);
                }
                break;

                case 7: {
                    ++clicks;
                    B7.setText("O");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'O';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B7);
                }
                break;

                case 8: {
                    ++clicks;
                    B8.setText("O");
                    B8.setEnabled(false);
                    boardMatrix[2][1] = 'O';
                    buttonClicks[7] = 1;
                    B8.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B8);
                }
                break;

                case 9: {
                    ++clicks;
                    B9.setText("O");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'O';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B9);
                }
                break;
            }
        }
    }

    private int gen() {

        int rand = r.nextInt(9 - 1 + 1) + 1;
        if (buttonClicks[rand - 1] == 0) {
            return rand;
        } else {
            return gen();
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void refreshTheBoard(View view) {
        //refresh The Board

        clicks = 0;
        flag = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardMatrix[i][j] = '1';
            }
        }

        for (int i = 0; i < 9; i++) {
            buttonClicks[i] = 0;
        }


        buttonsStatus(true);
        B1.setText("");
        B2.setText("");
        B3.setText("");
        B4.setText("");
        B5.setText("");
        B6.setText("");
        B7.setText("");
        B8.setText("");
        B9.setText("");

        rotate(B1);
        rotate(B2);
        rotate(B3);
        rotate(B4);
        rotate(B5);
        rotate(B6);
        rotate(B7);
        rotate(B8);
        rotate(B9);


    }

    public void gotoHome(View view) {
        //Intent to the Launcher activity

        startActivity(new Intent(SinglePlayerActivity.this, MainActivity.class));
    }
}
