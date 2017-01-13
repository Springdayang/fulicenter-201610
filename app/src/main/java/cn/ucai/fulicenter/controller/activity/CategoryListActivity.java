package cn.ucai.fulicenter.controller.activity;

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
        switch (view.getId()) {
            case R.id.btn_sort_price:
                if(priceAsc){
                    mNewGoodsFragment.sortGoods(I.SORT_BY_PRICE_ASC);
                }else{
                    mNewGoodsFragment.sortGoods(I.SORT_BY_PRICE_DESC);
                }
                priceAsc=!priceAsc;
                break;
            case R.id.btn_sort_addtime:
                if(addTimeAsc){
                    mNewGoodsFragment.sortGoods(I.SORT_BY_ADDTIME_ASC);
                }else{
                    mNewGoodsFragment.sortGoods(I.SORT_BY_ADDTIME_DESC);
                }
                addTimeAsc=!addTimeAsc;
                break;
        }
        mNewGoodsFragment.sortGoods(sortBy);
    }
}
