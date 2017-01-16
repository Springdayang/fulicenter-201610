package cn.ucai.fulicenter.application;

import android.app.Application;

import cn.ucai.fulicenter.model.bean.User;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class FuLiCenterApplication extends Application {
    private static FuLiCenterApplication instance;
    public static FuLiCenterApplication getInstance(){
        return instance;
    }
    public  User user;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static User getUser(){
            return new User();
    }
    public static void setUser(User user){
           user=user;
    }
}
