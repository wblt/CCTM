package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.ToastUtils;

public class WalletRecordActivity extends BaseActivity {

    @BindView(R.id.tv_look_all)
    TextView tv_look_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_wallet_record);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("释放记录");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {

    }

    @OnClick({R.id.tv_look_all})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_look_all:
                ToastUtils.toastutils("开发中",WalletRecordActivity.this);
                break;
            default:
                break;
        }
    }
}

