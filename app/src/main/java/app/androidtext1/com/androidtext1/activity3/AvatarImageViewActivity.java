package app.androidtext1.com.androidtext1.activity3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import app.androidtext1.com.androidtext1.R;
import app.androidtext1.com.androidtext1.view.AvatarImageView;

public class AvatarImageViewActivity extends Activity {
    private AvatarImageView avatarImageView;
    private boolean isChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_image_view);
        avatarImageView = (AvatarImageView) findViewById(R.id.avatarView);
    }

    public void onClick(View view) {
        avatarImageView.setAvatar(R.mipmap.itemtest);
        avatarImageView.setChecked(isChecked);
        isChecked = !isChecked;
    }
}
