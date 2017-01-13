package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<CategoryGroupBean> mGroupBean;
    ArrayList<ArrayList<CategoryChildBean>>mChildBean;
    LayoutInflater layoutInflater=null;
   public  CategoryAdapter(Context context,ArrayList<CategoryGroupBean>groupBean,ArrayList<ArrayList<CategoryChildBean>>childBean){
        this.context=context;
       mChildBean=new ArrayList<>();
       mGroupBean=new ArrayList<>();
       mChildBean.addAll(childBean);
       mGroupBean.addAll(groupBean);
       layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return mGroupBean.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildBean.size();
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
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.group_category_item,null);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.children_category_item,null);
            }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
