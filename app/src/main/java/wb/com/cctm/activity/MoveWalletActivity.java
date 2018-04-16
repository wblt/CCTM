package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.Code;

public class MoveWalletActivity extends BaseActivity {

    @BindView(R.id.iv_showCode)
    ImageView iv_showCode;
    private String realCode;
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

}
