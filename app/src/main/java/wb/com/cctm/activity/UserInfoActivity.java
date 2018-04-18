package wb.com.cctm.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import ch.ielse.view.imagewatcher.ImageWatcher;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.CommonUtils;
import wb.com.cctm.commons.widget.ActionSheet;

public class UserInfoActivity extends BaseActivity {
    Dialog dialog;
    @BindView(R.id.v_image_watcher)
    ImageWatcher v_image_watcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_user_info);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("个人信息");
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        dialog = ActionSheet.showSheet(this,R.layout.actionsheet_photo);
        TextView cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
    }

    @OnClick({R.id.ll_nick,R.id.tv_head_edit})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_nick:
                break;
            case R.id.tv_head_edit:
                dialog.show();
                break;
            default:
                break;
        }
    }
}
