package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.bean.AllReleaseBean;
import wb.com.cctm.bean.NoticeBean;

/**
 * Created by wb on 2018/4/22.
 */

public class AllReleaseAdapter extends RecyclerView.Adapter {
    private List<AllReleaseBean> datas = new ArrayList<>();
    public void clear() {
        datas.clear();
    }
    public void addAll(List<AllReleaseBean> data) {
        this.datas.addAll(data);
    }
    public List<AllReleaseBean> getData() {
        return datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_release,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllReleaseBean bean = datas.get(position);
        Myholder myholder = (Myholder) holder;
        myholder.tv_big.setText(bean.getBIG_CURRENCY());
        myholder.tv_small.setText(bean.getSMALL_CURRENCY());
        myholder.tv_jd.setText(bean.getJD_CURRENCY());
        myholder.tv_step.setText(bean.getSTEP_CURRENCY());
        myholder.tv_time.setText(bean.getCREATE_TIME());
        myholder.tv_today.setText(bean.getCALCULATE_MONEY());
        myholder.tv_smart.setText(bean.getSTATIC_CURRENCY());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        private TextView tv_today;
        private TextView tv_time;
        private TextView tv_smart;
        private TextView tv_big;
        private TextView tv_small;
        private TextView tv_step;
        private TextView tv_jd;

        public Myholder(View itemView) {
            super(itemView);
            tv_today = itemView.findViewById(R.id.tv_today);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_smart = itemView.findViewById(R.id.tv_smart);
            tv_big = itemView.findViewById(R.id.tv_big);
            tv_small = itemView.findViewById(R.id.tv_small);
            tv_step = itemView.findViewById(R.id.tv_step);
            tv_jd = itemView.findViewById(R.id.tv_jd);
        }
    }


}
