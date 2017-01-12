package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.util.ImageLoader;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class BoutiqueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<BoutiqueBean> boutiqueBeanArrayList;
    Context context;
    LayoutInflater inflater;
    String foot;
    boolean isMore;
    boolean isDrag;

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
        notifyDataSetChanged();
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public boolean isDrag() {
        return isDrag;
    }

    public void setDrag(boolean drag) {
        isDrag = drag;
    }

    public BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> boutiqueBeanArrayList) {
        this.context = context;
        this.boutiqueBeanArrayList = boutiqueBeanArrayList;
        inflater = LayoutInflater.from(context);
    }
    public void initData(ArrayList<BoutiqueBean>list){
        if(boutiqueBeanArrayList!=null){
            boutiqueBeanArrayList.clear();
        }
        addData(list);
    }
    public void addData(ArrayList<BoutiqueBean>list){
       boutiqueBeanArrayList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        view = inflater.inflate(R.layout.boutique_item, null);
        viewHolder = new BoutiqueViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BoutiqueViewHolder viewholder = (BoutiqueViewHolder) holder;
        viewholder.tvBoutitqueName.setText(boutiqueBeanArrayList.get(position).getName());
        viewholder.tvBoutiqueTitle.setText(boutiqueBeanArrayList.get(position).getTitle());
        viewholder.tvBoutiqueDescription.setText(boutiqueBeanArrayList.get(position).getDescription());
        ImageLoader.downloadImg(context, viewholder.ivBoutique, boutiqueBeanArrayList.get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return boutiqueBeanArrayList.size();
    }
    
    static class BoutiqueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivBoutique)
        ImageView ivBoutique;
        @BindView(R.id.tvBoutiqueTitle)
        TextView tvBoutiqueTitle;
        @BindView(R.id.tvBoutitqueName)
        TextView tvBoutitqueName;
        @BindView(R.id.tvBoutiqueDescription)
        TextView tvBoutiqueDescription;

        public BoutiqueViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
