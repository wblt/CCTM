package wb.com.cctm.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lmj.mypwdinputlibrary.InputPwdView;
import com.lmj.mypwdinputlibrary.MyInputPwdUtil;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.adapter.BBPageAdapter;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.ImageLoader;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.ToastUtils;
import wb.com.cctm.net.CommonCallbackImp;
import wb.com.cctm.net.FlowAPI;
import wb.com.cctm.net.MXUtils;

public class FinancialTransferActivity extends BaseActivity {

    @BindView(R.id.tv_d_curr)
    TextView tv_d_curr;
    @BindView(R.id.tv_t_money)
    TextView tv_t_money;
    @BindView(R.id.tv_w_energy)
    TextView tv_w_energy;
    @BindView(R.id.et_number)
    EditText et_number;
    @BindView(R.id.et_wallet_address)
    EditText et_wallet_address;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private MyInputPwdUtil myInputPwdUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_financial_transfer);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("财务转账");
        ButterKnife.bind(this);
        initview();
        sendMes();
    }

    private void initview() {
        myInputPwdUtil = new MyInputPwdUtil(this);
        myInputPwdUtil.getMyInputDialogBuilder().setAnimStyle(R.style.dialog_anim);
        myInputPwdUtil.setListener(new InputPwdView.InputPwdListener() {
            @Override
            public void hide() {
                myInputPwdUtil.hide();
            }
            @Override
            public void forgetPwd() {
                ToastUtils.toastutils("忘记密码",FinancialTransferActivity.this);
            }

            @Override
            public void finishPwd(String pwd) {
                myInputPwdUtil.hide();
                send(pwd);
            }
        });
    }

    @OnClick({R.id.btn_commit,R.id.iv_input_xx2,R.id.iv_input_xx})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                if (TextUtils.isEmpty(et_wallet_address.getText().toString())) {
                    ToastUtils.toastutils("请输入转入的钱包地址",FinancialTransferActivity.this);
                    return;
                }
                if (TextUtils.isEmpty(et_number.getText().toString())) {
                    ToastUtils.toastutils("请输入转入的数量",FinancialTransferActivity.this);
                    return;
                }
                if (et_wallet_address.getText().toString().length() != 64) {
                    ToastUtils.toastutils("钱包地址长度不正确",FinancialTransferActivity.this);
                    return;
                }
                if (SPUtils.getString(SPUtils.safety).equals("0")) {
                    Intent intent = new Intent(FinancialTransferActivity.this,SafetyPwdActivity.class);
                    startActivity(intent);
                } else {
                    myInputPwdUtil.show();
                }
                break;
            case R.id.iv_input_xx2:
                et_wallet_address.getText().clear();
                break;
            case R.id.iv_input_xx:
                et_number.getText().clear();
                break;
            default:
                break;
        }
    }

    private void sendMes() {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.sendMes);
        requestParams.addParameter("USER_NAME", SPUtils.getString(SPUtils.username));
        MXUtils.httpPost(requestParams,new CommonCallbackImp("MY - 可发送内容",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    tv_t_money.setText(pd_obj.getString("T_MONEY"));
                    tv_w_energy.setText(pd_obj.getString("W_ENERGY"));
                    tv_d_curr.setText(pd_obj.getString("D_CURRENCY"));
                } else {
                    ToastUtils.toastutils(message,FinancialTransferActivity.this);
                }

            }
        });
    }

    private void send(String pwd) {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.send);
        requestParams.addParameter("USER_NAME", SPUtils.getString(SPUtils.username));
        requestParams.addParameter("W_ADDRESS", et_wallet_address.getText().toString());
        requestParams.addParameter("S_MONEY", et_number.getText().toString());
        requestParams.addParameter("PASSW", pwd);
        MXUtils.httpPost(requestParams,new CommonCallbackImp("MY - 发送",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                } else {
                    ToastUtils.toastutils(message,FinancialTransferActivity.this);
                }
            }
        });
    }




}
