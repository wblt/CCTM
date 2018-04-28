package wb.com.cctm.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.WriterException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.BitmapUtils;
import wb.com.cctm.commons.utils.ImageLoader;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.zxing.encode.CodeCreator;

import static org.xutils.common.util.IOUtil.copy;

public class ReciveCodeActivity extends BaseActivity {

    @BindView(R.id.contentIv)
    ImageView contentIv;
    @BindView(R.id.iv_head_img)
    ImageView iv_head_img;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_copy)
    TextView tv_copy;
    @BindView(R.id.tv_nick_name)
    TextView tv_nick_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_recive_code);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("收款地址");
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String headpath = SPUtils.getString(SPUtils.headimgpath);
        if (!TextUtils.isEmpty(headpath)) {
            ImageLoader.load(headpath,iv_head_img);
        }
        tv_nick_name.setText(SPUtils.getString(SPUtils.nick_name));
    }

    private void initView() {
        try {
            String address = "0x94F9c5579Eb813065956E3832Ac4f6ff44439DF0";
            Bitmap bitmap = CodeCreator.createQRCode(address, 1000, 1000, null);
            if (bitmap != null) {
                contentIv.setImageBitmap(bitmap);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

}
