package app.androidtext1.com.androidtext1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Checkable;
import android.widget.ImageView;

import app.androidtext1.com.androidtext1.R;
import app.androidtext1.com.androidtext1.helper.ApiLevelHelper;
import app.androidtext1.com.androidtext1.view.outlineprovider.RoundOutlineProvider;

/**
 * Created by Administrator on 2016/4/25 0025.
 * 增加了本地图片的处理圆形，加圆形边框色
 */
public class AvatarImageView extends ImageView implements Checkable {
    private String TAG = "AvatarImageView";
    private boolean mChecked;//标记,checked，

    public AvatarImageView(Context context) {
        this(context, null);
    }

    public AvatarImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatarImageView(Context context, AttributeSet attrs, int defStyleAttr) {//布局加载入口
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean b) {//被标记
        mChecked = b;
        invalidate();//重绘
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {//绘制点击中的view，第一次加载布局的时候也会调用（我懂），但是后来又运行一次？？
        super.onDraw(canvas);
        Log.i(TAG, "onDraw");
        if (mChecked) {
            Drawable border = ContextCompat.getDrawable(getContext(), R.drawable.selector_avatar);//加图层
            border.setBounds(0, 0, getWidth(), getHeight());
            border.draw(canvas);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//加载布局的时候调用一次（为什么嫩，猜测：由于原来是两个值不一样才加载
        super.onSizeChanged(w, h, oldw, oldh);
        if (ApiLevelHelper.isLowerThan(Build.VERSION_CODES.LOLLIPOP)) {//小于sdk21
            return;
        }
        if (w > 0 && h > 0) {//>=21
            setOutlineProvider(new RoundOutlineProvider(Math.min(w, h)));//最小值绘制边界
        }
    }

    /**
     * Set the image for this avatar. Will be used to create a round version of this avatar.
     *
     * @param resId The image's resource id.
     */
    @SuppressLint("NewApi")
    public void setAvatar(@DrawableRes int resId) {
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {//21
            setClipToOutline(true);//允许剪切边界
            setImageResource(resId);
        } else {
            setAvatarPreLollipop(resId);//老版本兼容
        }
    }

    private void setAvatarPreLollipop(@DrawableRes int resId) {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), resId,
                getContext().getTheme());
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        @SuppressWarnings("ConstantConditions")
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                bitmapDrawable.getBitmap());
        roundedDrawable.setCircular(true);//设置成圆形图片
        setImageDrawable(roundedDrawable);
    }
}
