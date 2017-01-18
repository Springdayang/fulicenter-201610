package cn.ucai.fulicenter.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.Result;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModeUser;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.util.CommonUtils;
import cn.ucai.fulicenter.model.util.ImageLoader;
import cn.ucai.fulicenter.model.util.OnSetAvatarListener;
import cn.ucai.fulicenter.model.util.ResultUtils;
import cn.ucai.fulicenter.model.util.SharePreferenceUtils;
import cn.ucai.fulicenter.view.MFGT;

import static cn.ucai.fulicenter.application.FuLiCenterApplication.getUser;
import static cn.ucai.fulicenter.application.I.REQUEST_CODE_NICK;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.ivUserAvatar)
    ImageView ivUserAvatar;
    @BindView(R.id.tvUser)
    TextView tvUser;
    @BindView(R.id.tvNick)
    TextView tvNick;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.tvUserAvatar)
    TextView tvUserAvatar;
    @BindView(R.id.ivArrow1)
    ImageView ivArrow1;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvNickRelative)
    RelativeLayout tvNickRelative;
    @BindView(R.id.tvUserRelative)
    RelativeLayout tvUserRelative;

    IModeUser model;
    OnSetAvatarListener mOnSetAvatarListener;
    @BindView(R.id.relaAvatar)
    RelativeLayout relaAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        User user = getUser();
        if (user != null) {
            loadUserInfo(user);
        } else {
            MFGT.gotoLogin(this);
        }
    }

    private void loadUserInfo(User user) {
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), this, ivUserAvatar);
        tvUser.setText(user.getMuserName());
        tvNick.setText(user.getMuserNick());
    }

    @OnClick(R.id.btn_logout)
    public void onClick() {
        FuLiCenterApplication.setUser(null);
        SharePreferenceUtils.getInstance(this).removeUser();
        MFGT.gotoLogin(this);
        finish();
    }

    @OnClick(R.id.tvNickRelative)
    public void updateNick() {
        MFGT.gotoUpDataNick(this);
//        String nick = tvNick.getText().toString().trim();
//        if (TextUtils.isEmpty(nick)) {
//
//        } else {
//
//        }
    }

    @OnClick(R.id.tvUserRelative)
    public void onClickUserName() {
        CommonUtils.showLongToast(R.string.username_connot_be_modify);
    }
    @OnClick(R.id.relaAvatar)
    public void onClickAvatar(){
        Log.i("dayang","onClickAvatar...");
        mOnSetAvatarListener=new
                OnSetAvatarListener(this,R.id.relaAvatar,getUser().getMuserName()
                , I.AVATAR_TYPE_USER_PATH);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_NICK) {
            tvNick.setText(getUser().getMuserNick());
        } else if (requestCode == OnSetAvatarListener.REQUEST_CROP_PHOTO) {
            uploadAvatar();
        } else {
//            OnSetAvatarListener.setAvatar(requestCode,data,ivUserAvatar);
        }
    }

    private void uploadAvatar() {
        User user = getUser();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.update_user_avatar));
        dialog.show();
        File file = null;
        file = new File(String.valueOf(OnSetAvatarListener.getAvatarFile(this,
                OnSetAvatarListener.getAvatarPath(this, "/" + user.getMuserName() + user.getMavatarSuffix()))));
        Log.i("dayang", "file=" + file.getAbsolutePath());
        model.uploadAvatar(this,
                getUser().getMuserName()
                , file, new OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("dayang", "result=" + result);
                        int msg = R.string.update_user_avatar_fail;
                        if (result != null) {
                            Result result1 = ResultUtils.getResultFromJson(result, User.class);
                            if (result1 != null) {
                                if (result1.isRetMsg()) {
                                    msg = R.string.update_user_avatar_success;
                                }
                            }
                        }
                        CommonUtils.showLongToast(msg);
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(String error) {
                        CommonUtils.showLongToast(error);
                        dialog.dismiss();
                    }
                });
    }
}
