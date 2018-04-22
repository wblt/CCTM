package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.bean.NoticeBean;

/**
 * Created by wb on 2018/4/22.
 */

public class AllReleaseAdapter extends RecyclerView.Adapter {
    private List<String> datas;
    private Context context;

    public AllReleaseAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_all_release,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    public void refresh(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        public Myholder(View itemView) {
            super(itemView);

        }
    }


}
