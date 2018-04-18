package wb.com.cctm.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.ToastUtils;

public class InvitingFriendsActivity extends BaseActivity {

    @BindView(R.id.tv_left_copy)
    TextView tv_left_copy;
    @BindView(R.id.tv_right_copy)
    TextView tv_right_copy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_inviting_friends);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("邀请好友");
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_left_copy,R.id.tv_right_copy})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left_copy:
                ClipboardManager clip_left = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                clip_left.setText(tv_left_copy.getText());
                ToastUtils.toastutils("你已复制到粘贴板",InvitingFriendsActivity.this);
                break;
            case R.id.tv_right_copy:
                ClipboardManager clip_right = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                clip_right.setText(tv_right_copy.getText());
                ToastUtils.toastutils("你已复制到粘贴板",InvitingFriendsActivity.this);
                break;
            default:
                break;
        }
    }
}
