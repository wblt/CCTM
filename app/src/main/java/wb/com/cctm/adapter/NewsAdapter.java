package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.base.OnItemClickListener;
import wb.com.cctm.bean.NoticeBean;

/**
 * Created by wb on 2018/4/16.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private List<NoticeBean> datas;
    private Context context;
    private OnItemClickListener<NoticeBean> listener;
    public NewsAdapter(List<NoticeBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void refresh(List<NoticeBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        Myholder myViewHolder = new Myholder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final NoticeBean bean = datas.get(position);
        Myholder myholder = (Myholder) holder;
        myholder.tv_title.setText(bean.getTITLE());
        myholder.tv_content.setText(bean.getCONTENT());
        myholder.tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(bean,view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_detail;
        public Myholder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_detail = itemView.findViewById(R.id.tv_detail);
        }
    }

    public void setListener(OnItemClickListener<NoticeBean> listener) {
        this.listener = listener;
    }
}
