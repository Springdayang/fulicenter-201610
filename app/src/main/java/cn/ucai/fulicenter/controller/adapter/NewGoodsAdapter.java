package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.util.ImageLoader;
import cn.ucai.fulicenter.view.MFGT;


/**
 * Created by Administrator on 2017/1/11 0011.
 */
public class NewGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<NewGoodsBean>list;
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

    public NewGoodsAdapter(Context context, ArrayList<NewGoodsBean> list){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }
    public  void initData(ArrayList<NewGoodsBean>list){
        if(this.list!=null){
            this.list.clear();
        }
            addData(list);
    }
    public void addData(ArrayList<NewGoodsBean>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case I.TYPE_ITEM:
                View view=inflater.inflate(R.layout.new_goods_item,null);
                viewHolder=new GoodsViewHolder(view);
                break;
            case I.TYPE_FOOTER:
                View view1=inflater.inflate(R.layout.foot_item,null);
                viewHolder=new FootsViewHolder(view1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==I.TYPE_FOOTER){
            FootsViewHolder viewHolder=(FootsViewHolder)holder;
            viewHolder.tvFoot.setText(getFoot());
            return ;
        }
        GoodsViewHolder viewHolder=(GoodsViewHolder)holder;
        viewHolder.mTvNewGoodsPrice.setText(list.get(position).getPromotePrice());
        viewHolder.mTvNewGoodsName.setText(list.get(position).getGoodsName());
        ImageLoader.downloadImg(context,viewHolder.mIvNewGoods,list.get(position).getGoodsThumb());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.gotoGoodsDetail(context,list.get(position).getGoodsId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return I.TYPE_FOOTER;
        }
        return I.TYPE_ITEM;
    }

    static class GoodsViewHolder extends RecyclerView.ViewHolder{
        ImageView mIvNewGoods;
        TextView mTvNewGoodsName;
        TextView mTvNewGoodsPrice;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            mIvNewGoods= (ImageView) itemView.findViewById(R.id.ivNewGoods);
            mTvNewGoodsName= (TextView) itemView.findViewById(R.id.tvNewGoodsName);
            mTvNewGoodsPrice= (TextView) itemView.findViewById(R.id.tvNewGoodsPrice);
        }
    }
    static class FootsViewHolder extends RecyclerView.ViewHolder{
        TextView tvFoot;
        public FootsViewHolder(View itemView) {
            super(itemView);
            tvFoot= (TextView) itemView.findViewById(R.id.tvFoot);
        }
    }
}
