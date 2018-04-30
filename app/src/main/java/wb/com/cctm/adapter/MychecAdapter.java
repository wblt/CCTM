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
import wb.com.cctm.bean.MycheckBean;
import wb.com.cctm.databinding.ItemMyCheckBinding;

/**
 * Created by wb on 2018/4/18.
 */

public class MychecAdapter extends BaseRecyclerViewAdapter<MycheckBean> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(viewGroup,R.layout.item_my_check);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<MycheckBean,ItemMyCheckBinding> {

        public ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        public void onBindViewHolder(MycheckBean object, int position) {
            binding.executePendingBindings();
        }
    }


}
