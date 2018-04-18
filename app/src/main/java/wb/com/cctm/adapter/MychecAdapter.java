package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.base.OnItemClickListener;

/**
 * Created by wb on 2018/4/18.
 */

public class MychecAdapter extends RecyclerView.Adapter {
    private List<String> datas;
    private Context context;
    private OnItemClickListener<String> listener;
    public MychecAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_my_check,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Myholder myholder = (Myholder) holder;
        final String s = datas.get(position);
        myholder.ll_pipei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(s,view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        private LinearLayout ll_pipei;
        public Myholder(View itemView) {
            super(itemView);
            ll_pipei = itemView.findViewById(R.id.ll_pipei);
        }
    }


    public void setListener(OnItemClickListener<String> listener) {
        this.listener = listener;
    }
}
