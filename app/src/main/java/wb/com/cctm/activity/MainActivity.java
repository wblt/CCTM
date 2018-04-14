package wb.com.cctm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.VersionUtil;
import wb.com.cctm.fragment.DeliverFragment;
import wb.com.cctm.fragment.MarketFragment;
import wb.com.cctm.fragment.MineFragment;

public class MainActivity extends BaseActivity {
    private Button[] mTabs;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    private String verName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        DeliverFragment deliverFragment = new DeliverFragment();
        MarketFragment marketFragment = new MarketFragment();
        MineFragment mineFragment = new MineFragment();
        fragments = new Fragment[] {deliverFragment,marketFragment,mineFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, deliverFragment)
                .add(R.id.fragment_container, marketFragment)
                .add(R.id.fragment_container, mineFragment)
                .hide(deliverFragment)
                .hide(marketFragment)
                .hide(mineFragment)
                .show(deliverFragment)
                .commit();
        mTabs = new Button[5];
        mTabs[0] = (Button) findViewById(R.id.btn_deliver);
        mTabs[1] = (Button) findViewById(R.id.btn_market);
        mTabs[2] = (Button) findViewById(R.id.btn_mine);
        mTabs[0].setSelected(true);
    }
    /**
     * on tab clicked
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_deliver:
                index = 0;
                break;
            case R.id.btn_market:
                index = 1;
                break;
            case R.id.btn_mine:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab selected
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }
    private void update() {
        verName = VersionUtil.getAppVersionName(this);
        //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.setIsForced(true);
        PgyUpdateManager.register(MainActivity.this, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {

            }
            @Override
            public void onUpdateAvailable(String s) {
                // 将新版本信息封装到AppBean中
                final AppBean appBean = getAppBeanFromString(s);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("版本"+verName+"更新")
                        .setMessage(appBean.getReleaseNote())
                        .setNegativeButton(
                                "确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        startDownloadTask(
                                                MainActivity.this,
                                                appBean.getDownloadURL());
                                    }
                                }).show();
            }
        });

    }
}
