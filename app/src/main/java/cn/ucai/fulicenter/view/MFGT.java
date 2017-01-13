package cn.ucai.fulicenter.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.activity.BoutiqueChildActivity;
import cn.ucai.fulicenter.controller.activity.CategoryListActivity;
import cn.ucai.fulicenter.controller.activity.GoodsDetailsActivity;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class MFGT {
    public static void startActivity(Activity activity,Class clz){
        activity.overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
        activity.startActivity(new Intent(activity,clz));

    }
    public static void finishActivity(Activity activity){
        activity.overridePendingTransition(R.anim.push_left_out,R.anim.push_right_in);
        activity.finish();
    }
    public static void startActivity(Activity context,Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);//自带的动画效果
    }
    public static void gotoBoutiqueChild(Context context, BoutiqueBean boutiqueBean){
        Intent intent=new Intent(context, BoutiqueChildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID,boutiqueBean.getId());
        intent.putExtra(I.Boutique.NAME,boutiqueBean.getTitle());
        startActivity((Activity) context,intent);
    }
    public static void gotoGoodsDetail(Context context,int goodsId){
        Intent intent=new Intent(context, GoodsDetailsActivity.class);
        intent.putExtra(I.GoodsDetails.KEY_GOODS_ID,goodsId);
        startActivity((Activity) context,intent);
    }
    public static void gotoCategoryGoodsDetail(Context context,int catId){
        Intent intent=new Intent(context, CategoryListActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID,catId);
        startActivity((Activity)context,intent);
    }
}
