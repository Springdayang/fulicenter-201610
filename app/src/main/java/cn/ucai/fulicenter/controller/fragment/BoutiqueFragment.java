package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.adapter.BoutiqueAdapter;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.net.IModelNewBoutique;
import cn.ucai.fulicenter.model.net.ModelBoutique;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.util.ConvertUtils;
import cn.ucai.fulicenter.view.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 * 精选页面
 */
public class BoutiqueFragment extends Fragment {
    @BindView(R.id.tvRefresh)
    TextView tvRefresh;
    @BindView(R.id.recyler)
    RecyclerView recyler;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    static final int ACITON_DOWNLOAD = 0;
    static final int ACTION_PULL_UP_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN_DOWNLOAD = 2;
    BoutiqueAdapter boutiqueAdapter;
    ArrayList<BoutiqueBean> boutiqueBeanArrayList;
    LinearLayoutManager linearlayoutManager;
    IModelNewBoutique iModelNewBoutique;//获取数据
    @BindView(R.id.tv_nomore)
    TextView tvNomore;
    public BoutiqueFragment() {
        // Required empty public constructor
    }

    /*
    *从网络上下载数据
     */
    private void downData(final int action) {
        iModelNewBoutique.downData(getContext(), new OnCompleteListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                boutiqueAdapter.setMore(result != null && result.length > 0);
                if (!boutiqueAdapter.isMore()) {
                        srl.setVisibility(View.GONE);
                        tvNomore.setVisibility(View.VISIBLE);
                        boutiqueAdapter.setFoot("没有更多数据加载");
                    return;
                }
                srl.setVisibility(View.VISIBLE);
                tvNomore.setVisibility(View.GONE);
                boutiqueAdapter.setFoot("加载更多数据");
                ArrayList<BoutiqueBean> list = ConvertUtils.array2List(result);
                switch (action) {
                    case ACITON_DOWNLOAD:
                        boutiqueAdapter.initData(list);
                        break;
                    case ACTION_PULL_DOWN_DOWNLOAD:
                        srl.setRefreshing(false);
                        tvRefresh.setVisibility(View.GONE);
                        boutiqueAdapter.initData(list);
                        break;
                    case ACTION_PULL_UP_DOWNLOAD:
                        boutiqueAdapter.addData(list);
                        break;
                }
            }
            @Override
            public void onError(String error) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                srl.setVisibility(View.GONE);
                tvNomore.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setListener() {
//        setPullUpListener();
        setPullDownListener();
    }

    private void setPullDownListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                tvRefresh.setVisibility(View.VISIBLE);
                downData(ACTION_PULL_DOWN_DOWNLOAD);
            }
        });
    }

    private void initRecyler() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow)
        );
        boutiqueBeanArrayList = new ArrayList<>();
        boutiqueAdapter = new BoutiqueAdapter(getContext(), boutiqueBeanArrayList);
        recyler.setAdapter(boutiqueAdapter);
        linearlayoutManager = new LinearLayoutManager(getContext());
        recyler.setLayoutManager(linearlayoutManager);
        recyler.addItemDecoration(new SpaceItemDecoration(12));
        recyler.setHasFixedSize(true);//自适应
        srl.setVisibility(View.VISIBLE);
        tvNomore.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boutique, container, false);
        ButterKnife.bind(this, view);
        iModelNewBoutique = new ModelBoutique();
        initRecyler();
        downData(ACITON_DOWNLOAD);
        setListener();
        return view;
    }
    @OnClick(R.id.tv_nomore)
    public void onClick() {//点击要加载数据
        downData(ACITON_DOWNLOAD);
    }
}
