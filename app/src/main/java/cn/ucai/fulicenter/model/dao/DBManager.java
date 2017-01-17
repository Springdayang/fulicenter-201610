package cn.ucai.fulicenter.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cn.ucai.fulicenter.model.bean.User;

import static cn.ucai.fulicenter.model.dao.DBOpenHelper.instance;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class DBManager {
    private static final String TAG=DBManager.class.getSimpleName();
    private static DBOpenHelper dbOpenHelper;
    static DBManager dbMgr=new DBManager();
    public DBManager(){

    }
    public static void onInit(Context context){
        dbOpenHelper=new DBOpenHelper(context);
    }

    public void savaUser(User user){
        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        if (db.isOpen()){
            ContentValues values=new ContentValues();
            values.put(UserDao.USER_COLUME_NAME,user.getMuserName());
            values.put(UserDao.USER_COLUME_NICK,user.getMuserNick());
            values.put(UserDao.USER_COLUME_AVATAR,user.getMavatarId());
            values.put(UserDao.USER_COLUME_AVATAR_PATH,user.getMavatarPath());
            values.put(UserDao.USER_COLUME_AVATAR_TYPE,user.getMavatarType());
            values.put(UserDao.USER_COLUME_AVATAR_SUFFIX,user.getMavatarSuffix());
            values.put(UserDao.USER_COLUME_AVATAR_UPDATE_TIME,user.getMavatarLastUpdateTime());
            db.replace(UserDao.USER_TABLE_NAME,null,values);
        }
    }
    private void closeDB(){
        if(instance!=null){
            SQLiteDatabase db=instance.getWritableDatabase();
            db.close();
            instance=null;
        }
    }
    public synchronized  static DBManager getInstance(){
        if(dbOpenHelper==null){
            Log.i("dayang","没有调用onInit()");
        }
        return dbMgr;
    }
}