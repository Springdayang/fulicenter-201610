package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.MessageBean;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModeUser;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.net.UserModel;
import cn.ucai.fulicenter.model.util.ImageLoader;
import cn.ucai.fulicenter.model.util.SharePreferenceUtils;
import cn.ucai.fulicenter.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {

    @BindView(R.id.ivUserAvatar)
    ImageView ivUserAvatar;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tv_center_settigns)
    TextView tvCenterSettigns;
    @BindView(R.id.center_top)
    RelativeLayout centerTop;
    @BindView(R.id.ivPerson)
    ImageView ivPerson;
    IModeUser mIModel;
    @BindView(R.id.tvCollectCount)
    TextView tvCollectCount;

    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        User user = FuLiCenterApplication.getUser();
        if (user != null) {
            Log.i("dayang", "==========" + user.toString() + "=========");
            loadUserInfo(user);
            getCollectCount();
        } else {
            MFGT.gotoLogin(getActivity());
            Log.i("dayang", "initData :null user");//检测空用户登录后退跳转
        }
    }
    private void getCollectCount() {
        mIModel = new UserModel();
        mIModel.collectCount(getContext(), FuLiCenterApplication.getUser().getMuserName(),
                new OnCompleteListener<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean result) {
                        Log.i("dayang","onSuccess:result="+result);
                        if(result!=null&& result.isSuccess()){
                            loadCollectCount(result.getMsg());
                        }else{
                            loadCollectCount("0");
                        }
                    }
                    @Override
                    public void onError(String error) {
                        loadCollectCount("0");
                    }
                });
    }
    private void loadUserInfo(User user) {
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user),getContext(),ivUserAvatar);
        tvUserName.setText(user.getMuserNick());
        loadCollectCount("0");
    }

    private void loadCollectCount(String count) {
        tvCollectCount.setText(String.valueOf(count));
    }

    @OnClick({R.id.tv_center_settigns, R.id.center_top, R.id.ivPerson})
    public void onClick(View view) {
        FuLiCenterApplication.setUser(null);
        SharePreferenceUtils.getInstance(getContext()).removeUser();
        MFGT.gotoSettings(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
