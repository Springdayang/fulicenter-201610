package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.util.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class ModelNewCategory implements IModeCategoryGoodsBean {

    @Override
    public void downData(Context context, String parent_id, int page_id, int page_size, OkHttpUtils.OnCompleteListener<NewGoodsBean> listener) {

    }
}
