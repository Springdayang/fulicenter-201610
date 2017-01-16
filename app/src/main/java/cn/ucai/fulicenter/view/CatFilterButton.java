package cn.ucai.fulicenter.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.activity.CategoryListActivity;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.util.ImageLoader;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class CatFilterButton extends Button {
    boolean isExpand = false;
    Drawable right;
    PopupWindow mPopupWindow;
    CatFilterAdapter adapter;
    GridView mGridView;
    Context mContext;
    String groupName;

    public CatFilterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void initCatFileterButton(String groupName, ArrayList<CategoryChildBean> list) {
        this.setText(groupName);
        this.groupName=groupName;
        setCartFilterButtonListener();
        adapter = new CatFilterAdapter(mContext, list);
        initGridView();
    }

    private void initGridView() {
        mGridView = new GridView(mContext);
        mGridView.setVerticalSpacing(10);
        mGridView.setHorizontalSpacing(10);
        mGridView.setColumnWidth(GridView.AUTO_FIT);
        mGridView.setAdapter(adapter);
    }

    private void setCartFilterButtonListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpand) {
                    initPopup();
                } else {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                }
                setArrow();
            }
        });
    }

    /**
     * 初始化popweidow
     */
    private void initPopup() {
        mPopupWindow = new PopupWindow();
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xbb000000));
        mPopupWindow.setContentView(mGridView);
        mPopupWindow.showAsDropDown(this);
    }

    private void setArrow() {
        if (isExpand) {
            right = getResources().getDrawable(R.mipmap.arrow2_up);
        } else {
            right = getResources().getDrawable(R.mipmap.arrow2_down);
        }
        right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, right, null);
        isExpand = !isExpand;
    }

    class CatFilterAdapter extends BaseAdapter {
        Context context;
        ArrayList<CategoryChildBean> list;
        LayoutInflater inflater;

        CatFilterAdapter(Context context, ArrayList<CategoryChildBean> list) {
            this.context = context;
            this.list = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CatFilterViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_cart_filter, null);
                viewHolder = new CatFilterViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (CatFilterViewHolder) convertView.getTag();
            }
            viewHolder.bind(position);
            return convertView;
        }

        class CatFilterViewHolder {
            @BindView(R.id.tvCategoryChildName)
            TextView tvCategoryChildName;
            @BindView(R.id.ivGoodView)
            ImageView ivGoodView;
            @BindView(R.id.rela_list)
            RelativeLayout relaList;

            CatFilterViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

            public void bind(final int position) {
                ImageLoader.downloadImg(context,ivGoodView,list.get(position).getImageUrl());
                tvCategoryChildName.setText(list.get(position).getName());
                 relaList.setOnClickListener(new OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         MFGT.gotoCategoryGoodsDetail(context,list.get(position).getId(),groupName,list);
                     }
                 });
                MFGT.finishActivity((CategoryListActivity)context);
            }
        }
    }

}
