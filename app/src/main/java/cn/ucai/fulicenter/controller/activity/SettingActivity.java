package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.util.ImageLoader;
import cn.ucai.fulicenter.model.util.SharePreferenceUtils;
import cn.ucai.fulicenter.view.MFGT;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.ivUserAvatar)
    ImageView ivUserAvatar;
    @BindView(R.id.tvUser)
    TextView tvUser;
    @BindView(R.id.tvNick)
    TextView tvNick;
    @BindView(R.id.btn_logout)
    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initData();
    }
    private void initData(){
        User user= FuLiCenterApplication.getUser();
        if(user!=null){
            loadUserInfo(user);
        }else{
            MFGT.gotoLogin(this);
        }
    }
    private void loadUserInfo(User user){
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user),this,ivUserAvatar);
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

}