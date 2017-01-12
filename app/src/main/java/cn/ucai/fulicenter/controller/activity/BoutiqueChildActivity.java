package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.NewsGoodsFragment;
import cn.ucai.fulicenter.view.MFGT;

/**
 * 需要title_id,和在精选页面的所在的item_id
 * item_id为精选二级页面的
 */
public class BoutiqueChildActivity extends AppCompatActivity {

    @BindView(R.id.tv_common_title)
    TextView tvCommonTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_child);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new NewsGoodsFragment())
                .commit();
        tvCommonTitle.setText(getIntent().getStringExtra(I.Boutique.NAME));
    }
    @OnClick(R.id.backClickArea)
    public void onClick(){
        MFGT.finishActivity(this);
    }
}
