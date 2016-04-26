package app.androidtext1.com.androidtext1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class OnTouchOnClickActivity extends Activity implements View.OnClickListener,View.OnTouchListener{
    private String TAG = "OnTouchOnClickActivity";
    private ImageView touchClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch_on_click);

        touchClick = (ImageView) findViewById(R.id.touchClick);
        touchClick.setOnClickListener(this);
        touchClick.setOnTouchListener(this);
        touchClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG,"onLongClick");
                return false;
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.touchClick:
                Log.i(TAG,"onClick");
                break;
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i(TAG,"onTouch");
        return false;
    }
}
