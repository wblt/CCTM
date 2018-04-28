package wb.com.cctm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseRecyclerViewAdapter;
import wb.com.cctm.base.BaseRecyclerViewHolder;
import wb.com.cctm.base.OnItemClickListener;
import wb.com.cctm.databinding.ItemChecBinding;

/**
 * Created by wb on 2018/4/14.
 */

public class CheckAdpter extends BaseRecyclerViewAdapter<String>{

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(viewGroup,R.layout.item_chec);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<String,ItemChecBinding> {

        public ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }
        @Override
        public void onBindViewHolder(final String object, final int position) {
            binding.executePendingBindings();
            binding.llPipei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(object,view,position);
                    }
                }
            });
        }
    }
}
