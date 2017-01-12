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
    static final int TYPE_ITEM = 0;
    static final int TYPE_FOOT = 1;
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

    BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> boutiqueBeanArrayList) {
        this.context = context;
        this.boutiqueBeanArrayList = boutiqueBeanArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case TYPE_FOOT:
                view = inflater.inflate(R.layout.foot_item, null);
                viewHolder = new FootViewHolder(view);
                break;
            case TYPE_ITEM:
                view = inflater.inflate(R.layout.boutique_item, null);
                viewHolder = new BoutiqueViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOT) {
            FootViewHolder viewHolder = (FootViewHolder) holder;
            viewHolder.tvFoot.setText(getFoot());
            return;
        }
        BoutiqueViewHolder viewholder = (BoutiqueViewHolder) holder;
        viewholder.tvBoutitqueName.setText(boutiqueBeanArrayList.get(position).getName());
        viewholder.tvBoutiqueTitle.setText(boutiqueBeanArrayList.get(position).getTitle());
        viewholder.tvBoutiqueDescription.setText(boutiqueBeanArrayList.get(position).getDescription());
        ImageLoader.downloadImg(context, viewholder.ivBoutique, boutiqueBeanArrayList.get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return boutiqueBeanArrayList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOT;
        }
        return TYPE_ITEM;
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvFoot)
        TextView tvFoot;

       public  FootViewHolder(View view) {
           super(view);
           ButterKnife.bind(this, view);
        }
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
