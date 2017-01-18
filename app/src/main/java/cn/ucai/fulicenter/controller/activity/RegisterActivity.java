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
import cn.ucai.fulicenter.model.bean.Result;
import cn.ucai.fulicenter.model.net.IModeUser;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.net.UserModel;
import cn.ucai.fulicenter.model.util.CommonUtils;
import cn.ucai.fulicenter.model.util.ResultUtils;
import cn.ucai.fulicenter.view.DisplayUtils;
import cn.ucai.fulicenter.view.MFGT;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.backClickArea)
    Button backClickArea;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.ivRegisterBack)
    ImageView ivRegisterBack;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etUserNickName)
    EditText etUserNickName;
    @BindView(R.id.etUserPassword)
    EditText etUserPassword;
    @BindView(R.id.etConfirmUserPassword)
    EditText etConfirmUserPassword;
    @BindView(R.id.tvAvatar)
    TextView tvAvatar;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;

    IModeUser model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this,"用户注册");
    }

    @OnClick({R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backClickArea:
                MFGT.finishActivity(this);
                break;
            case R.id.btn_register:
                checkInput();
                break;
        }
    }
    private void register(String username,String usernick,String password){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage(getString(R.string.registering));
        model=new UserModel();
        model.register(RegisterActivity.this, username, usernick, password, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null){
                    Result result1= ResultUtils.getResultFromJson(result,Result.class);
                    Log.i("dayang","-------------result="+result);
                    if(result1.isRetMsg()){
                        //register success
                        CommonUtils.showLongToast(R.string.register_success);
                        MFGT.finishActivity(RegisterActivity.this);
                    }else{
                        //register fail,username,102
                        CommonUtils.showLongToast(R.string.register_fail);
                    }
                }else{
                    CommonUtils.showLongToast(R.string.register_fail);
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                CommonUtils.showLongToast(error);
            }
        });
    }

    private void checkInput() {
        String username =etUserName.getText().toString();
        String usernick =etUserNickName.getText().toString();
        String password=etUserPassword.getText().toString();
        String confirm=etConfirmUserPassword.getText().toString();
        if(TextUtils.isEmpty(username)){
            etUserName.setError(getResources().getString(R.string.user_name_connot_be_empty));
            etUserName.requestFocus();
        }else if(!username.matches("[a-zA-Z]\\w{5,15}")){
            etUserName.setError(getResources().getString(R.string.illegal_user_name));
            etUserName.requestFocus();
        }else if(TextUtils.isEmpty(usernick)){
            etUserNickName.setError(getResources().getString(R.string.nick_name_connot_be_empty));
            etUserNickName.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etUserPassword.setError(getResources().getString(R.string.confirm_password_connot_be_empty));
            etUserPassword.requestFocus();
        }else if(TextUtils.isEmpty(confirm)){
            etConfirmUserPassword.setError(getResources().getString(R.string.two_input_password));
            etConfirmUserPassword.requestFocus();
        }else{
            register(username,usernick,password);
        }
    }
}
