package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
//        addFragment();
    }
//
//    private void addFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment, new NewsGoodsFragment());
//        fragmentTransaction.commit();
//    }

    private void initView() {
        rbNewGoods = (RadioButton) findViewById(R.id.rbNewGoods);
        rbBoutique = (RadioButton) findViewById(R.id.rbBoutique);
        rbCategory = (RadioButton) findViewById(R.id.rbCategory);
        rbCart = (RadioButton) findViewById(R.id.rbCart);
        rbPerson = (RadioButton) findViewById(R.id.rbPerson);
        radioButton[0] = rbNewGoods;
        radioButton[1] = rbCategory;
        radioButton[2] = rbBoutique;
        radioButton[3] = rbCart;
        radioButton[4] = rbPerson;
        radioButton[0].setChecked(true);
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rbNewGoods:
                index = 0;
                break;
            case R.id.rbPerson:
                index = 1;
                break;
            case R.id.rbBoutique:
                index = 2;
                break;
            case R.id.rbCategory:
                index = 3;
                break;
            case R.id.rbCart:
                index = 4;
                break;
        }
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
}
