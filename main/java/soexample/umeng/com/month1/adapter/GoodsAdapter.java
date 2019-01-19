package soexample.umeng.com.month1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.month1.R;
import soexample.umeng.com.month1.bean.GoodsBean;
import soexample.umeng.com.month1.bean.InfoBean;
import soexample.umeng.com.month1.weight.JiaJianView;

/**
 * author:author${朱佳华}
 * data:2019/1/18
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private List<GoodsBean.DataBean> list;
    private Context context;

    public GoodsAdapter(List<GoodsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapter.ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImages();
        String replace = images.replace("https", "http");
        String[] split = replace.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.img);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText("￥"+list.get(i).getPrice());

        //给num赋值
        viewHolder.jiajianview.setCount(list.get(i).getPid());
        viewHolder.jiajianview.setJiaJianNumChange(new JiaJianView.JiaJianCallBack() {
            @Override
            public void setNumChange(int count) {
                callBack.setNum(i,count);
            }
        });

        //给ckb赋值
        viewHolder.ckb.setChecked(list.get(i).isWeatherCheck());
        viewHolder.ckb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.setChecked(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox ckb;
        private ImageView img;
        private TextView title;
        private TextView price;
        private JiaJianView jiajianview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ckb = itemView.findViewById(R.id.ckb);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            jiajianview = itemView.findViewById(R.id.jiajianview);
        }
    }



    public void setGoodsNumber(int position,int num){
        list.get(position).setPid(num);
    }
    //判断遍历商品是否被选中
    public boolean isItemGoodChecked(int position){
        boolean checked = list.get(position).isWeatherCheck();
        if (checked){
            return true;
        }
        return false;
    }
    //设置点击
    public void setItemGoodChecked(int position,boolean isChecked){
        GoodsBean.DataBean dataBean = list.get(position);
        dataBean.setWeatherCheck(isChecked);
    }
    //全选反选  查看遍历
    public boolean isAllGoods(){
        boolean boo=true;
        for (int i = 0; i < list.size(); i++) {
            GoodsBean.DataBean dataBean = list.get(i);
            if (!dataBean.isWeatherCheck()){
                boo=false;
            }
        }
        return  boo;
    }
    //全选反选
    public void setAllGoodsIsChecked(boolean isChecked){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWeatherCheck(isChecked);
        }
    }

    //计算所有商品的价格
    public double getAllGoodsPrice(){
        double allPrice=0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isWeatherCheck()){
                allPrice=list.get(i).getPid()*list.get(i).getPrice();
            }
        }
        return allPrice;
    }

    //接口回调
    public interface CallBack{
        void setChecked(View view,int position);  //ckb
        void setNum(int position,int num);
    }
    private CallBack callBack;
    public void setOnLinstener(CallBack callBack){
        this.callBack = callBack;
    }
}
