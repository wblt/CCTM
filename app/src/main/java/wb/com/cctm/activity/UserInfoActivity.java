package wb.com.cctm.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import ch.ielse.view.imagewatcher.ImageWatcher;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.BitmapUtils;
import wb.com.cctm.commons.utils.CommonUtils;
import wb.com.cctm.commons.utils.FileUtil;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.widget.ActionSheet;
import wb.com.cctm.net.FlowAPI;

public class UserInfoActivity extends BaseActivity {
    Dialog dialog;
    @BindView(R.id.v_image_watcher)
    ImageWatcher v_image_watcher;
    private String filepath;
    @BindView(R.id.iv_img_head)
    ImageView iv_img_head;
    private List<ImageView> groupList;
    private List<String> urlList;
    boolean isTranslucentStatus;
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
        // 配置error图标
        v_image_watcher.setErrorImageRes(R.mipmap.error_picture);
        filepath = SPUtils.getString(SPUtils.headimgpath);
        if (TextUtils.isEmpty(filepath)) {
            try {
                Bitmap bitmap = BitmapUtils.readBitMap(this,R.mipmap.head);
                filepath = FlowAPI.YYW_FILE_PATH + System.currentTimeMillis() + "save.png";
                FileUtil.saveBitmap(bitmap,filepath);
                SPUtils.putString(SPUtils.headimgpath,filepath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        groupList = new ArrayList<>();
        urlList = new ArrayList<>();
        urlList.add(filepath);
        groupList.add(iv_img_head);
    }

    @OnClick({R.id.ll_nick,R.id.tv_head_edit,R.id.iv_img_head})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_nick:
                break;
            case R.id.tv_head_edit:
                dialog.show();
                break;
            case R.id.iv_img_head:
                v_image_watcher.show(iv_img_head,groupList,urlList);
                break;
            default:
                break;
        }
    }
}
