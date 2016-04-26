package app.androidtext1.com.androidtext1.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.guangda.dao.DaoSession;
import com.guangda.dao.UsersDao;

import java.util.List;

import app.androidtext1.com.androidtext1.MyApp;
import greendao.Users;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class DbService {
    private static final String TAG = DbService.class.getSimpleName();
    private static DbService instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private UsersDao usersDao;
    private DbService(){}
    public static DbService getInstance(Context context){
        if (instance == null){
            instance = new DbService();
            if (appContext == null){
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = MyApp.getDaoSession(context);
            instance.usersDao = instance.mDaoSession.getUsersDao();
        }
        return instance;
    }
    public Users loadNote(long id){
        if (!TextUtils.isEmpty(id+"")){
            return usersDao.load(id);
        }
        return null;
    }
    public List<Users> loadAllNote(){
        return usersDao.loadAll();
    }
    public List<Users> loadAllNoteByOrder(){
        return usersDao.queryBuilder().orderDesc(UsersDao.Properties.Id).list();
    }
    public List<Users> queryNote(String where, String... params){
        return usersDao.queryRaw(where, params);
    }
    public long saveNote(Users user){
        return usersDao.insertOrReplace(user);
    }
    public void saveNoteLists(final  List<Users> list){
        if (list == null || list.isEmpty()){
            return;
        }
        usersDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Users user = list.get(i);
                    usersDao.insertOrReplace(user);
                }
            }
        });
    }
    public void deleteAllNote(){
        usersDao.deleteAll();
    }
    public void deleteNote(long id){
        usersDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }
    public void deleteNote(Users user){
        usersDao.delete(user);
    }
}
