package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.NewsGoodsFragment;

/**
 * 需要title_id,和在精选页面的所在的item_id
 * item_id为精选二级页面的
 *
 */
public class BoutiqueChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_child);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,new NewsGoodsFragment())
                .commit();
    }

    /**
     * 接收数据
     */
    private void receiveData(){
        int catId= getIntent().getIntExtra(I.NewAndBoutiqueGoods.CAT_ID,I.CAT_ID);
    }

}
