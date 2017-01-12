package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.adapter.NewGoodsAdapter;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.net.IModelNewGoods;
import cn.ucai.fulicenter.model.net.ModelNewGoods;
import cn.ucai.fulicenter.model.util.ConvertUtils;
import cn.ucai.fulicenter.model.util.OkHttpUtils;
import cn.ucai.fulicenter.view.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsGoodsFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    NewGoodsAdapter adapter;
    TextView tvRefresh;
    public  ArrayList<NewGoodsBean>list;
    IModelNewGoods modelNewGoods;
    int pageId=1;
    public NewsGoodsFragment() {
    }

    private void initData(){
        modelNewGoods.downData(getContext(), I.CAT_ID, pageId, new OkHttpUtils.OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                if (result!=null&&result.length>0){
                    ArrayList<NewGoodsBean>list= ConvertUtils.array2List(result);
                    adapter.initData(list);
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news_goods, container, false);
        modelNewGoods=new ModelNewGoods();
        initView(view);
        initData();
        return view;
    }
    private void initView(View view){
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl);
        tvRefresh= (TextView) view.findViewById(R.id.tvRefresh);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow),
                getResources().getColor(R.color.google_blue)

        );
        gridLayoutManager=new GridLayoutManager(getContext(),I.COLUM_NUM);
        list=new ArrayList<>();
        adapter=new NewGoodsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(12));
        recyclerView.setHasFixedSize(true);//自适应
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
