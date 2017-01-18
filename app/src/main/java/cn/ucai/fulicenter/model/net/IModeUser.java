package cn.ucai.fulicenter.model.net;

import android.content.Context;

import java.io.File;

import cn.ucai.fulicenter.model.bean.MessageBean;
import cn.ucai.fulicenter.model.util.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public interface IModeUser{
    void login(Context context, String username, String password, OkHttpUtils.OnCompleteListener<String>listener);
    void register(Context context, String username, String usernick, String password, OkHttpUtils.OnCompleteListener<String>listener);
    void updateNick(Context context, String username, String nick, OkHttpUtils.OnCompleteListener<String>listener);
    void uploadAvatar(Context context, String username, File file,OkHttpUtils.OnCompleteListener<String>listener);
    void collectCount(Context context, String username, OkHttpUtils.OnCompleteListener<MessageBean>listener);
}
