package app.androidtext1.com.androidtext1;

import android.app.Application;
import android.content.Context;

import com.guangda.dao.DaoMaster;
import com.guangda.dao.DaoSession;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class MyApp extends Application{
    public static MyApp mInstance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null){
            mInstance = this;
        }
    }
    public static DaoMaster getDaoMaster(Context context){
        if (daoMaster == null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "myDb", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }
    public static DaoSession getDaoSession(Context context){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
