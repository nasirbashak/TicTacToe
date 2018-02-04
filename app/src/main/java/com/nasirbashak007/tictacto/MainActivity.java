package com.nasirbashak007.tictacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView name;
    Button start, exit, about;
    Animation topToBottom, bottomToTop, leftToRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView) findViewById(R.id.text_name);
        start = (Button) findViewById(R.id.button_start);
        exit = (Button) findViewById(R.id.button_exit);
        about = (Button) findViewById(R.id.button_about);

        topToBottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        start.startAnimation(topToBottom);

        bottomToTop = AnimationUtils.loadAnimation(this, R.anim.botton_to_top);
        exit.startAnimation(bottomToTop);

        leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right_linear);
        about.startAnimation(leftToRight);

    }

    public void startTheActivity(View view) {

        startActivity(new Intent(this, SelectionActivity.class));
    }

    public void closeAllTheActivities(View view) {
        finishAffinity();
    }

    public void diplaytext(View view) {
        String text = "Nasir Basha K";
        name.setText(text);
    }
}
