package wb.com.cctm.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.RegExpValidator;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.ToastUtils;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_re_password)
    EditText et_re_password;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.btn_get_code)
    Button btn_get_code;
    private String codeNum;     // 验证码
    private MyCount myCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_register);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("注册");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        myCount = new MyCount(60000, 1000);
    }

    @OnClick({R.id.btn_register,R.id.btn_get_code,R.id.tv_zhuce_xieyi})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_get_code:
                getcode();
                break;
            case R.id.tv_zhuce_xieyi:
                ToastUtils.toastutils("开发中",RegisterActivity.this);
                break;
            default:
                break;
        }
    }

    private void getcode() {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        String re_password = et_re_password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.toastutils("电话号码为空",RegisterActivity.this);
            return;
        }
        if (!RegExpValidator.IsHandset(username)) {
            ToastUtils.toastutils("密码为空",RegisterActivity.this);
            return;
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        if (!password.matches(regex)) {
            ToastUtils.toastutils("请输入6-15位字母、数字的密码",RegisterActivity.this);
            return;
        }
        if (!password.equals(re_password)) {
            ToastUtils.toastutils("密码输入不一致",RegisterActivity.this);
            return;
        }
        ToastUtils.toastutils("验证码发送成功",RegisterActivity.this);
        btn_get_code.setEnabled(false);
        myCount.start();
        ToastUtils.toastutils("验证码为123456",RegisterActivity.this);
    }

    private void register() {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        String re_password = et_re_password.getText().toString();
        String code = et_code.getText().toString();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.toastutils("电话号码为空",RegisterActivity.this);
            return;
        }
        if (!RegExpValidator.IsHandset(username)) {
            ToastUtils.toastutils("密码为空",RegisterActivity.this);
            return;
        }
        if (!password.equals(re_password)) {
            ToastUtils.toastutils("密码输入不一致",RegisterActivity.this);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.toastutils("验证为空",RegisterActivity.this);
            return;
        }
        if (!code.equals("123456")) {
            ToastUtils.toastutils("验证码输入错误",RegisterActivity.this);
            return;
        }
        showLoadding("注册中...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadding();
                SPUtils.putString(SPUtils.userid,username);
                SPUtils.putString(SPUtils.password,password);
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            int second = (int) (l / 1000);
            btn_get_code.setText(second + "秒");
        }

        @Override
        public void onFinish() {
            btn_get_code.setEnabled(true);
            btn_get_code.setText("获取验证码");
        }
    }
}
