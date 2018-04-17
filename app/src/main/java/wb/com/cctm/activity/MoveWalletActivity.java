package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.Code;
import wb.com.cctm.commons.utils.ToastUtils;

public class MoveWalletActivity extends BaseActivity {

    @BindView(R.id.iv_showCode)
    ImageView iv_showCode;
    private String realCode;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_number)
    EditText et_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_move_wallet);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("转入算力钱包");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        iv_showCode.setImageBitmap(Code.getInstance().createBitmap("#303030"));
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    @OnClick({R.id.btn_commit,R.id.iv_showCode})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                commite();
                break;
            case R.id.iv_showCode:
                iv_showCode.setImageBitmap(Code.getInstance().createBitmap("#303030"));
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            default:
                break;
        }
    }

    private void commite() {
        String code = et_code.getText().toString();
        String number = et_number.getText().toString();
        if (TextUtils.isEmpty(number)) {
            ToastUtils.toastutils("请输入转入数量",MoveWalletActivity.this);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.toastutils("请输入验证码",MoveWalletActivity.this);
            return;
        }
        if (!realCode.equals(code)) {
            ToastUtils.toastutils("验证码输入错误",MoveWalletActivity.this);
            return;
        }
        ToastUtils.toastutils("接口开发中",MoveWalletActivity.this);
    }

}
