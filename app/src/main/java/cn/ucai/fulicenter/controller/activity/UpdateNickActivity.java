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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.Result;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.dao.UserDao;
import cn.ucai.fulicenter.model.net.IModeUser;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.net.UserModel;
import cn.ucai.fulicenter.model.util.CommonUtils;
import cn.ucai.fulicenter.model.util.ResultUtils;
import cn.ucai.fulicenter.view.DisplayUtils;
import cn.ucai.fulicenter.view.MFGT;

public class UpdateNickActivity extends AppCompatActivity {

    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    @BindView(R.id.etNickName)
    EditText etNickName;
    @BindView(R.id.savaNickName)
    Button savaNickName;
    User user;
    IModeUser model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this,"修改昵称");
        initData();
    }
    private void initData(){
        user= FuLiCenterApplication.getUser();
        if(user==null){
            MFGT.finishActivity(this);
        }else{
            etNickName.setText(user.getMuserNick());
        }
    }

    @OnClick({R.id.ivArrow, R.id.savaNickName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivArrow:
                MFGT.finishActivity(this);
                break;
            case R.id.savaNickName:
                chekInput();
                break;
        }
    }
    public void chekInput(){
        String nick=etNickName.getText().toString().trim();
        if(TextUtils.isEmpty(nick)){
            CommonUtils.showLongToast(R.string.nick_name_connot_be_empty);
        }else if(nick.equals(user.getMuserNick())) {
            CommonUtils.showLongToast(R.string.update_nick_fail_unmodify);
        }else{
            updateNick(nick);
        }
    }
    private void updateNick(String nick){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage(getString(R.string.update_user_nick));
        dialog.show();
        model=new UserModel();
        model.updateNick(this, user.getMuserName(), nick, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                int msg=R.string.update_fail;
                if(result!=null){
                    Result result1= ResultUtils.getResultFromJson(result,User.class);
                    if(result1!=null){
                        if(result1.isRetMsg()){
                            msg=R.string.update_user_nick_success;
                            User user= (User) result1.getRetData();
                            Log.i("dayang","onSuccess : user="+user);
                            saveNewUser(user);
                            setResult(RESULT_OK);
                            finish();
                        }else{
                            if(result1.getRetCode()== I.MSG_USER_SAME_NICK
                                    ||result1.getRetCode()==I.MSG_USER_UPDATE_NICK_FAIL){
                                msg=R.string.update_nick_fail_unmodify;
                            }

                        }
                    }
                }
                CommonUtils.showLongToast(msg);
                dialog.dismiss();
            }
            @Override
            public void onError(String error) {
                CommonUtils.showLongToast(R.string.update_fail);
                dialog.dismiss();
                Log.i("dayang","error="+error);
            }
        });
    }
    private void saveNewUser(User user){
        FuLiCenterApplication.setUser(user);
        UserDao.getInstance().savaUser(user);
    }
}
