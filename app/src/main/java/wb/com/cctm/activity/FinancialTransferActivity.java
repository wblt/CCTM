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

public class FinancialTransferActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_financial_transfer);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("财务转账");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {

    }
}
