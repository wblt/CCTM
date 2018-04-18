package wb.com.cctm.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.adapter.CheckAdpter;
import wb.com.cctm.adapter.MychecAdapter;
import wb.com.cctm.base.BaseFragment;
import wb.com.cctm.base.OnItemClickListener;


public class MyCheckFragment extends BaseFragment {
    private Unbinder unbinder;
    @BindView(R.id.recyc_list)
    RecyclerView recyc_list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        appendMainBody(this,R.layout.fragment_my_check);
        unbinder = ButterKnife.bind(this,view);
        initview(view);
        return view;
    }

    private void initview(View view) {
        List<String> dates = new ArrayList<>();
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        MychecAdapter adpter = new MychecAdapter(dates,getContext());
        adpter.setListener(new OnItemClickListener<String>() {
            @Override
            public void onClick(String s, View view, int position) {
            }
        });
        recyc_list.setLayoutManager(new LinearLayoutManager(getContext()));
        recyc_list.setAdapter(adpter);
    }
}
