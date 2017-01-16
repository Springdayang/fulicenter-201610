package cn.ucai.fulicenter.model.net;

import android.content.Context;

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
}
