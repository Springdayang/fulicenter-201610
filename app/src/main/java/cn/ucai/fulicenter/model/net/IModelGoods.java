package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.bean.MessageBean;
import cn.ucai.fulicenter.model.util.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public interface IModelGoods {
    void downData(Context context, int goodsId, OnCompleteListener<GoodsDetailsBean> listener);
    void isCollect(Context context, int goodsId, String username, OkHttpUtils.OnCompleteListener<MessageBean>listener);
    void setCollect(Context context,int goodsId,String username,int action,OkHttpUtils.OnCompleteListener<MessageBean>listener);
}
