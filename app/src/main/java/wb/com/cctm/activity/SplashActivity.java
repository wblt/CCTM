package wb.com.cctm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import wb.com.cctm.R;
import wb.com.cctm.commons.utils.SPUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (SPUtils.getString(SPUtils.isLogin).equals("0")) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
