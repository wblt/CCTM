package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_forgot_password);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("忘记密码");
        setTopLeftDefultListener();
    }
}
