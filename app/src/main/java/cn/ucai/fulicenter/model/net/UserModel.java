package cn.ucai.fulicenter.model.net;

import android.content.Context;

import java.io.File;

import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.util.MD5;
import cn.ucai.fulicenter.model.util.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class UserModel implements IModeUser{
    @Override
    public void login(Context context, String username, String password, OkHttpUtils.OnCompleteListener<String> listener) {
            OkHttpUtils<String>utils=new OkHttpUtils<>(context);
            utils.setRequestUrl(I.REQUEST_LOGIN)
                    .addParam(I.User.USER_NAME,username)
                    .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                    .targetClass(String.class)
                    .execute(listener);
    }
    @Override
    public void register(Context context, String username, String usernick, String password, OkHttpUtils.OnCompleteListener<String> listener) {
            OkHttpUtils<String>utils=new OkHttpUtils<>(context);
            utils.setRequestUrl(I.REQUEST_REGISTER)
                    .addParam(I.User.USER_NAME,username)
                    .addParam(I.User.NICK,usernick)
                    .addParam(I.User.PASSWORD,MD5.getMessageDigest(password))
                    .post()
                    .targetClass(String.class)
                    .execute(listener);
    }

    @Override
    public void updateNick(Context context, String username, String nick, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nick)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void uploadAvatar(Context context, String username, File file, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID,username)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }

}
