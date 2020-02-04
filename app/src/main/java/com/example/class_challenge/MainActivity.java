package com.example.class_challenge;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator bottomToTop, topToBottom,lftToRgt,rgtToLft;
    private View view;
    private float scrnHeight;
    private float scrnWidth;
    private  AnimatorSet animatorSet;//required to set the sequence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        final float hieght = point.y; // screen height
        scrnHeight = hieght-250;
        final float width = point.x; // screen width
        scrnWidth = width-60;

       initUI();

    animateTopBottom(10000);

}


    void animateTopBottom(long i){

        topToBottom = ObjectAnimator.ofFloat(view,"translationY",0f, scrnHeight)
                .setDuration(i);
        bottomToTop = ObjectAnimator.ofFloat(view,"translationY", scrnHeight,0f )
                .setDuration(i);
        animatorSet.play(topToBottom).before(bottomToTop);
        animatorSet.start();

    }


    void animateLeftRight(){

        lftToRgt = ObjectAnimator.ofFloat(view,"translationX",0f,scrnWidth )
                .setDuration(10000);
        rgtToLft = ObjectAnimator.ofFloat(view,"translationX",scrnWidth,0f )
                .setDuration(10000);

        animatorSet.play( lftToRgt ).before( topToBottom );
        animatorSet.start();
    }

    void initUI(){
        view = (View) findViewById(R.id.widgetView);
        animatorSet = new AnimatorSet();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatorSet.end();
                view.setBackgroundColor(Color.RED);
                animateLeftRight();
            }
        });
    }


}