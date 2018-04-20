package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.base.OnItemClickListener;
import wb.com.cctm.bean.FrendsBean;
import wb.com.cctm.bean.NoticeBean;
import wb.com.cctm.commons.utils.ImageLoader;

/**
 * Created by wb on 2018/4/20.
 */

public class FrendsAdapter extends RecyclerView.Adapter {
    private List<FrendsBean> datas;
    private Context context;
    private OnItemClickListener<FrendsBean> listener;
    public FrendsAdapter(List<FrendsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void refresh(List<FrendsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_frends,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FrendsBean bean = datas.get(position);
        Myholder myholder = (Myholder) holder;
        myholder.tv_username.setText(bean.getUSER_NAME());
        ImageLoader.load(bean.getHEAD_URL(),myholder.iv_img);
        myholder.tv_tel.setText("tel:"+bean.getTEL());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_username;
        private TextView tv_tel;
        public Myholder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_tel = itemView.findViewById(R.id.tv_tel);
        }
    }

    public void setListener(OnItemClickListener<FrendsBean> listener) {
        this.listener = listener;
    }
}