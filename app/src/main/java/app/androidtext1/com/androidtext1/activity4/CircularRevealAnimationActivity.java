package app.androidtext1.com.androidtext1.activity4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import app.androidtext1.com.androidtext1.R;

public class CircularRevealAnimationActivity extends Activity {
    private ImageView myView;
    private boolean isCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal_animation);
        myView = (ImageView) findViewById(R.id.image);
    }
    public void onClick(View view) {
        if (isCheck){
            enterReveal();
        }else {
            exitReveal();
        }
        isCheck =!isCheck;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void enterReveal(){
        int cx = myView.getMeasuredWidth()/2;
        int cy = myView.getMeasuredHeight()/2;
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight())/2;
        Animator animator = ViewAnimationUtils.createCircularReveal(myView, cx,cy
        ,0,finalRadius);
        myView.setVisibility(View.VISIBLE);
        animator.start();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void exitReveal(){
        int cx = myView.getMeasuredWidth()/2;
        int cy = myView.getMeasuredHeight()/2;
        int initialRadius = myView.getWidth()/2;
        Animator animator = ViewAnimationUtils.createCircularReveal(myView,cx,cy,initialRadius,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }


}
