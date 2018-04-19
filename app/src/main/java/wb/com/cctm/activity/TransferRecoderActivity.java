package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;

public class TransferRecoderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_transfer_recoder);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("转账记录");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
    }
}
