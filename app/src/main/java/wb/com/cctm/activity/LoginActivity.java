package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.top_left)
    ImageButton top_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_login);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("登录");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        top_left.setVisibility(View.INVISIBLE);
    }
}
