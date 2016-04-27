package app.androidtext1.com.androidtext1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import app.androidtext1.com.androidtext1.adapter.RecyclerListAdapter;
import app.androidtext1.com.androidtext1.helper.OnStartDragListener;
import app.androidtext1.com.androidtext1.helper.SimpleItemTouchHelperCallback;

public class MainActivity extends Activity implements OnStartDragListener{
    private String TAG = "MainActivity";
    private RecyclerView mRecyclerView;

    private ItemTouchHelper mItemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float a = 5/10;
        Log.i(TAG,"Mytest:"+a);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        final RecyclerListAdapter adapter = new RecyclerListAdapter(this,this);
        mRecyclerView.setAdapter(adapter);
        final int spanCount = 4;
        final GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i(TAG,"onTouch:"+motionEvent.getAction());
                return false;
            }
        });
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
