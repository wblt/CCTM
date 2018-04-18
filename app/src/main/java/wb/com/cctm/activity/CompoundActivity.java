package wb.com.cctm.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;

public class CompoundActivity extends BaseActivity {

    @BindView(R.id.ll_fuli)
    LinearLayout ll_fuli;
    @BindView(R.id.ll_stop_fuli)
    LinearLayout ll_stop_fuli;
    @BindView(R.id.img_fuli_gou)
    ImageView img_fuli_gou;
    @BindView(R.id.img_stop_gou)
    ImageView img_stop_gou;
    private String flag = "0";
    private String lastflag = "0";
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_compound);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("复利");
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        updatastatus();
    }

    @OnClick({R.id.ll_fuli,R.id.ll_stop_fuli,R.id.btn_commit})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_fuli:
                flag = "0";
                updatastatus();
                break;
            case R.id.ll_stop_fuli:
                flag = "1";
                updatastatus();
                break;
            case R.id.btn_commit:
                showLoadding("请稍候...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoadding();
                        lastflag = flag;
                        btn_commit.setBackgroundResource(0);
                        btn_commit.setBackgroundResource(R.drawable.button_round_gray);
                    }
                },2000);
                break;
            default:
                break;
        }
    }

    private void updatastatus() {
        if (flag.equals("0")) {
            img_fuli_gou.setVisibility(View.VISIBLE);
            img_stop_gou.setVisibility(View.INVISIBLE);
            ll_fuli.setBackgroundColor(getResources().getColor(R.color.bottom_color));
            ll_stop_fuli.setBackgroundColor(getResources().getColor(R.color.mainPrimary));
        } else {
            img_fuli_gou.setVisibility(View.INVISIBLE);
            img_stop_gou.setVisibility(View.VISIBLE);
            ll_fuli.setBackgroundColor(getResources().getColor(R.color.mainPrimary));
            ll_stop_fuli.setBackgroundColor(getResources().getColor(R.color.bottom_color));
        }
        if (lastflag.equals(flag)) {
            btn_commit.setBackgroundResource(0);
            btn_commit.setBackgroundResource(R.drawable.button_round_gray);
        } else {
            btn_commit.setBackgroundResource(0);
            btn_commit.setBackgroundResource(R.drawable.button_round_golden);
        }
    }

}