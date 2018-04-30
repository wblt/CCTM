package wb.com.cctm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseRecyclerViewAdapter;
import wb.com.cctm.base.BaseRecyclerViewHolder;
import wb.com.cctm.bean.MybuyBean;
import wb.com.cctm.databinding.ItemMyBuyBinding;

/**
 * Created by wb on 2018/4/30.
 */

public class MybuyAdapter extends BaseRecyclerViewAdapter<MybuyBean> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(viewGroup, R.layout.item_my_buy);
    }
    private class ViewHolder extends BaseRecyclerViewHolder<MybuyBean,ItemMyBuyBinding> {

        public ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        public void onBindViewHolder(MybuyBean object, int position) {
            binding.executePendingBindings();
        }
    }


}
