package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lmj.mypwdinputlibrary.InputPwdView;
import com.lmj.mypwdinputlibrary.MyInputPwdUtil;

import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.ToastUtils;

public class MarkBuyActivity extends BaseActivity {
    private MyInputPwdUtil myInputPwdUtil;
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
                ToastUtils.toastutils(pwd,MarkBuyActivity.this);
            }
        });
    }

}
