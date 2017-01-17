package cn.ucai.fulicenter.model.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class SharePreferenceUtils {
    private static final String SHARE_PREFERNCE_NAME="cn.user.fulicenter_user";
    private static final String SHARE_PREFERNCE_NAME_USERNAME="cn.user.fulicenter_user_username";
    private static SharePreferenceUtils instance;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor mEditor;
    public SharePreferenceUtils(Context context){
        preferences=context.getSharedPreferences(SHARE_PREFERNCE_NAME,Context.MODE_PRIVATE);
        mEditor=preferences.edit();
    }
    public static SharePreferenceUtils getInstance(Context context){
        if(instance==null){
            instance=new SharePreferenceUtils(context);
        }
        return instance;
    }
    public static  void saveUser(String username){
        mEditor.putString(SHARE_PREFERNCE_NAME_USERNAME,username);
        mEditor.commit();
    }
    public static String getUser(){
        return preferences.getString(SHARE_PREFERNCE_NAME_USERNAME,null);
    }
    public void removeUser(){
        mEditor.remove(SHARE_PREFERNCE_NAME_USERNAME);
        mEditor.commit();
    }
}
