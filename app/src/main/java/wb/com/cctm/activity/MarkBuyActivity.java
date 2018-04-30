package wb.com.cctm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lmj.mypwdinputlibrary.InputPwdView;
import com.lmj.mypwdinputlibrary.MyInputPwdUtil;

import org.xutils.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.ToastUtils;
import wb.com.cctm.net.CommonCallbackImp;
import wb.com.cctm.net.FlowAPI;
import wb.com.cctm.net.MXUtils;

public class MarkBuyActivity extends BaseActivity {
    private MyInputPwdUtil myInputPwdUtil;
    @BindView(R.id.tv_sell_price)
    TextView tv_sell_price;
    @BindView(R.id.tv_sell_number)
    TextView tv_sell_number;
    @BindView(R.id.et_buy_number)
    EditText et_buy_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_mark_buy);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("买单");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        myInputPwdUtil = new MyInputPwdUtil(MarkBuyActivity.this);
        myInputPwdUtil.getMyInputDialogBuilder().setAnimStyle(R.style.dialog_anim);
        myInputPwdUtil.setListener(new InputPwdView.InputPwdListener() {
            @Override
            public void hide() {
                myInputPwdUtil.hide();
            }
            @Override
            public void forgetPwd() {
                ToastUtils.toastutils("忘记密码",MarkBuyActivity.this);
            }
            @Override
            public void finishPwd(String pwd) {
                myInputPwdUtil.hide();
                buy(pwd);
            }
        });
    }

    @OnClick({R.id.btn_commit})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                if (TextUtils.isEmpty(et_buy_number.getText().toString())) {
                    ToastUtils.toastutils("请输入买入数量",MarkBuyActivity.this);
                    return;
                }
                int sell_number = Integer.valueOf(tv_sell_number.getText().toString());
                int buy_number = Integer.valueOf(et_buy_number.getText().toString());
                if (buy_number>sell_number) {
                    ToastUtils.toastutils("买入数量大于当前卖单数量",MarkBuyActivity.this);
                    return;
                }
                if (SPUtils.getString(SPUtils.safety).equals("0")) {
                    Intent intent = new Intent(MarkBuyActivity.this,SafetyPwdActivity.class);
                    startActivity(intent);
                } else {
                    myInputPwdUtil.show();
                }
                break;
        }
    }

    private void buy(String pwd) {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.buy);
        requestParams.addParameter("TRADE_ID", getIntent().getStringExtra("TRADE_ID"));
        requestParams.addParameter("USER_NAME", SPUtils.getString(SPUtils.username));
        requestParams.addParameter("D_CURRENCY", et_buy_number.getText().toString());
        requestParams.addParameter("PASSW", pwd);
        MXUtils.httpPost(requestParams,new CommonCallbackImp("USER - 注册短信验证码",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    ToastUtils.toastutils("提交成功",MarkBuyActivity.this);
                    finish();
                } else {
                    ToastUtils.toastutils(message,MarkBuyActivity.this);
                }
            }
        });
    }

}