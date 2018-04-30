package wb.com.cctm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class OrderDetailActivity extends BaseActivity {
    @BindView(R.id.tv_order_name)
    TextView tv_order_name;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_all)
    TextView tv_all;
    @BindView(R.id.tv_bank_name)
    TextView tv_bank_name;
    @BindView(R.id.tv_bank_number)
    TextView tv_bank_number;
    @BindView(R.id.tv_bank_user_name)
    TextView tv_bank_user_name;
    @BindView(R.id.tv_bank_addr_name)
    TextView tv_bank_addr_name;
    @BindView(R.id.tv_wechat)
    TextView tv_wechat;
    @BindView(R.id.tv_zhifu_bao)
    TextView tv_zhifu_bao;
    @BindView(R.id.btn_contain)
    Button btn_contain;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_time)
    TextView tv_time;
    private MyInputPwdUtil myInputPwdUtil;
    private String action = "";
    @BindView(R.id.ll_tool_bar)
    LinearLayout ll_tool_bar;
    @BindView(R.id.btn_cancel)
    Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_order_detail);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("订单详情");
        ButterKnife.bind(this);
        initview();
        orderDetail();
    }

    private void initview() {
        myInputPwdUtil = new MyInputPwdUtil(OrderDetailActivity.this);
        myInputPwdUtil.getMyInputDialogBuilder().setAnimStyle(R.style.dialog_anim);
        myInputPwdUtil.setListener(new InputPwdView.InputPwdListener() {
            @Override
            public void hide() {
                myInputPwdUtil.hide();
            }
            @Override
            public void forgetPwd() {
                ToastUtils.toastutils("忘记密码",OrderDetailActivity.this);
            }

            @Override
            public void finishPwd(String pwd) {
                myInputPwdUtil.hide();
                if (action.equals("可取消")) {
                    orderCancle(pwd);
                } else if (action.equals("确认付款")){
                    pay(pwd);
                }
            }
        });
    }

    @OnClick({})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_contain:
                if (SPUtils.getString(SPUtils.safety).equals("0")) {
                    Intent intent = new Intent(OrderDetailActivity.this,SafetyPwdActivity.class);
                    startActivity(intent);
                } else {
                    action = "确认付款";
                    myInputPwdUtil.show();
                }
                break;
            case R.id.btn_cancel:
                if (SPUtils.getString(SPUtils.safety).equals("0")) {
                    Intent intent = new Intent(OrderDetailActivity.this,SafetyPwdActivity.class);
                    startActivity(intent);
                } else {
                    action = "可取消";
                    myInputPwdUtil.show();
                }
                break;
            default:
                break;
        }
    }

    private void orderDetail() {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.orderDetail);
        requestParams.addParameter("TRADE_ID", getIntent().getStringExtra("TRADE_ID"));
        MXUtils.httpPost(requestParams,new CommonCallbackImp("MARKET - 订单详情",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    tv_order_name.setText(pd_obj.getString("USER_NAME"));
                    tv_price.setText(pd_obj.getString("BUSINESS_PRICE"));
                    tv_number.setText(pd_obj.getString("BUSINESS_COUNT"));
                    tv_all.setText(pd_obj.getString("TOTAL_MONEY"));
                    tv_bank_name.setText(pd_obj.getString("BANK_NAME"));
                    tv_bank_number.setText(pd_obj.getString("BANK_NO"));
                    tv_bank_user_name.setText(pd_obj.getString("BANK_USERNAME"));
                    tv_bank_addr_name.setText(pd_obj.getString("BANK_ADDR"));
                    tv_wechat.setText(pd_obj.getString("WCHAT"));
                    tv_zhifu_bao.setText(pd_obj.getString("ALIPAY"));
                    tv_time.setText(pd_obj.getString("CREATE_TIME"));
                    String status = pd_obj.getString("STATUS");
                    if (status.equals("0")) {
                        tv_status.setText("待审核");
                    } else if (status.equals("1")) {
                        tv_status.setText("审核通过");
                    } else if (status.equals("2")) {
                        tv_status.setText("部分成交");
                    } else if (status.equals("3")) {
                        tv_status.setText("待付款");
                        ll_tool_bar.setVisibility(View.VISIBLE);
                        btn_cancel.setVisibility(View.VISIBLE);
                        btn_contain.setVisibility(View.VISIBLE);
                    } else if (status.equals("4")) {
                        tv_status.setText("已付款");
                    } else if (status.equals("5")) {
                        tv_status.setText("已成交");
                    } else if (status.equals("6")) {
                        tv_status.setText("已取消");
                    } else {
                        tv_status.setText("未知状态");
                    }
                } else {
                    ToastUtils.toastutils(message,OrderDetailActivity.this);
                }

            }
        });

    }

    private void orderCancle(String pwd) {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.orderCancle);
        requestParams.addParameter("TRADE_ID", getIntent().getStringExtra("TRADE_ID"));
        requestParams.addParameter("TYPE", "0");
        requestParams.addParameter("PASSW", pwd);
        MXUtils.httpPost(requestParams,new CommonCallbackImp("MARKET - 订单取消",requestParams, this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    ToastUtils.toastutils("取消成功",OrderDetailActivity.this);
                    // 调下刷新接口
                    finish();
                } else {
                    ToastUtils.toastutils(message,OrderDetailActivity.this);
                }
            }
        });
    }

    private void pay(String pwd) {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.pay);
        requestParams.addParameter("TRADE_ID", getIntent().getStringExtra("TRADE_ID"));
        requestParams.addParameter("STATUS", "4");
        requestParams.addParameter("PASSW", pwd);
        MXUtils.httpPost(requestParams,new CommonCallbackImp("MARKET - 订单确认收款",requestParams, this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    ToastUtils.toastutils("付款成功",OrderDetailActivity.this);
                    finish();
                } else {
                    ToastUtils.toastutils(message,OrderDetailActivity.this);
                }

            }
        });

    }
}
