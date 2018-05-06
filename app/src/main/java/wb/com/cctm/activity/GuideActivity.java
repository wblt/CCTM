package wb.com.cctm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import org.xutils.common.util.LogUtil;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.guide.BGABanner;
import wb.com.cctm.commons.utils.SPUtils;


public class GuideActivity extends BaseActivity {
    private BGABanner mBannerGuideBackground;
    private BGABanner mBannerGuideForeground;
    private Button btn_guide_enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mBannerGuideBackground = (BGABanner) findViewById(R.id.banner_guide_background);
        mBannerGuideForeground = (BGABanner) findViewById(R.id.banner_guide_foreground);
        btn_guide_enter = findViewById(R.id.btn_guide_enter);
        //设置背景图
        mBannerGuideBackground.setData(R.mipmap.uoko_guide_background_1, R.mipmap.uoko_guide_background_2, R.mipmap.uoko_guide_background_3);
        /// /设置背景图对应的文案
        mBannerGuideForeground.setData(R.mipmap.uoko_guide_foreground_1, R.mipmap.uoko_guide_foreground_2, R.mipmap.uoko_guide_foreground_3);
        initListener();
    }

    private void initListener() {
        //将跳过按钮与进入按钮加入控制器设置点击事件
        mBannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                goActivity();
            }
        });
        mBannerGuideBackground.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                LogUtil.i("onPageSelected:index:"+i);
                if (i == 2) {
                    btn_guide_enter.setVisibility(View.VISIBLE);
                } else {
                    btn_guide_enter.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    private void goActivity() {
        showLoadding("请稍候...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadding();
                if (TextUtils.isEmpty(SPUtils.getString(SPUtils.isLogin))) {
                    Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },1000);
    }
}
