package wb.com.cctm.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.adapter.BBPageAdapter;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.fragment.OutChangeFragment;
import wb.com.cctm.fragment.OutenergyFragment;

public class FinancialTransferActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.mvp)
    ViewPager mvp;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_financial_transfer);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("转账");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new OutenergyFragment());
        mFragmentList.add(new OutChangeFragment());
        mTitleList = new ArrayList<>();
        mTitleList.add("转出能量包");
        mTitleList.add("转出零钱包");
        mvp.setAdapter(new BBPageAdapter(getSupportFragmentManager(),mFragmentList,mTitleList));
        //将tablayout与fragment关联
        tab.setTabMode(TabLayout.MODE_FIXED);
        //将tablayout与fragment关联
        tab.setupWithViewPager(mvp);
    }
}
