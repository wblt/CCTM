package wb.com.cctm.activity;

import android.icu.math.BigDecimal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.xutils.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.Code;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.ToastUtils;
import wb.com.cctm.net.CommonCallbackImp;
import wb.com.cctm.net.FlowAPI;
import wb.com.cctm.net.MXUtils;

public class MoveWalletActivity extends BaseActivity {

    @BindView(R.id.iv_showCode)
    ImageView iv_showCode;
    private String realCode;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_number)
    EditText et_number;
    @BindView(R.id.tv_s_curr)
    TextView tv_s_curr;
    @BindView(R.id.tv_d_curr)
    TextView tv_d_curr;
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
        tv_d_curr.setText(getIntent().getStringExtra("D_CURRENCY"));
        tv_s_curr.setText(getIntent().getStringExtra("S_CURRENCY"));
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
        BigDecimal d = new BigDecimal(number.toString());
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.transferred);
        requestParams.addParameter("USER_NAME", SPUtils.getString(SPUtils.username));
        requestParams.addParameter("D_CURRENCY",d);
        MXUtils.httpPost(requestParams,new CommonCallbackImp("USER - 注册短信验证码",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    ToastUtils.toastutils("提交成功",MoveWalletActivity.this);
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    tv_d_curr.setText(pd_obj.getString("D_CURRENCY"));
                    tv_s_curr.setText(pd_obj.getString("S_CURRENCY"));
                    finish();
                } else {
                    ToastUtils.toastutils(message,MoveWalletActivity.this);
                }

            }
        });
    }



}
