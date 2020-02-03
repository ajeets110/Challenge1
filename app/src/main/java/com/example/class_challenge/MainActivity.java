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
    private float screenHieght;
    private float screenWidth;
    private  AnimatorSet animatorSet;//required to set the sequence
    Button tenX, twoX,halfX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        final float hieght = point.y; // screen height
        screenHieght = hieght-250;
        final float width = point.x; // screen width
        screenWidth = width-60;

       initializeUI();

    animateTopBottom(10000);




}





    void initializeUI(){
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

    void animateTopBottom(long i){
        // translationX to move object along y axis
        // next values are position value

        topToBottom = ObjectAnimator.ofFloat(view,"translationY",0f, screenHieght)
                .setDuration(i); // top to bottom
        bottomToTop = ObjectAnimator.ofFloat(view,"translationY", screenHieght,0f )
                .setDuration(i); // bottom to top
        animatorSet.play(topToBottom).before(bottomToTop);
        animatorSet.start(); // play the animation

    }


    void animateLeftRight(){
        // translationX to move object along x axis
        // next values are position value
        lftToRgt = ObjectAnimator.ofFloat(view,"translationX",0f,screenWidth )
                .setDuration(10000); // to animate left to right
        rgtToLft = ObjectAnimator.ofFloat(view,"translationX",screenWidth,0f )
                .setDuration(10000); // to animate right to left

        animatorSet.play( lftToRgt ).before( topToBottom ); // manage sequence
        animatorSet.start(); // play the animation
    }
}