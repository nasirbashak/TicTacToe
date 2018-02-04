package com.nasirbashak007.tictacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

public class SelectionActivity extends AppCompatActivity {

    String pass1, pass2;
    TextView T1;
    Button B1;
    Animation leftToright, RightToLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        T1 = (TextView) findViewById(R.id.text_p1);
        B1 = (Button) findViewById(R.id.button_p2);

        leftToright = AnimationUtils.loadAnimation(this, R.anim.left_to_right_linear);
        T1.startAnimation(leftToright);

        RightToLeft = AnimationUtils.loadAnimation(this, R.anim.righht_to_left_linear);
        B1.startAnimation(RightToLeft);


        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);

        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
                //  fc.fold(true);
                fc.animate().setDuration(200);
            }
        });

    }

    public void startTheGame(View view) {
        Intent intent = new Intent(SelectionActivity.this, Main2Activity.class);
        Bundle bundle = new Bundle();
        pass1 = "2";
        bundle.putString("two", pass1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startTheGameEasy(View view) {

        Intent intent = new Intent(SelectionActivity.this, SinglePlayerActivity.class);
        Bundle bundle = new Bundle();
        pass2 = "1E";
        bundle.putString("two", pass2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startTheGameMedium(View view) {
        Intent intent = new Intent(SelectionActivity.this, SinglePlayerActivity.class);
        Bundle bundle = new Bundle();
        pass2 = "1M";
        bundle.putString("two", pass2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startTheGmaeHard(View view) {

        Intent intent = new Intent(SelectionActivity.this, SinglePlayerActivity.class);
        Bundle bundle = new Bundle();
        pass2 = "1H";
        bundle.putString("two", pass2);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
