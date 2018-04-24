package wb.com.cctm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.bean.ReciverBean;

/**
 * Created by wb on 2018/4/24.
 */

public class ReciverAdapter extends RecyclerView.Adapter {
    private List<ReciverBean> datas = new ArrayList<>();
    public void clear() {
        datas.clear();
    }
    public void addAll(List<ReciverBean> data) {
        this.datas.addAll(data);
    }
    public List<ReciverBean> getData() {
        return datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transfe_recoder,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReciverBean bean = datas.get(position);
        Myholder myViewHolder = (Myholder) holder;
        myViewHolder.tv_address.setText(bean.getW_ADDRESS());
        myViewHolder.tv_number.setText("+"+bean.getRECEIVE_MONEY());
        myViewHolder.tv_time.setText(bean.getCREATE_TIME());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        private TextView tv_address;
        private TextView tv_time;
        private TextView tv_number;
        public Myholder(View itemView) {
            super(itemView);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_number = itemView.findViewById(R.id.tv_number);
        }
    }
}
