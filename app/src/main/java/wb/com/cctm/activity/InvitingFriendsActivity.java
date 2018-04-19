package wb.com.cctm.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.xutils.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.ImageLoader;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.ToastUtils;
import wb.com.cctm.net.CommonCallbackImp;
import wb.com.cctm.net.FlowAPI;
import wb.com.cctm.net.MXUtils;

public class InvitingFriendsActivity extends BaseActivity {

    @BindView(R.id.tv_left_copy)
    TextView tv_left_copy;
    @BindView(R.id.tv_right_copy)
    TextView tv_right_copy;
    @BindView(R.id.iv_img_qrcode)
    ImageView iv_img_qrcode;
    private String left_str = "";
    private String right_str = "";
    private String app_url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_inviting_friends);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("邀请好友");
        ButterKnife.bind(this);
        invitation();
    }

    @OnClick({R.id.tv_left_copy,R.id.tv_right_copy})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left_copy:
                ClipboardManager clip_left = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                clip_left.setText(left_str);
                ToastUtils.toastutils("你已复制到粘贴板",InvitingFriendsActivity.this);
                break;
            case R.id.tv_right_copy:
                ClipboardManager clip_right = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                clip_right.setText(right_str);
                ToastUtils.toastutils("你已复制到粘贴板",InvitingFriendsActivity.this);
                break;
            default:
                break;
        }
    }

    private void invitation() {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.invitation);
        requestParams.addParameter("USER_NAME", SPUtils.getString(SPUtils.username));
        requestParams.addParameter("TERMINAL", "2");
        MXUtils.httpPost(requestParams,new CommonCallbackImp("INDEX - 邀请好友",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    JSONObject pd_obj = JSONObject.parseObject(pd);
                    left_str = pd_obj.getString("LEFT_URL");
                    right_str = pd_obj.getString("RIGHT_URL");
                    app_url = pd_obj.getString("APP_URL");
                    ImageLoader.load(app_url,iv_img_qrcode);
                } else {
                    ToastUtils.toastutils(message,InvitingFriendsActivity.this);
                }

            }
        });

    }
}
