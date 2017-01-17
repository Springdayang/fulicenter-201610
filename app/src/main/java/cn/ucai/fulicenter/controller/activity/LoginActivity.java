package cn.ucai.fulicenter.controller.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.Result;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModeUser;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.net.UserModel;
import cn.ucai.fulicenter.model.util.CommonUtils;
import cn.ucai.fulicenter.model.util.ResultUtils;
import cn.ucai.fulicenter.model.util.SharePreferenceUtils;
import cn.ucai.fulicenter.view.MFGT;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG=LoginActivity.class.getSimpleName();
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.Login)
    Button Login;
    @BindView(R.id.Register)
    Button Register;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    IModeUser modeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.Login, R.id.Register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                MFGT.finishActivity(this);
                break;
            case R.id.Login:
                checkInput();
                break;
            case R.id.Register:
                MFGT.gotoRegister(this);
                break;
        }
    }

    private void checkInput() {
        String userName =etUsername.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        if(TextUtils.isEmpty(userName)){
            etUsername.setError(getString(R.string.user_name_connot_be_empty));
            etUsername.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError(getString(R.string.password_connot_be_empty));
            etPassword.requestFocus();
        }else{
            login(userName,password);
        }
    }
    private void login(String username,String password){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage(getString(R.string.logining));
        modeUser=new UserModel();
        modeUser.login(this, username, password, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if(s!=null){
                    Result result= ResultUtils.getResultFromJson(s,Result.class);
                    Log.i("dayang","---------result------------"+result);
                    if(result!=null){
                        if(result.isRetMsg()){
                            User user= (User) result.getRetData();
                            SharePreferenceUtils.getInstance(LoginActivity.this).saveUser(user.getMuserName());
                            MFGT.finishActivity(LoginActivity.this);
                        }else{
                            if(result.getRetCode()== I.MSG_LOGIN_UNKNOW_USER){
                                CommonUtils.showLongToast(getString(R.string.login_fail_unknow_user));
                            }
                            if(result.getRetCode()==I.MSG_LOGIN_ERROR_PASSWORD){
                                CommonUtils.showLongToast(getString(R.string.login_fail_error_password));
                            }
                        }
                    }else{
                        CommonUtils.showLongToast(getString(R.string.login_fail));
                    }
                }else{
                    CommonUtils.showLongToast(getString(R.string.login_fail));
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Log.i("dayang","dialog"+error+"------------------");
                CommonUtils.showLongToast(error);
            }
        });
    }
}
