package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.util.ImageLoader;
import cn.ucai.fulicenter.view.MFGT;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<CategoryGroupBean> mGroupBean;
    ArrayList<ArrayList<CategoryChildBean>> mChildBean;
    LayoutInflater layoutInflater = null;

    public CategoryAdapter(Context context, ArrayList<CategoryGroupBean> groupBean, ArrayList<ArrayList<CategoryChildBean>> childBean) {
        this.context = context;
        mChildBean = new ArrayList<>();
        mGroupBean = new ArrayList<>();
        mChildBean.addAll(childBean);
        mGroupBean.addAll(groupBean);
        layoutInflater = LayoutInflater.from(context);
    }
    public void initData(ArrayList<CategoryGroupBean>groupBeanlist,ArrayList<ArrayList<CategoryChildBean>>childBeanlist){
        if(mGroupBean!=null){
            mGroupBean.clear();
        }
        if(mChildBean!=null){
            mChildBean.clear();
        }
        mGroupBean.addAll(groupBeanlist);
        mChildBean.addAll(childBeanlist);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mGroupBean!=null?mGroupBean.size():0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return mChildBean!=null&&mChildBean.get(groupPosition)!=null?mChildBean.get(groupPosition).size():0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupBean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildBean.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder vh=null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.group_category_item, null);
            vh=new GroupViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh= (GroupViewHolder) convertView.getTag();
        }
        ImageLoader.downloadImg(context,vh.ivGroupImage,mGroupBean.get(groupPosition).getImageUrl());
        vh.tvGroupGoodsName.setText(mGroupBean.get(groupPosition).getName());
        vh.ivGroupArrow.setImageResource(isExpanded?R.mipmap.expand_off:R.mipmap.expand_on);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder=null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.children_category_item, null);
            childViewHolder=new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder= (ChildViewHolder) convertView.getTag();
        }
        ImageLoader.downloadImg(context,childViewHolder.ivChildImage,mChildBean.get(groupPosition).get(childPosition).getImageUrl());
        childViewHolder.tvChildrenGoodsName.setText(mChildBean.get(groupPosition).get(childPosition).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.gotoCategoryGoodsDetail(context,mChildBean.get(groupPosition).get(childPosition).getId(),
                        mGroupBean.get(groupPosition).getName(),mChildBean.get(groupPosition));
            }
        });
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        @BindView(R.id.ivGroupImage)
        ImageView ivGroupImage;
        @BindView(R.id.tvGroupGoodsName)
        TextView tvGroupGoodsName;
        @BindView(R.id.ivGroupArrow)
        ImageView ivGroupArrow;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.ivChildImage)
        ImageView ivChildImage;
        @BindView(R.id.tvChildrenGoodsName)
        TextView tvChildrenGoodsName;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
