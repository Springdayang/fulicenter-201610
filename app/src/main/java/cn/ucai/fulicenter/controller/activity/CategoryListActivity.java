package cn.ucai.fulicenter.controller.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.NewsGoodsFragment;

/**
 * 分类列表
 */
public class CategoryListActivity extends AppCompatActivity {
    NewsGoodsFragment mNewGoodsFragment;
    @BindView(R.id.btn_sort_price)
    Button btnSortPrice;
    @BindView(R.id.btn_sort_addtime)
    Button btnSortAddtime;
    boolean addTimeAsc=false;
    boolean priceAsc=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        mNewGoodsFragment=new NewsGoodsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mNewGoodsFragment)
                .commit();
    }

    @OnClick({R.id.btn_sort_price, R.id.btn_sort_addtime})
    public void onClick(View view) {
        int sortBy =I.SORT_BY_ADDTIME_ASC;
        Drawable right;
        switch (view.getId()) {
            case R.id.btn_sort_price:
                if(priceAsc){
                    mNewGoodsFragment.sortGoods(I.SORT_BY_PRICE_ASC);
                    right=getResources().getDrawable(R.mipmap.arrow_order_up);
                }else{
                    mNewGoodsFragment.sortGoods(I.SORT_BY_PRICE_DESC);
                    right=getResources().getDrawable(R.mipmap.arrow_order_down);
                }
                right.setBounds(0,0,right.getIntrinsicWidth(),right.getIntrinsicHeight());
                btnSortPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,right,null);
                priceAsc=!priceAsc;
                break;
            case R.id.btn_sort_addtime:
                if(addTimeAsc){
                    mNewGoodsFragment.sortGoods(I.SORT_BY_ADDTIME_ASC);
                    right=getResources().getDrawable(R.mipmap.arrow_order_up);
                }else{
                    mNewGoodsFragment.sortGoods(I.SORT_BY_ADDTIME_DESC);
                    right=getResources().getDrawable(R.mipmap.arrow_order_down);
                }
                right.setBounds(0,0,right.getIntrinsicWidth(),right.getIntrinsicHeight());
                btnSortAddtime.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,right,null);
                addTimeAsc=!addTimeAsc;
                break;
        }
        mNewGoodsFragment.sortGoods(sortBy);
    }
}