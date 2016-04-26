package app.androidtext1.com.androidtext1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import app.androidtext1.com.androidtext1.R;

/**
 * Created by Administrator on 2016/4/19 0019.
 */
public class MyRecyclerView extends RecyclerView {
    String TAG = "MyRecyclerView";
    private Bitmap background;
    DisplayMetrics dm;
    private int screenWidth;
    private int screenHeight;
    private int screenWidthdp;
    private int screenHeightdp;
    private int backgroundWidth2dp;
    private int backgroundHeight2dp;
    int heightChild;
    int itemHeight = 160;

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        background = BitmapFactory.decodeResource(getResources(),
                R.mipmap.mybookshelf_img);
        dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        float scale = getResources().getDisplayMetrics().density;
        screenWidthdp = (int) (screenWidth / scale + 0.5f);
        screenHeightdp = (int) (screenHeight / scale + 0.5f);
//        Log.i(TAG, "screenWidth:" + screenWidth + " screenHeight" + screenHeight + " screenWidthdp:" + screenWidthdp +
//                " screenHeightdp:" + screenHeightdp);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        View view = getFocusedChild();
        if (view != null) {
            heightChild = view.getMeasuredHeight();
//            Log.i(TAG, "mearHeight" + heightChild);
        }
        int count = getChildCount();
//        Log.i(TAG, "count:" + count);//子布局个数40
        int top = count > 0 ? getChildAt(0).getTop() : 0;
//        Log.i(TAG, "top:" + top);//子布局个数
        int backgroundWidth = background.getWidth();
        int backgroundHeight = background.getHeight() + 2;
        int width = getWidth();
        int height = getHeight();
        float scale = getResources().getDisplayMetrics().density;
        backgroundWidth2dp = (int) (backgroundWidth / scale + 0.5f);
        backgroundHeight2dp = (int) (backgroundHeight / scale + 0.5f);
//        Log.i(TAG, "height:" + height + " width:" + width + " backgroundWidth:" + backgroundWidth +
//                " backgroundHeight:" + backgroundHeight + " backgroundWidth2dp:" +
//                backgroundWidth2dp + " backgroundHeight2dp:" + backgroundHeight2dp);//
        for (int y = top; y < height; y += (int) (itemHeight * scale + 0.5f)) {//绘制整个背景
//            for (int x = 0; x < width; x += backgroundWidth) {
//                int xxpx = (int) (x * scale + 0.5f);//dp2px
//                int xdp = (int) (x / scale + 0.5f);//px2dp
//                int ydp = (int) (y / scale + 0.5f);//px2dp
//                Log.i(TAG, "x:" + x + " y:" + y + " xdp:" + xdp + " ydp:" + ydp);
//                int dimindp = 15;//dp
//                int startxdp = 15;//dp
//                if (x == 0) {
//                    x = (int) (startxdp * scale + 0.5f);//2dp to px//164=134+30;
//                } else if (xdp == (backgroundWidth2dp+startxdp)) {//134+15
//                    x = (int) ((xdp - dimindp) * scale + 0.5f);//dd:134+15=149(图片的宽度)
//                } else if (xdp == (backgroundWidth2dp+startxdp-dimindp+backgroundWidth2dp)) {//298+20
//                    x = (int) ((xdp - dimindp) * scale + 0.5f);//dd:134:149+
//                } else if (xdp == (backgroundWidth2dp+startxdp-dimindp+backgroundWidth2dp - startxdp+backgroundWidth2dp)) {//298+20
//                    x = (int) ((xdp - dimindp) * scale + 0.5f);//dd:134
//                } else if (xdp == (backgroundWidth2dp+startxdp-dimindp+backgroundWidth2dp - startxdp+backgroundWidth2dp -startxdp+backgroundWidth2dp)) {//298+20
//                    x = (int) ((xdp - dimindp) * scale + 0.5f);
//                }
//                                canvas.drawBitmap(background, x, y, null);
//
//
//            }
            Paint paint = new Paint();//设置画笔颜色
            paint.setColor(getResources().getColor(R.color.color1));
//            paint.setColor(Color.RED);
            paint.setStrokeWidth(4); //
            paint.setStyle(Paint.Style.FILL);//设置填充类型
            Paint paint1 = new Paint();//设置画笔颜色
            paint1.setColor(getResources().getColor(R.color.color2));
            paint1.setStrokeWidth(4); //
            paint1.setStyle(Paint.Style.FILL);//设置填充类型

            Path path2 = new Path();
            path2.reset();//(int) (x * scale + 0.5f)
            int tops = 130;//距离顶部
            int topSpa = 115;
            int lefts = 20;
            int leftsL = 40;
            int topSpa2 = 20;//两个模型垂直
            path2.moveTo((int) (lefts * scale + 0.5f), y + (int) (tops * scale + 0.5f)); //左顶点 也即起始点
            path2.lineTo(screenWidth - (int) (lefts * scale + 0.5f), y + (int) (tops * scale + 0.5f)); //右顶点
            path2.lineTo(screenWidth - (int) (leftsL * scale + 0.5f), y + (int) (itemHeight * scale + 0.5f)); //右底部
            path2.lineTo((int) (leftsL * scale + 0.5f), y + (int) (itemHeight * scale + 0.5f)); // 左底部
            canvas.drawRect((int) (lefts * scale + 0.5f), y + (int) (topSpa * scale + 0.5f),
                    screenWidth - (int) (lefts * scale + 0.5f), y + (int) (tops * scale + 0.5f), paint1);//绘制黄色矩形
            canvas.drawPath(path2, paint);//绘制梯形
        }
        super.dispatchDraw(canvas);
    }
}
