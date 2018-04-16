package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wb.com.cctm.R;

/**
 * Created by wb on 2018/4/16.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private List<String> datas;
    private Context context;
    public NewsAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
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
