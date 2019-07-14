package com.nasirbashak007.tictacto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button B1, B2, B3, B4, B5, B6, B7, B8, B9;
    int clicks = 0;
    TextView T1, T2, T3;
    Button Continue, Home;
    int P1, P2, Draw;
    String MODE;
    Boolean flag = false;
    Button modeButton;

    private char[][] boardMatrix = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    String board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

        Bundle bundle = getIntent().getExtras();
        MODE = bundle.getString("two").toString();


        Toast.makeText(getApplicationContext(), "2 Players Mode", Toast.LENGTH_SHORT).show();


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

                //checkForVictory();

                rotate(B1);

                if (clicks % 2 == 0) {
                    B1.setText("O");
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[0][0] = 'O';
                } else {
                    B1.setText("X");
                    B1.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[0][0] = 'X';
                }
                B1.setEnabled(false);
                checkForVictory();
                //buildTheBoard(B1);

                // Toast.makeText(getApplicationContext(), "1 " + boardMatrix[0][0], Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button2: {
                ++clicks;

                //checkForVictory();

                rotate(B2);
                if (clicks % 2 == 0) {
                    B2.setText("O");
                    B2.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[0][1] = 'O';
                } else {
                    B2.setText("X");
                    B2.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[0][1] = 'X';
                }
                B2.setEnabled(false);
                checkForVictory();
                //buildTheBoard(B2);

                // Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button3: {
                ++clicks;

                //checkForVictory();
                rotate(B3);

                if (clicks % 2 == 0) {
                    B3.setText("O");
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[0][2] = 'O';
                } else {
                    B3.setText("X");
                    B3.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[0][2] = 'X';
                }
                B3.setEnabled(false);
                checkForVictory();
                //buildTheBoard(B3);
                // Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button4: {
                ++clicks;

                //checkForVictory();
                rotate(B4);

                if (clicks % 2 == 0) {
                    B4.setText("O");
                    B4.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[1][0] = 'O';
                } else {
                    B4.setText("X");
                    B4.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[1][0] = 'X';
                }
                B4.setEnabled(false);
                checkForVictory();
                // buildTheBoard(B4);
                // Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button5: {
                ++clicks;

                //checkForVictory();
                rotate(B5);

                if (clicks % 2 == 0) {
                    B5.setText("O");
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[1][1] = 'O';
                } else {
                    B5.setText("X");
                    B5.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[1][1] = 'X';
                }
                B5.setEnabled(false);
                checkForVictory();
                //buildTheBoard(B5);
                //   Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button6: {
                ++clicks;

                //checkForVictory();
                rotate(B6);

                if (clicks % 2 == 0) {
                    B6.setText("O");
                    B6.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[1][2] = 'O';
                } else {
                    B6.setText("X");
                    B6.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[1][2] = 'X';
                }
                B6.setEnabled(false);
                checkForVictory();
                // buildTheBoard(B6);
                //  Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button7: {
                ++clicks;

                //checkForVictory();
                rotate(B7);

                if (clicks % 2 == 0) {
                    B7.setText("O");
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[2][0] = 'O';
                } else {
                    B7.setText("X");
                    B7.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[2][0] = 'X';
                }
                B7.setEnabled(false);
                checkForVictory();
                //buildTheBoard(B7);
                //  Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button8: {
                ++clicks;

                //checkForVictory();
                rotate(B8);

                if (clicks % 2 == 0) {
                    B8.setText("O");
                    B8.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[2][1] = 'O';
                } else {
                    B8.setText("X");
                    B8.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[2][1] = 'X';
                }
                B8.setEnabled(false);
                checkForVictory();
                // buildTheBoard(B8);
                //   Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button9: {
                ++clicks;

                //checkForVictory();
                rotate(B9);

                if (clicks % 2 == 0) {
                    B9.setText("O");
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    boardMatrix[2][2] = 'O';
                } else {
                    B9.setText("X");
                    B9.setTextColor(Color.parseColor("#0b48d8"));
                    boardMatrix[2][2] = 'X';
                }
                B9.setEnabled(false);
                checkForVictory();
                // buildTheBoard(B9);
                //   Toast.makeText(getApplicationContext(), "9", Toast.LENGTH_SHORT).show();

            }
            break;

        }


        if (clicks >= 9 && !flag) {
            Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_LONG).show();
            Draw++;
            T2.setText("Draw : " + Draw);
            buttonsStatus(false);
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void refreshTheBoard(View view) {

        clicks = 0;
        flag = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardMatrix[i][j] = '1';
            }
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

        startActivity(new Intent(Main2Activity.this, MainActivity.class));
    }
}
