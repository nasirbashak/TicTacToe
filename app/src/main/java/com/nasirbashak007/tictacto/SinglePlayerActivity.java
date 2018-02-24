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
    int Mode, Xpos, Ypos;
    Boolean flag = false;
    Random r = new Random();
    int[] buttonClicks = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    Button modeButton;

    private char[][] boardMatrix = {{'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        init();

        Bundle bundle = getIntent().getExtras();
        MODE = bundle.getString("two").toString();
        Mode = Integer.parseInt(MODE);
        switch (Mode) {
            case 1:
                modeButton.setText("EASY");
                Toast.makeText(getApplicationContext(), MODE + " Easy", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                modeButton.setText("MEDIUM");
                Toast.makeText(getApplicationContext(), MODE + " Medium", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                modeButton.setText("HARD");
                Toast.makeText(getApplicationContext(), MODE + " Hard", Toast.LENGTH_SHORT).show();
                break;
        }

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

        modeButton = (Button) findViewById(R.id.buttonMode);

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B1);
                        }
                    } else if (Mode == 3) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B1);
                            if (clicks < 5) {
                                computer(B1);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B1);
                                }
                            }
                        }

                    }//End of Mode 3
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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B2);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B2);
                            if (clicks < 5) {
                                computer(B2);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B2);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {

                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B3);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B3);
                            if (clicks < 5) {
                                computer(B3);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B3);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B4);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B4);
                            if (clicks < 5) {
                                computer(B4);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B4);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B5);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            // computer(B5);
                            if (clicks < 5) {
                                computer(B5);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B5);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B6);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B6);
                            if (clicks < 5) {
                                computer(B6);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B6);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B7);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B7);
                            if (clicks < 5) {
                                computer(B7);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B7);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B8);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B8);
                            if (clicks < 5) {
                                computer(B8);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B8);
                                }
                            }
                        }
                    }

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
                    if (Mode == 1) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer();
                        }
                    } else if (Mode == 2) {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            computer(B9);
                        }
                    } else {
                        if (boardMatrix[1][1] == '5') {
                            ++clicks;
                            B5.setText("O");
                            B5.setEnabled(false);
                            boardMatrix[1][1] = 'O';
                            buttonClicks[4] = 1;
                            B5.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B5);
                        } else {
                            //computer(B9);
                            if (clicks < 5) {
                                computer(B9);
                            } else {
                                if (!testForOPattern()) {
                                    computer(B9);
                                }
                            }
                        }
                    }

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

    //this is for the medium
    public int gen2(int n1, int n2, int n3) {

        int num = r.nextInt(n3 - n1 + 1) + n1;
        if (num == n1) {
            return num;
        } else if (num == n2) {
            return num;
        } else if (num == n3) {
            return num;
        } else {
            return gen2(n1, n2, n3);
        }

    }

    public int ForFifthCase() {

        int num = r.nextInt(9) + 1;
        if (num == 1 || num == 3 || num == 7 || num == 9) {
            return num;
        } else {
            return ForFifthCase();
        }
    }


    public int generate(int n) {
        int num = 0;

        switch (n) {

            case 1: {
                num = gen2(3, 7, 9);
            }
            break;

            case 2: {
                num = gen2(7, 8, 9);
            }
            break;

            case 3: {
                num = gen2(1, 7, 9);

            }
            break;

            case 4: {

                num = gen2(3, 6, 9);

            }
            break;

            case 5: {

                num = ForFifthCase();

            }
            break;

            case 6: {
                num = gen2(1, 4, 7);

            }
            break;

            case 7: {
                num = gen2(1, 3, 9);

            }
            break;

            case 8: {
                num = gen2(1, 2, 3);

            }
            break;

            case 9: {
                num = gen2(1, 3, 7);
            }
            break;

        }


        return num;
    }


    public boolean testForOPattern() {
        // Toast.makeText(getApplicationContext(), "TestForOPattern", Toast.LENGTH_SHORT).show();

        if ((boardMatrix[0][0] == boardMatrix[0][1]) && (boardMatrix[0][0] == 'O') && boardMatrix[0][2] == '3') { // 1 -> 2 out 3

            //B3
            ++clicks;
            B3.setText("O");
            B3.setEnabled(false);
            boardMatrix[0][2] = 'O';
            buttonClicks[2] = 1;
            B3.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B3);
            return true;

        } else if ((boardMatrix[0][0] == boardMatrix[0][2]) && (boardMatrix[0][0] == 'O') && boardMatrix[0][1] == '2') { // 1 -> 3 out 2

            //B2
            ++clicks;
            B2.setText("O");
            B2.setEnabled(false);
            boardMatrix[0][1] = 'O';
            buttonClicks[1] = 1;
            B2.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B2);
            return true;

        } else if ((boardMatrix[0][0] == boardMatrix[2][0]) && (boardMatrix[0][0] == 'O') && boardMatrix[1][0] == '4') { // 1 -> 7 out 4

            //B4
            ++clicks;
            B4.setText("O");
            B4.setEnabled(false);
            boardMatrix[1][0] = 'O';
            buttonClicks[3] = 1;
            B4.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B4);
            return true;

        } else if ((boardMatrix[0][0] == boardMatrix[1][0]) && (boardMatrix[0][0] == 'O') && boardMatrix[2][0] == '7') { // 1 -> 4 out 7

            //B7
            ++clicks;
            B7.setText("O");
            B7.setEnabled(false);
            boardMatrix[2][0] = 'O';
            buttonClicks[6] = 1;
            B7.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B7);
            return true;

        } else if ((boardMatrix[0][0] == boardMatrix[2][2]) && (boardMatrix[0][0] == 'O') && boardMatrix[1][1] == '5') { // 1 -> 9 out 5

            //B5
            ++clicks;
            B5.setText("O");
            B5.setEnabled(false);
            boardMatrix[1][1] = 'O';
            buttonClicks[4] = 1;
            B5.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B5);
            return true;

        } else if ((boardMatrix[0][0] == boardMatrix[1][1]) && (boardMatrix[0][0] == 'O') && boardMatrix[2][2] == '9') { // 1 -> 5 out 9

            //B9
            ++clicks;
            B9.setText("O");
            B9.setEnabled(false);
            boardMatrix[2][2] = 'O';
            buttonClicks[8] = 1;
            B9.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B9);
            return true;

        } else if ((boardMatrix[0][1] == boardMatrix[0][0]) && (boardMatrix[0][1] == 'O') && boardMatrix[0][2] == '3') { // 2 -> 1 out 3

            //B3
            ++clicks;
            B3.setText("O");
            B3.setEnabled(false);
            boardMatrix[0][2] = 'O';
            buttonClicks[2] = 1;
            B3.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B3);
            return true;

        } else if ((boardMatrix[0][1] == boardMatrix[0][2]) && (boardMatrix[0][1] == 'O') && boardMatrix[0][0] == '1') { //2->3 out 1

            //B1
            ++clicks;
            B1.setText("O");
            B1.setEnabled(false);
            boardMatrix[0][0] = 'O';
            buttonClicks[0] = 1;
            B1.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B1);
            return true;

        } else if ((boardMatrix[0][1] == boardMatrix[1][1]) && (boardMatrix[0][1] == 'O') && boardMatrix[2][1] == '8') { //2->5 out 8

            //B8
            ++clicks;
            B8.setText("O");
            B8.setEnabled(false);
            boardMatrix[2][1] = 'O';
            buttonClicks[7] = 1;
            B8.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B8);
            return true;

        } else if ((boardMatrix[0][1] == boardMatrix[2][1]) && (boardMatrix[0][1] == 'O') && boardMatrix[1][1] == '5') { //2->8 out 5

            //B5
            ++clicks;
            B5.setText("O");
            B5.setEnabled(false);
            boardMatrix[1][1] = 'O';
            buttonClicks[4] = 1;
            B5.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B5);
            return true;

        } else if ((boardMatrix[0][2] == boardMatrix[0][0]) && (boardMatrix[0][2] == 'O') && boardMatrix[0][1] == '2') { //3->1 out 2

            //B2
            ++clicks;
            B2.setText("O");
            B2.setEnabled(false);
            boardMatrix[0][1] = 'O';
            buttonClicks[1] = 1;
            B2.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B2);
            return true;

        } else if ((boardMatrix[0][2] == boardMatrix[0][1]) && (boardMatrix[0][2] == 'O') && boardMatrix[0][0] == '1') { //3->2 out 1

            //B1
            ++clicks;
            B1.setText("O");
            B1.setEnabled(false);
            boardMatrix[0][0] = 'O';
            buttonClicks[0] = 1;
            B1.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B1);
            return true;

        } else if ((boardMatrix[0][2] == boardMatrix[1][1]) && (boardMatrix[0][2] == 'O') && boardMatrix[2][0] == '7') { //3->5 out 7

            //B7
            ++clicks;
            B7.setText("O");
            B7.setEnabled(false);
            boardMatrix[2][0] = 'O';
            buttonClicks[6] = 1;
            B7.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B7);
            return true;

        } else if ((boardMatrix[0][2] == boardMatrix[2][0]) && (boardMatrix[0][2] == 'O') && boardMatrix[1][1] == '5') { //3->7 out 5

            //B5
            ++clicks;
            B5.setText("O");
            B5.setEnabled(false);
            boardMatrix[1][1] = 'O';
            buttonClicks[4] = 1;
            B5.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B5);
            return true;

        } else if ((boardMatrix[0][2] == boardMatrix[1][2]) && (boardMatrix[0][2] == 'O') && boardMatrix[2][2] == '9') { //3->6 out 9

            //B9
            ++clicks;
            B9.setText("O");
            B9.setEnabled(false);
            boardMatrix[2][2] = 'O';
            buttonClicks[8] = 1;
            B9.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B9);
            return true;

        } else if ((boardMatrix[0][2] == boardMatrix[2][2]) && (boardMatrix[0][2] == 'O') && boardMatrix[1][2] == '6') { //3->9 out 6

            //B6
            ++clicks;
            B6.setText("O");
            B6.setEnabled(false);
            boardMatrix[1][2] = 'O';
            buttonClicks[5] = 1;
            B6.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B6);
            return true;

        } else if ((boardMatrix[1][0] == boardMatrix[0][0]) && (boardMatrix[1][0] == 'O') && (boardMatrix[2][0] == '7')) { // 4-> 1 out 7

            //B7
            ++clicks;
            B7.setText("O");
            B7.setEnabled(false);
            boardMatrix[2][0] = 'O';
            buttonClicks[6] = 1;
            B7.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B7);
            return true;

        } else if ((boardMatrix[1][0] == boardMatrix[2][0]) && (boardMatrix[1][0] == 'O') && (boardMatrix[0][0] == '1')) { // 4-> 7 out 1

            //B1
            ++clicks;
            B1.setText("O");
            B1.setEnabled(false);
            boardMatrix[0][0] = 'O';
            buttonClicks[0] = 1;
            B1.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B1);
            return true;

        } else if ((boardMatrix[1][0] == boardMatrix[1][1]) && (boardMatrix[1][0] == 'O') && (boardMatrix[1][2] == '6')) { // 4-> 5 out 6

            //B6
            ++clicks;
            B6.setText("O");
            B6.setEnabled(false);
            boardMatrix[1][2] = 'O';
            buttonClicks[5] = 1;
            B6.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B6);
            return true;

        } else if ((boardMatrix[1][0] == boardMatrix[1][2]) && (boardMatrix[1][0] == 'O') && (boardMatrix[1][1] == '5')) { // 4-> 6 out 5

            //B5
            ++clicks;
            B5.setText("O");
            B5.setEnabled(false);
            boardMatrix[1][1] = 'O';
            buttonClicks[4] = 1;
            B5.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B5);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[0][0]) && (boardMatrix[1][1] == 'O') && (boardMatrix[2][2] == '9')) { // 5->1 out 9

            //B9
            ++clicks;
            B9.setText("O");
            B9.setEnabled(false);
            boardMatrix[2][2] = 'O';
            buttonClicks[8] = 1;
            B9.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B9);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[2][2]) && (boardMatrix[1][1] == 'O') && (boardMatrix[0][0] == '1')) { // 5->9 out 1

            //B1
            ++clicks;
            B1.setText("O");
            B1.setEnabled(false);
            boardMatrix[0][0] = 'O';
            buttonClicks[0] = 1;
            B1.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B1);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[0][1]) && (boardMatrix[1][1] == 'O') && (boardMatrix[2][1] == '8')) { // 5->2 out 8

            //B8
            ++clicks;
            B8.setText("O");
            B8.setEnabled(false);
            boardMatrix[2][1] = 'O';
            buttonClicks[7] = 1;
            B8.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B8);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[2][1]) && (boardMatrix[1][1] == 'O') && (boardMatrix[0][1] == '2')) { // 5->8 out 2

            //B2
            ++clicks;
            B2.setText("O");
            B2.setEnabled(false);
            boardMatrix[0][1] = 'O';
            buttonClicks[1] = 1;
            B2.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B2);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[0][2]) && (boardMatrix[1][1] == 'O') && (boardMatrix[2][0] == '7')) { // 5->3 out 7

            //B7
            ++clicks;
            B7.setText("O");
            B7.setEnabled(false);
            boardMatrix[2][0] = 'O';
            buttonClicks[6] = 1;
            B7.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B7);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[2][0]) && (boardMatrix[1][1] == 'O') && (boardMatrix[0][2] == '3')) { // 5->7 out 3

            //B3
            ++clicks;
            B3.setText("O");
            B3.setEnabled(false);
            boardMatrix[0][2] = 'O';
            buttonClicks[2] = 1;
            B3.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B3);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[1][0]) && (boardMatrix[1][1] == 'O') && (boardMatrix[1][2] == '6')) { // 5->4 out 6

            //B6
            ++clicks;
            B6.setText("O");
            B6.setEnabled(false);
            boardMatrix[1][2] = 'O';
            buttonClicks[5] = 1;
            B6.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B6);
            return true;

        } else if ((boardMatrix[1][1] == boardMatrix[1][2]) && (boardMatrix[1][1] == 'O') && (boardMatrix[1][0] == '4')) { // 5->6 out 4

            //B4
            ++clicks;
            B4.setText("O");
            B4.setEnabled(false);
            boardMatrix[1][0] = 'O';
            buttonClicks[3] = 1;
            B4.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B4);
            return true;

        } else if ((boardMatrix[1][2] == boardMatrix[2][2]) && (boardMatrix[1][2] == 'O') && (boardMatrix[0][2] == '3')) { // 6 -> 9 out 3

            //B3
            ++clicks;
            B3.setText("O");
            B3.setEnabled(false);
            boardMatrix[0][2] = 'O';
            buttonClicks[2] = 1;
            B3.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B3);
            return true;

        } else if ((boardMatrix[2][0] == boardMatrix[2][1]) && (boardMatrix[2][0] == 'O') && (boardMatrix[2][2] == '9')) { // 7 -> 8 out 9

            //B9
            ++clicks;
            B9.setText("O");
            B9.setEnabled(false);
            boardMatrix[2][2] = 'O';
            buttonClicks[8] = 1;
            B9.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B9);
            return true;

        } else if ((boardMatrix[2][0] == boardMatrix[2][2]) && (boardMatrix[2][0] == 'O') && (boardMatrix[2][1] == '8')) { // 7 -> 9 out 8

            //B8
            ++clicks;
            B8.setText("O");
            B8.setEnabled(false);
            boardMatrix[2][1] = 'O';
            buttonClicks[7] = 1;
            B8.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B8);
            return true;

        } else if ((boardMatrix[2][1] == boardMatrix[2][2]) && (boardMatrix[2][1] == 'O') && (boardMatrix[2][0] == '7')) { // 8 -> 9 out 7

            //B7
            ++clicks;
            B7.setText("O");
            B7.setEnabled(false);
            boardMatrix[2][0] = 'O';
            buttonClicks[6] = 1;
            B7.setTextColor(Color.parseColor("#FF8C00"));
            rotate(B7);
            return true;

        }


        return false;
    }


    //To Search forom the clicked button
    public void SearchAndPlace(Button b) {

        switch (b.getId()) {

            case R.id.button1: {
                // Toast.makeText(getApplicationContext(), "Entered Button1", Toast.LENGTH_SHORT).show();


                if ((boardMatrix[0][0] == boardMatrix[0][1]) && (boardMatrix[0][0] == 'X') && boardMatrix[0][2] == '3') { // 1 -> 2 out 3

                    //if (boardMatrix[0][2] == '3') {

                    //B3
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);
                    //} else {
                    //  computer();
                    //}

                } else if ((boardMatrix[0][0] == boardMatrix[0][2]) && (boardMatrix[0][0] == 'X') && boardMatrix[0][1] == '2') { // 1 -> 3 out 2

                    if (boardMatrix[0][1] == '2') {
                        //B2
                        ++clicks;
                        B2.setText("O");
                        B2.setEnabled(false);
                        boardMatrix[0][1] = 'O';
                        buttonClicks[1] = 1;
                        B2.setTextColor(Color.parseColor("#FF8C00"));
                        rotate(B2);

                    } else {
                        computer();
                    }

                } else if ((boardMatrix[0][0] == boardMatrix[2][0]) && (boardMatrix[0][0] == 'X') && boardMatrix[1][0] == '4') { // 1 -> 7 out 4

                    if (boardMatrix[1][0] == '4') {
                        //B4
                        ++clicks;
                        B4.setText("O");
                        B4.setEnabled(false);
                        boardMatrix[1][0] = 'O';
                        buttonClicks[3] = 1;
                        B4.setTextColor(Color.parseColor("#FF8C00"));
                        rotate(B4);


                    } else {
                        computer();
                    }


                } else if ((boardMatrix[0][0] == boardMatrix[1][0]) && (boardMatrix[0][0] == 'X') && boardMatrix[2][0] == '7') { // 1 -> 4 out 7

                    if (boardMatrix[2][0] == '7') {
                        //B7
                        ++clicks;
                        B7.setText("O");
                        B7.setEnabled(false);
                        boardMatrix[2][0] = 'O';
                        buttonClicks[6] = 1;
                        B7.setTextColor(Color.parseColor("#FF8C00"));
                        rotate(B7);

                    } else {
                        computer();
                    }

                } else if ((boardMatrix[0][0] == boardMatrix[2][2]) && (boardMatrix[0][0] == 'X') && boardMatrix[1][1] == '5') { // 1 -> 9 out 5

                    if (boardMatrix[1][1] == '5') {
                        //B5
                        ++clicks;
                        B5.setText("O");
                        B5.setEnabled(false);
                        boardMatrix[1][1] = 'O';
                        buttonClicks[4] = 1;
                        B5.setTextColor(Color.parseColor("#FF8C00"));
                        rotate(B5);

                    } else {
                        computer();
                    }

                } else if ((boardMatrix[0][0] == boardMatrix[1][1]) && (boardMatrix[0][0] == 'X') && boardMatrix[2][2] == '9') { // 1 -> 5 out 9

                    if (boardMatrix[2][2] == '9') {
                        //B9
                        ++clicks;
                        B9.setText("O");
                        B9.setEnabled(false);
                        boardMatrix[2][2] = 'O';
                        buttonClicks[8] = 1;
                        B9.setTextColor(Color.parseColor("#FF8C00"));
                        rotate(B9);

                    } else {
                        computer();
                    }

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //  Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }

                }

            }
            break;

            case R.id.button2: {
                // Toast.makeText(getApplicationContext(), "Entered Button2", Toast.LENGTH_SHORT).show();
                if ((boardMatrix[0][1] == boardMatrix[0][0]) && (boardMatrix[0][1] == 'X') && boardMatrix[0][2] == '3') { // 2 -> 1 out 3
                    //B3
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);

                } else if ((boardMatrix[0][1] == boardMatrix[0][2]) && (boardMatrix[0][1] == 'X') && boardMatrix[0][0] == '1') { //2->3 out 1
                    //B1
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);

                } else if ((boardMatrix[0][1] == boardMatrix[1][1]) && (boardMatrix[0][1] == 'X') && boardMatrix[2][1] == '8') { //2->5 out 8
                    //B8
                    ++clicks;
                    B8.setText("O");
                    B8.setEnabled(false);
                    boardMatrix[2][1] = 'O';
                    buttonClicks[7] = 1;
                    B8.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B8);

                } else if ((boardMatrix[0][1] == boardMatrix[2][1]) && (boardMatrix[0][1] == 'X') && boardMatrix[1][1] == '5') { //2->8 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            // Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }


            }
            break;

            case R.id.button3: {
                //  Toast.makeText(getApplicationContext(), "Entered Button3", Toast.LENGTH_SHORT).show();
                if ((boardMatrix[0][2] == boardMatrix[0][0]) && (boardMatrix[0][2] == 'X') && boardMatrix[0][1] == '2') { //3->1 out 2
                    //B2
                    ++clicks;
                    B2.setText("O");
                    B2.setEnabled(false);
                    boardMatrix[0][1] = 'O';
                    buttonClicks[1] = 1;
                    B2.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B2);

                } else if ((boardMatrix[0][2] == boardMatrix[0][1]) && (boardMatrix[0][2] == 'X') && boardMatrix[0][0] == '1') { //3->2 out 1
                    //B1
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);

                } else if ((boardMatrix[0][2] == boardMatrix[1][1]) && (boardMatrix[0][2] == 'X') && boardMatrix[2][0] == '7') { //3->5 out 7
                    //B7
                    ++clicks;
                    B7.setText("O");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'O';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B7);


                } else if ((boardMatrix[0][2] == boardMatrix[2][0]) && (boardMatrix[0][2] == 'X') && boardMatrix[1][1] == '5') { //3->7 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else if ((boardMatrix[0][2] == boardMatrix[1][2]) && (boardMatrix[0][2] == 'X') && boardMatrix[2][2] == '9') { //3->6 out 9
                    //B9
                    ++clicks;
                    B9.setText("O");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'O';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B9);

                } else if ((boardMatrix[0][2] == boardMatrix[2][2]) && (boardMatrix[0][2] == 'X') && boardMatrix[1][2] == '6') { //3->9 out 6
                    //B6
                    ++clicks;
                    B6.setText("O");
                    B6.setEnabled(false);
                    boardMatrix[1][2] = 'O';
                    buttonClicks[5] = 1;
                    B6.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B6);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //  Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

            case R.id.button4: {
                // Toast.makeText(getApplicationContext(), "Entered Button4", Toast.LENGTH_SHORT).show();

                if ((boardMatrix[1][0] == boardMatrix[0][0]) && (boardMatrix[1][0] == 'X') && (boardMatrix[2][0] == '7')) { // 4-> 1 out 7
                    //B7
                    ++clicks;
                    B7.setText("O");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'O';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B7);

                } else if ((boardMatrix[1][0] == boardMatrix[2][0]) && (boardMatrix[1][0] == 'X') && (boardMatrix[0][0] == '1')) { // 4-> 7 out 1
                    //B1
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);

                } else if ((boardMatrix[1][0] == boardMatrix[1][1]) && (boardMatrix[1][0] == 'X') && (boardMatrix[1][2] == '6')) { // 4-> 5 out 6
                    //B6
                    ++clicks;
                    B6.setText("O");
                    B6.setEnabled(false);
                    boardMatrix[1][2] = 'O';
                    buttonClicks[5] = 1;
                    B6.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B6);

                } else if ((boardMatrix[1][0] == boardMatrix[1][2]) && (boardMatrix[1][0] == 'X') && (boardMatrix[1][1] == '5')) { // 4-> 6 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //   Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

            case R.id.button5: {
                // Toast.makeText(getApplicationContext(), "Entered Button5", Toast.LENGTH_SHORT).show();

                if ((boardMatrix[1][1] == boardMatrix[0][0]) && (boardMatrix[1][1] == 'X') && (boardMatrix[2][2] == '9')) { // 5->1 out 9
                    //B9
                    ++clicks;
                    B9.setText("O");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'O';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B9);

                } else if ((boardMatrix[1][1] == boardMatrix[2][2]) && (boardMatrix[1][1] == 'X') && (boardMatrix[0][0] == '1')) { // 5->9 out 1
                    //B1
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);

                } else if ((boardMatrix[1][1] == boardMatrix[0][1]) && (boardMatrix[1][1] == 'X') && (boardMatrix[2][1] == '8')) { // 5->2 out 8
                    //B8
                    ++clicks;
                    B8.setText("O");
                    B8.setEnabled(false);
                    boardMatrix[2][1] = 'O';
                    buttonClicks[7] = 1;
                    B8.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B8);

                } else if ((boardMatrix[1][1] == boardMatrix[2][1]) && (boardMatrix[1][1] == 'X') && (boardMatrix[0][1] == '2')) { // 5->8 out 2
                    //B2
                    ++clicks;
                    B2.setText("O");
                    B2.setEnabled(false);
                    boardMatrix[0][1] = 'O';
                    buttonClicks[1] = 1;
                    B2.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B2);

                } else if ((boardMatrix[1][1] == boardMatrix[0][2]) && (boardMatrix[1][1] == 'X') && (boardMatrix[2][0] == '7')) { // 5->3 out 7
                    //B7
                    ++clicks;
                    B7.setText("O");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'O';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B7);

                } else if ((boardMatrix[1][1] == boardMatrix[2][0]) && (boardMatrix[1][1] == 'X') && (boardMatrix[0][2] == '3')) { // 5->7 out 3
                    //B3
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);

                } else if ((boardMatrix[1][1] == boardMatrix[1][0]) && (boardMatrix[1][1] == 'X') && (boardMatrix[1][2] == '6')) { // 5->4 out 6
                    //B6
                    ++clicks;
                    B6.setText("O");
                    B6.setEnabled(false);
                    boardMatrix[1][2] = 'O';
                    buttonClicks[5] = 1;
                    B6.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B6);

                } else if ((boardMatrix[1][1] == boardMatrix[1][2]) && (boardMatrix[1][1] == 'X') && (boardMatrix[1][0] == '4')) { // 5->6 out 4
                    //B4
                    ++clicks;
                    B4.setText("O");
                    B4.setEnabled(false);
                    boardMatrix[1][0] = 'O';
                    buttonClicks[3] = 1;
                    B4.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B4);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //  Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

            case R.id.button6: {
                //  Toast.makeText(getApplicationContext(), "Entered Button6", Toast.LENGTH_SHORT).show();

                if ((boardMatrix[1][2] == boardMatrix[0][2]) && (boardMatrix[1][2] == 'X') && (boardMatrix[2][2] == '9')) { // 6 -> 3 out 9
                    //B9
                    ++clicks;
                    B9.setText("O");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'O';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B9);

                } else if ((boardMatrix[1][2] == boardMatrix[2][2]) && (boardMatrix[1][2] == 'X') && (boardMatrix[0][2] == '3')) { // 6 -> 9 out 3
                    //B3
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);

                } else if ((boardMatrix[1][2] == boardMatrix[1][0]) && (boardMatrix[1][2] == 'X') && (boardMatrix[1][1] == '5')) { // 6 -> 4 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else if ((boardMatrix[1][2] == boardMatrix[1][1]) && (boardMatrix[1][2] == 'X') && (boardMatrix[1][0] == '4')) { // 6 -> 5 out 4
                    //B4
                    ++clicks;
                    B4.setText("O");
                    B4.setEnabled(false);
                    boardMatrix[1][0] = 'O';
                    buttonClicks[3] = 1;
                    B4.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B4);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //  Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

            case R.id.button7: {
                // Toast.makeText(getApplicationContext(), "Entered Button7", Toast.LENGTH_SHORT).show();

                if ((boardMatrix[2][0] == boardMatrix[0][0]) && (boardMatrix[2][0] == 'X') && (boardMatrix[1][0] == '4')) { // 7 -> 1 out 4
                    //B4
                    ++clicks;
                    B4.setText("O");
                    B4.setEnabled(false);
                    boardMatrix[1][0] = 'O';
                    buttonClicks[3] = 1;
                    B4.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B4);

                } else if ((boardMatrix[2][0] == boardMatrix[1][0]) && (boardMatrix[2][0] == 'X') && (boardMatrix[0][0] == '1')) { // 7 -> 4 out 1
                    //B1
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);

                } else if ((boardMatrix[2][0] == boardMatrix[0][2]) && (boardMatrix[2][0] == 'X') && (boardMatrix[1][1] == '5')) { // 7 -> 3 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else if ((boardMatrix[2][0] == boardMatrix[1][1]) && (boardMatrix[2][0] == 'X') && (boardMatrix[0][2] == '3')) { // 7 -> 5 out 3
                    //B3
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);

                } else if ((boardMatrix[2][0] == boardMatrix[2][1]) && (boardMatrix[2][0] == 'X') && (boardMatrix[2][2] == '9')) { // 7 -> 8 out 9
                    //B9
                    ++clicks;
                    B9.setText("O");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'O';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B9);

                } else if ((boardMatrix[2][0] == boardMatrix[2][2]) && (boardMatrix[2][0] == 'X') && (boardMatrix[2][1] == '8')) { // 7 -> 9 out 8
                    //B8
                    ++clicks;
                    B8.setText("O");
                    B8.setEnabled(false);
                    boardMatrix[2][1] = 'O';
                    buttonClicks[7] = 1;
                    B8.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B8);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //   Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

            case R.id.button8: {
                //  Toast.makeText(getApplicationContext(), "Entered Button8", Toast.LENGTH_SHORT).show();

                if ((boardMatrix[2][1] == boardMatrix[2][0]) && (boardMatrix[2][1] == 'X') && (boardMatrix[2][2] == '9')) { // 8 -> 7 out 9
                    //B9
                    ++clicks;
                    B9.setText("O");
                    B9.setEnabled(false);
                    boardMatrix[2][2] = 'O';
                    buttonClicks[8] = 1;
                    B9.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B9);

                } else if ((boardMatrix[2][1] == boardMatrix[2][2]) && (boardMatrix[2][1] == 'X') && (boardMatrix[2][0] == '7')) { // 8 -> 9 out 7
                    //B7
                    ++clicks;
                    B7.setText("O");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'O';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B7);

                } else if ((boardMatrix[2][1] == boardMatrix[0][1]) && (boardMatrix[2][1] == 'X') && (boardMatrix[1][1] == '5')) { // 8 -> 2 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else if ((boardMatrix[2][1] == boardMatrix[1][1]) && (boardMatrix[2][1] == 'X') && (boardMatrix[0][1] == '2')) { // 8 -> 5 out 2
                    //B2
                    ++clicks;
                    B2.setText("O");
                    B2.setEnabled(false);
                    boardMatrix[0][1] = 'O';
                    buttonClicks[1] = 1;
                    B2.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B2);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //  Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

            case R.id.button9: {
                //  Toast.makeText(getApplicationContext(), "Entered Button9", Toast.LENGTH_SHORT).show();

                if ((boardMatrix[2][2] == boardMatrix[0][0]) && (boardMatrix[2][2] == 'X') && (boardMatrix[1][1] == '5')) { // 9 -> 1 out 5
                    //B5
                    ++clicks;
                    B5.setText("O");
                    B5.setEnabled(false);
                    boardMatrix[1][1] = 'O';
                    buttonClicks[4] = 1;
                    B5.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B5);

                } else if ((boardMatrix[2][2] == boardMatrix[1][1]) && (boardMatrix[2][2] == 'X') && (boardMatrix[0][0] == '1')) { // 9 -> 5 out 1
                    //B1
                    ++clicks;
                    B1.setText("O");
                    B1.setEnabled(false);
                    boardMatrix[0][0] = 'O';
                    buttonClicks[0] = 1;
                    B1.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B1);

                } else if ((boardMatrix[2][2] == boardMatrix[0][2]) && (boardMatrix[2][2] == 'X') && (boardMatrix[1][2] == '6')) { // 9 -> 3 out 6
                    //B6
                    ++clicks;
                    B6.setText("O");
                    B6.setEnabled(false);
                    boardMatrix[1][2] = 'O';
                    buttonClicks[5] = 1;
                    B6.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B6);

                } else if ((boardMatrix[2][2] == boardMatrix[1][2]) && (boardMatrix[2][2] == 'X') && (boardMatrix[0][2] == '3')) { // 9 -> 6 out 3
                    //B3
                    ++clicks;
                    B3.setText("O");
                    B3.setEnabled(false);
                    boardMatrix[0][2] = 'O';
                    buttonClicks[2] = 1;
                    B3.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B3);

                } else if ((boardMatrix[2][2] == boardMatrix[2][0]) && (boardMatrix[2][2] == 'X') && (boardMatrix[2][1] == '8')) { // 9 -> 7 out 8
                    //B8
                    ++clicks;
                    B8.setText("O");
                    B8.setEnabled(false);
                    boardMatrix[2][1] = 'O';
                    buttonClicks[7] = 1;
                    B8.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B8);

                } else if ((boardMatrix[2][2] == boardMatrix[2][1]) && (boardMatrix[2][2] == 'X') && (boardMatrix[2][0] == '7')) { // 9 -> 8 out 7
                    //B7
                    ++clicks;
                    B7.setText("O");
                    B7.setEnabled(false);
                    boardMatrix[2][0] = 'O';
                    buttonClicks[6] = 1;
                    B7.setTextColor(Color.parseColor("#FF8C00"));
                    rotate(B7);

                } else {
                    if (Mode == 3) {
                        if (!testForOPattern()) {
                            //  Toast.makeText(getApplicationContext(), "M 3 Failure of test case", Toast.LENGTH_SHORT).show();
                            computer();
                        }
                    } else {
                        computer();
                    }
                }

            }
            break;

        }


    }


    public void computer(Button b) {

        switch (b.getId()) {

            case R.id.button1: {

                switch (clicks) {

                    case 1: {

                        int num = generate(1);
                        if (num == 3) {
                            B3.setText("O");
                            B3.setEnabled(false);
                            boardMatrix[0][2] = 'O';
                            buttonClicks[2] = 1;
                            B3.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B3);
                        } else if (num == 7) {
                            B7.setText("O");
                            B7.setEnabled(false);
                            boardMatrix[2][0] = 'O';
                            buttonClicks[6] = 1;
                            B7.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B7);
                        } else if (num == 9) {
                            B9.setText("O");
                            B9.setEnabled(false);
                            boardMatrix[2][2] = 'O';
                            buttonClicks[8] = 1;
                            B9.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B9);
                        }
                        clicks++;

                    }
                    break;

                    case 3: {

                        SearchAndPlace(B1);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B1);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B1);

                    }
                    break;
                }
            }
            break;

            case R.id.button2: {
                switch (clicks) {

                    case 1: {


                        int num = generate(2);
                        if (num == 7) {
                            B7.setText("O");
                            B7.setEnabled(false);
                            boardMatrix[2][0] = 'O';
                            buttonClicks[6] = 1;
                            B7.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B7);
                        } else if (num == 8) {
                            B8.setText("O");
                            B8.setEnabled(false);
                            boardMatrix[2][1] = 'O';
                            buttonClicks[7] = 1;
                            B8.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B8);
                        } else if (num == 9) {
                            B9.setText("O");
                            B9.setEnabled(false);
                            boardMatrix[2][2] = 'O';
                            buttonClicks[8] = 1;
                            B9.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B9);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B2);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B2);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B2);

                    }
                    break;
                }
            }
            break;

            case R.id.button3: {
                switch (clicks) {

                    case 1: {

                        int num = generate(3);
                        if (num == 1) {
                            B1.setText("O");
                            B1.setEnabled(false);
                            boardMatrix[0][0] = 'O';
                            buttonClicks[0] = 1;
                            B1.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B1);
                        } else if (num == 7) {
                            B7.setText("O");
                            B7.setEnabled(false);
                            boardMatrix[2][0] = 'O';
                            buttonClicks[6] = 1;
                            B7.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B7);
                        } else if (num == 9) {
                            B9.setText("O");
                            B9.setEnabled(false);
                            boardMatrix[2][2] = 'O';
                            buttonClicks[8] = 1;
                            B9.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B9);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B3);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B3);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B3);

                    }
                    break;
                }
            }
            break;

            case R.id.button4: {

                switch (clicks) {

                    case 1: {
                        int num = generate(4);
                        if (num == 3) {
                            B3.setText("O");
                            B3.setEnabled(false);
                            boardMatrix[0][2] = 'O';
                            buttonClicks[2] = 1;
                            B3.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B3);
                        } else if (num == 6) {
                            B6.setText("O");
                            B6.setEnabled(false);
                            boardMatrix[1][2] = 'O';
                            buttonClicks[5] = 1;
                            B6.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B6);
                        } else if (num == 9) {
                            B9.setText("O");
                            B9.setEnabled(false);
                            boardMatrix[2][2] = 'O';
                            buttonClicks[8] = 1;
                            B9.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B9);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B4);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B4);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B4);

                    }
                    break;
                }
            }
            break;
            //code it
            case R.id.button5: {
                switch (clicks) {

                    case 1: {
                        int num = ForFifthCase();
                        if (num == 1) {
                            B1.setText("O");
                            B1.setEnabled(false);
                            boardMatrix[0][0] = 'O';
                            buttonClicks[0] = 1;
                            B1.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B1);
                        } else if (num == 2) {
                            B2.setText("O");
                            B2.setEnabled(false);
                            boardMatrix[0][1] = 'O';
                            buttonClicks[1] = 1;
                            B2.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B2);
                        } else if (num == 3) {
                            B3.setText("O");
                            B3.setEnabled(false);
                            boardMatrix[0][2] = 'O';
                            buttonClicks[2] = 1;
                            B3.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B3);
                        } else if (num == 4) {
                            B4.setText("O");
                            B4.setEnabled(false);
                            boardMatrix[1][0] = 'O';
                            buttonClicks[3] = 1;
                            B4.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B4);
                        } else if (num == 6) {
                            B6.setText("O");
                            B6.setEnabled(false);
                            boardMatrix[1][2] = 'O';
                            buttonClicks[5] = 1;
                            B6.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B6);
                        } else if (num == 7) {
                            B7.setText("O");
                            B7.setEnabled(false);
                            boardMatrix[2][0] = 'O';
                            buttonClicks[6] = 1;
                            B7.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B7);
                        } else if (num == 8) {
                            B8.setText("O");
                            B8.setEnabled(false);
                            boardMatrix[2][1] = 'O';
                            buttonClicks[7] = 1;
                            B8.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B8);
                        } else if (num == 9) {
                            B9.setText("O");
                            B9.setEnabled(false);
                            boardMatrix[2][2] = 'O';
                            buttonClicks[8] = 1;
                            B9.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B9);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B5);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B5);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B5);

                    }
                    break;
                }
            }
            break;

            case R.id.button6: {

                switch (clicks) {

                    case 1: {
                        int num = generate(6);
                        if (num == 1) {
                            B1.setText("O");
                            B1.setEnabled(false);
                            boardMatrix[0][0] = 'O';
                            buttonClicks[0] = 1;
                            B1.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B1);
                        } else if (num == 4) {
                            B4.setText("O");
                            B4.setEnabled(false);
                            boardMatrix[1][0] = 'O';
                            buttonClicks[3] = 1;
                            B4.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B4);
                        } else if (num == 7) {
                            B7.setText("O");
                            B7.setEnabled(false);
                            boardMatrix[2][0] = 'O';
                            buttonClicks[6] = 1;
                            B7.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B7);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B6);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B6);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B6);

                    }
                    break;
                }
            }
            break;

            case R.id.button7: {
                switch (clicks) {

                    case 1: {
                        int num = generate(7);
                        if (num == 1) {
                            B1.setText("O");
                            B1.setEnabled(false);
                            boardMatrix[0][0] = 'O';
                            buttonClicks[0] = 1;
                            B1.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B1);
                        } else if (num == 3) {
                            B3.setText("O");
                            B3.setEnabled(false);
                            boardMatrix[0][2] = 'O';
                            buttonClicks[2] = 1;
                            B3.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B3);
                        } else if (num == 9) {
                            B9.setText("O");
                            B9.setEnabled(false);
                            boardMatrix[2][2] = 'O';
                            buttonClicks[8] = 1;
                            B9.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B9);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B7);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B7);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B7);

                    }
                    break;
                }
            }
            break;

            case R.id.button8: {
                switch (clicks) {

                    case 1: {
                        int num = generate(8);
                        if (num == 1) {
                            B1.setText("O");
                            B1.setEnabled(false);
                            boardMatrix[0][0] = 'O';
                            buttonClicks[0] = 1;
                            B1.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B1);
                        } else if (num == 2) {
                            B2.setText("O");
                            B2.setEnabled(false);
                            boardMatrix[0][1] = 'O';
                            buttonClicks[1] = 1;
                            B2.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B2);
                        } else if (num == 3) {
                            B3.setText("O");
                            B3.setEnabled(false);
                            boardMatrix[0][2] = 'O';
                            buttonClicks[2] = 1;
                            B3.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B3);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B8);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B8);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B8);

                    }
                    break;
                }
            }
            break;

            case R.id.button9: {
                switch (clicks) {

                    case 1: {
                        int num = generate(9);
                        if (num == 1) {
                            B1.setText("O");
                            B1.setEnabled(false);
                            boardMatrix[0][0] = 'O';
                            buttonClicks[0] = 1;
                            B1.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B1);
                        } else if (num == 3) {
                            B3.setText("O");
                            B3.setEnabled(false);
                            boardMatrix[0][2] = 'O';
                            buttonClicks[2] = 1;
                            B3.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B3);
                        } else if (num == 7) {
                            B7.setText("O");
                            B7.setEnabled(false);
                            boardMatrix[2][0] = 'O';
                            buttonClicks[6] = 1;
                            B7.setTextColor(Color.parseColor("#FF8C00"));
                            rotate(B7);
                        }
                        clicks++;
                    }
                    break;

                    case 3: {

                        SearchAndPlace(B9);

                    }
                    break;

                    case 5: {

                        SearchAndPlace(B9);

                    }
                    break;

                    case 7: {

                        SearchAndPlace(B9);

                    }
                    break;
                }
            }
            break;
        }

    }


    private void computer() {
        if (clicks < 9) {
            //Toast.makeText(getApplicationContext(), "computer()", Toast.LENGTH_SHORT).show();
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


    public void refreshTheBoard(View view) {
        //refresh The Board

        clicks = 0;
        flag = false;
        int c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ++c;
                String a = Integer.toString(c);
                boardMatrix[i][j] = a.charAt(0);

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
