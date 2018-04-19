package wb.com.cctm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.App;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.VersionUtil;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_version_name)
    TextView tv_version_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_setting);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("设置");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initview();
    }
    private void initview() {
        tv_version_name.setText(VersionUtil.getAppVersionName(SettingActivity.this));
    }

    @OnClick({R.id.btn_logout})
    void viewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_logout:
                SPUtils.clearUser();
                intent = new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                App.getInstance().getMainActivity().finish();
                break;
            default:
                break;
        }
    }
}
