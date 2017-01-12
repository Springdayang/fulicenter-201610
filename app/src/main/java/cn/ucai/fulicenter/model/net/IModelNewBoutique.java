package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.util.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public interface IModelNewBoutique {
    void downData(Context context, OkHttpUtils.OnCompleteListener<BoutiqueBean[]>listener);
}
