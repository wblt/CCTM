package wb.com.cctm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.bean.StepBean;
import wb.com.cctm.commons.utils.ImageLoader;
import wb.com.cctm.commons.utils.SPUtils;

/**
 * Created by wb on 2018/4/24.
 */

public class StepAdapter extends RecyclerView.Adapter {

    private List<StepBean> datas = new ArrayList<>();
    public void clear() {
        datas.clear();
    }
    public void addAll(List<StepBean> data) {
        this.datas.addAll(data);
    }
    public List<StepBean> getData() {
        return datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StepBean bean = datas.get(position);
        Myholder myholder = (Myholder) holder;
        myholder.tv_step_number.setText(bean.getUSER_STEP());
        myholder.tv_username.setText(SPUtils.getString(SPUtils.nick_name));
        myholder.tv_time.setText(bean.getCREATE_TIME());
        ImageLoader.load(SPUtils.getString(SPUtils.headimgpath),myholder.iv_img);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class Myholder extends RecyclerView.ViewHolder {
        private TextView tv_username;
        private ImageView iv_img;
        private TextView tv_step_number;
        private TextView tv_time;
        public Myholder(View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_step_number = itemView.findViewById(R.id.tv_step_number);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
