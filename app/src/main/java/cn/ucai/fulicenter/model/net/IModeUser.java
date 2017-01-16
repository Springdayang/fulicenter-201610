package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.util.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public interface IModeUser{
    void login(Context context, String username, String password, OkHttpUtils.OnCompleteListener<String>listener);
    void register(Context context, String username, String usernick, String password, OkHttpUtils.OnCompleteListener<String>listener);
}
