package cn.ucai.fulicenter.view;

import android.app.Activity;
import android.content.Intent;

import cn.ucai.fulicenter.R;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class MGTF {
    public static void startActivity(Activity activity,Class clz){
        activity.overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
        activity.startActivity(new Intent(activity,clz));

    }
    public static void finishActivity(Activity activity){
        activity.overridePendingTransition(R.anim.push_left_out,R.anim.push_right_in);
        activity.finish();
    }
}
