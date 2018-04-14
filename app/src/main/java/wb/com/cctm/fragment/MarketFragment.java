package wb.com.cctm.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.adapter.MarketPageAdapter;
import wb.com.cctm.base.BaseFragment;

public class MarketFragment extends BaseFragment {
    @BindView(R.id.top_left)
    ImageButton top_left;
    private Unbinder unbinder;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.mvp)
    ViewPager mvp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        appendMainBody(this,R.layout.fragment_market);
        appendTopBody(R.layout.activity_top_icon);
        setTopBarTitle("市场");
        unbinder = ButterKnife.bind(this,view);
        initview(view);
        return view;
    }

    private void initview(View view) {
        top_left.setVisibility(View.INVISIBLE);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new CheckFragment());
        mFragmentList.add(new CheckFragment());
        mTitleList = new ArrayList<>();
        mTitleList.add("买单市场");
        mTitleList.add("卖单市场");
        mvp.setAdapter(new MarketPageAdapter(getFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        tab.setTabMode(TabLayout.MODE_FIXED);
        //将tablayout与fragment关联
        tab.setupWithViewPager(mvp);
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
