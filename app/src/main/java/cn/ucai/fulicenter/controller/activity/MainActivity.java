package cn.ucai.fulicenter.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.BoutiqueFragment;
import cn.ucai.fulicenter.controller.fragment.CategoryFragment;
import cn.ucai.fulicenter.controller.fragment.NewsGoodsFragment;
import cn.ucai.fulicenter.controller.fragment.PersonFragment;
import cn.ucai.fulicenter.view.MFGT;

public class MainActivity extends AppCompatActivity {
    int index, currentIndex;
    RadioButton radioButton[] = new RadioButton[5];
//    @BindView(R.id.fragment)
//    FrameLayout fragment;
    @BindView(R.id.rbNewGoods)
    RadioButton rbNewGoods;
    @BindView(R.id.rbBoutique)
    RadioButton rbBoutique;
    @BindView(R.id.rbCategory)
    RadioButton rbCategory;
    @BindView(R.id.rbCart)
    RadioButton rbCart;
    @BindView(R.id.rbPerson)
    RadioButton rbPerson;
    @BindView(R.id.linear)
    LinearLayout linear;

    //Fragment
    NewsGoodsFragment mNewGoodsFragment;
    BoutiqueFragment mBoutiqueFragment;
    CategoryFragment mCategoryFragment;
    PersonFragment mPersonFragment;
    Fragment mFragment[]=new Fragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        addFragment();
    }
//
    private void addFragment() {
        mNewGoodsFragment=new NewsGoodsFragment();
        mBoutiqueFragment=new BoutiqueFragment();
        mCategoryFragment=new CategoryFragment();
        mPersonFragment=new PersonFragment();
        mFragment[0]=mNewGoodsFragment;
        mFragment[1]=mBoutiqueFragment;
        mFragment[2]=mCategoryFragment;
        mFragment[4]=mPersonFragment;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, mNewGoodsFragment);
        fragmentTransaction.add(R.id.fragment,mBoutiqueFragment);
        fragmentTransaction.add(R.id.fragment,mCategoryFragment);
        fragmentTransaction.add(R.id.fragment,mPersonFragment);
        fragmentTransaction.show(mNewGoodsFragment);
        fragmentTransaction.hide(mBoutiqueFragment);
        fragmentTransaction.hide(mCategoryFragment);
        fragmentTransaction.hide(mPersonFragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        rbNewGoods = (RadioButton) findViewById(R.id.rbNewGoods);
        rbBoutique = (RadioButton) findViewById(R.id.rbBoutique);
        rbCategory = (RadioButton) findViewById(R.id.rbCategory);
        rbCart = (RadioButton) findViewById(R.id.rbCart);
        rbPerson = (RadioButton) findViewById(R.id.rbPerson);
        radioButton[0] = rbNewGoods;
        radioButton[1] = rbBoutique;
        radioButton[2] = rbCategory;
        radioButton[3] = rbCart;
        radioButton[4] = rbPerson;
        radioButton[0].setChecked(true);
    }
    private void setFragment(){
        getSupportFragmentManager().beginTransaction().show(mFragment[index])
                .hide(mFragment[currentIndex]).commit();
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rbNewGoods:
                index = 0;
                break;
            case R.id.rbBoutique:
                index = 1;
                break;
            case R.id.rbCategory:
                index = 2;
                break;
            case R.id.rbCart:
                index = 3;
            case R.id.rbPerson:
                if (FuLiCenterApplication.getUser()==null){
                    MFGT.gotoLogin(this);
                }else{
                    index=4;
                }
                break;
        }
        setFragment();
        if (index != currentIndex) {
            setRadioState();
        }
    }


    public void setRadioState() {
        for (int i = 0; i < radioButton.length; i++) {
            if (index != i) {
                radioButton[i].setChecked(false);
            } else {
                radioButton[i].setChecked(true);
            }
            currentIndex = index;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("dayang","onResume,currentIndex="+currentIndex+",index="+
                index+",user= "+ FuLiCenterApplication.getUser());
        setRadioState();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("dayang","dayang"+requestCode+"requestCode"+resultCode+"resultCode");
        if(resultCode==RESULT_OK&&requestCode== I.REQUEST_CODE_LOGIN){
            index=4;
            setFragment();
            setRadioState();
        }
    }
}
