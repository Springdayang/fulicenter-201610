package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.util.ImageLoader;
import cn.ucai.fulicenter.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    ImageView mTvUserAvatar;
    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }
    private void initData(){
        User user= FuLiCenterApplication.getUser();
        if(user!=null){
            loadUserInfo(user);
        }else{
            MFGT.gotoLogin(getActivity());
        }
    }
    private void loadUserInfo(User user){
        String path="";
        ImageLoader.downloadImg(getContext(),mTvUserAvatar,user.getMavatarPath());
    }

}
