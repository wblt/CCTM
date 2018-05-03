package wb.com.cctm.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.File;
import java.security.NoSuchAlgorithmException;

import wb.com.cctm.App;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.BBConfig;
import wb.com.cctm.commons.utils.MD5;
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
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_main);
        App.getInstance().addActivity(this);
        initView();
        test();
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
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            initTools();
        }
    }


    public void initTools(){
        String out_file_path = BBConfig.YYW_FILE_PATH;
        File dir = new File(out_file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private void test() {
        try {
            String str  = "15388965285dec";
            String md5_str = MD5.MD5Encode(str);
            Log.i("s",md5_str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if((System.currentTimeMillis()-exitTime) > 2000){
            Toast.makeText(this,"再次点击退出应用",Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            App.getInstance().exit();
        }
    }

    //双击后退按钮关闭应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("","KKKKKKKKKKKKKKKKKKKKKKK=" + keyCode);
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(this,"再次点击退出应用",Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                App.getInstance().exit();
            }
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_HOME){
            // 不退出程序，进入后台
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
