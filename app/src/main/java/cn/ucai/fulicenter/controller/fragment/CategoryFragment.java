package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.adapter.CategoryAdapter;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.net.IModelNewCategory;
import cn.ucai.fulicenter.model.net.ModelCategory;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.util.ConvertUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    @BindView(R.id.elv_category)
    ExpandableListView elvCategory;
    @BindView(R.id.tv_nomore)
    TextView tvNomore;
    IModelNewCategory modelNewCategory;
    ArrayList<ArrayList<CategoryChildBean>>childList;
    ArrayList<CategoryGroupBean>groupList;
    CategoryAdapter adapter;
    int groupCount=0;
    public CategoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        initView(false);
        childList=new ArrayList<>();
        groupList=new ArrayList<>();
        adapter=new CategoryAdapter(getContext(),groupList,childList);
        initData();
        return view;
    }
    private void initData(){
        modelNewCategory=new ModelCategory();
        modelNewCategory.downData(getContext(),new OnCompleteListener<CategoryGroupBean[]>() {
            @Override
            public void onSuccess(CategoryGroupBean[] result) {
                for (int i=0;i<result.length;i++){
                    Log.i("dayang","-------"+"result"+result[i].toString()+"--------------");
                }
                if(result!=null&&result.length>0){
                    initView(true);
                    groupList= ConvertUtils.array2List(result);
                    for (int i=0;i<groupList.size();i++){
                        downChildData(groupList.get(i).getId());
                    }
                }else{
                    initView(false);
                }
            }
            @Override
            public void onError(String error) {
                    initView(false);
                Log.i("dayang","-----------"+"error"+"-------------");
            }
        });
    }

    private void downChildData(int id) {
        modelNewCategory.downData(getContext(), id, new OnCompleteListener<CategoryChildBean[]>() {
            @Override
            public void onSuccess(CategoryChildBean[] result) {
                if(result!=null&&result.length>0){
                    groupCount++;
                    ArrayList<CategoryChildBean>list=ConvertUtils.array2List(result);
                    childList.add(list);
                }
                if(groupCount==groupList.size()){
                    adapter.initData(groupList,childList);
                }
            }
            @Override
            public void onError(String error) {
                Log.i("dayang","--------------error="+error);
            }
        });
    }

    private void initView(boolean hasData){
        elvCategory.setVisibility(hasData?View.VISIBLE:View.GONE);
        elvCategory.setAdapter(adapter);
        tvNomore.setVisibility(hasData?View.GONE:View.VISIBLE);
    }

}
