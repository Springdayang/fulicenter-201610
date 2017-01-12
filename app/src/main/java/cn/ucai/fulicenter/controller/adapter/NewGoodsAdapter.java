package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.util.ImageLoader;


/**
 * Created by Administrator on 2017/1/11 0011.
 */
public class NewGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<NewGoodsBean>list;
    LayoutInflater inflater;
    public NewGoodsAdapter(Context context, ArrayList<NewGoodsBean> list){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }
    public  void initData(ArrayList<NewGoodsBean>list){
        if(this.list!=null){
            this.list.clear();
        }

        this.list.addAll(list);
        Log.i("dayang","onBindViewHolder"+"---------------"+list.size()+"---");
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.new_goods_item,null);
        Log.i("dayang","onCreateViewHolder"+"---------------");
        RecyclerView.ViewHolder viewHolder=new GoodsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoodsViewHolder viewHolder=(GoodsViewHolder)holder;
        Log.i("dayang","onBindViewHolder"+"---------------");
        viewHolder.mTvNewGoodsPrice.setText(list.get(position).getPromotePrice());
        viewHolder.mTvNewGoodsName.setText(list.get(position).getGoodsName());
        ImageLoader.downloadImg(context,viewHolder.mIvNewGoods,list.get(position).getGoodsThumb());
    }

    @Override
    public int getItemCount() {
        Log.i("dayang","getItemCount"+"---------------"+list.size());
        return this.list.size();
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
}
