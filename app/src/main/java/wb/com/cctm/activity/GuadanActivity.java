package wb.com.cctm.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.adapter.BBPageAdapter;
import wb.com.cctm.base.BaseActivity;

public class GuadanActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_guadan);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("挂卖单");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {

    }
}
