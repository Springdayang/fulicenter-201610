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
import cn.ucai.fulicenter.model.bean.User;
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
            Log.i("dayang","=========="+user.toString()+"=========");
            loadUserInfo(user);
        } else {
            MFGT.gotoLogin(getActivity());
        }
    }

    private void loadUserInfo(User user) {
        ImageLoader.downloadImg(getContext(), ivUserAvatar, user.getMavatarPath());
        tvUserName.setText(user.getMuserNick());
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
