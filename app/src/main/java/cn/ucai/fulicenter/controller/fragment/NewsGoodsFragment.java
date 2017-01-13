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

import butterknife.BindView;
import butterknife.ButterKnife;
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
    GridLayoutManager gridLayoutManager;
    NewGoodsAdapter adapter;
    public ArrayList<NewGoodsBean> list;
    IModelNewGoods modelNewGoods;
    int pageId = 1;
    static final int ACTION_DOWNLOAD = 0;
    static final int ACTION_PULL_DOWN_DOWNLOAD = 1;
    static final int ACTION_PULL_UP_DOWNLOAD = 2;
    @BindView(R.id.tvRefresh)
    TextView tvRefresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    public NewsGoodsFragment() {
    }

    private void setListener() {
        setPullUpListener();//上拉加载
        setPullDownListener();//下拉刷新
    }

    private void setPullUpListener() {
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                final int position = gridLayoutManager.findLastVisibleItemPosition();
                adapter.setDrag(newState == RecyclerView.SCROLL_STATE_DRAGGING);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && adapter.isMore()
                        && position == adapter.getItemCount() - 1) {
                    pageId++;
                    downLoadData(ACTION_PULL_UP_DOWNLOAD, pageId);
                }
            }

        });


    }

    private void setPullDownListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageId = 1;
                tvRefresh.setVisibility(View.VISIBLE);
                srl.setRefreshing(true);
                downLoadData(ACTION_PULL_DOWN_DOWNLOAD, pageId);
            }
        });
    }

    private void downLoadData(final int action, int pageId) {
        int catId=getActivity().getIntent().getIntExtra(I.NewAndBoutiqueGoods.CAT_ID,I.CAT_ID);
        modelNewGoods.downData(getContext(), catId, pageId, new OkHttpUtils.OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                adapter.setMore(result != null && result.length > 0);
                if (!adapter.isMore()) {
                    if (action == ACTION_PULL_UP_DOWNLOAD) {
                        adapter.setFoot("没有更多数据加载");
                    }
                    return;
                }
                adapter.setFoot("加载更多数据");
                    ArrayList<NewGoodsBean> list = ConvertUtils.array2List(result);
                    switch (action) {
                        case ACTION_DOWNLOAD:
                            adapter.initData(list);
                            break;
                        case ACTION_PULL_DOWN_DOWNLOAD:
                            tvRefresh.setVisibility(View.GONE);
                            srl.setRefreshing(false);
                            adapter.initData(list);
                            break;
                        case ACTION_PULL_UP_DOWNLOAD:
                            adapter.addData(list);
                            break;
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
        View view = inflater.inflate(R.layout.fragment_news_goods, container, false);
        ButterKnife.bind(this, view);
        modelNewGoods = new ModelNewGoods();
        initView();
        downLoadData(ACTION_DOWNLOAD, pageId);
        setListener();
        return view;
    }

    private void initView() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow),
                getResources().getColor(R.color.google_blue)

        );
        gridLayoutManager = new GridLayoutManager(getContext(), I.COLUM_NUM);
        list = new ArrayList<>();
        adapter = new NewGoodsAdapter(getContext(), list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.addItemDecoration(new SpaceItemDecoration(12));
        recycler.setHasFixedSize(true);//自适应
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public void sortGoods(int sortBy){
        adapter.sortGoods(sortBy);
    }
}
