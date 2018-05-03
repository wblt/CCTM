package wb.com.cctm.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.BBConfig;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.VersionUtil;
import wb.com.cctm.net.FlowAPI;

public class SplashActivity extends BaseActivity {
    private ProgressDialog pBar;
    private String filepath;
    private String verName;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 检测权限
        int permission = ActivityCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            initTools();
        }

        // 获取版本信息
        getNewVersion();
    }

    private void getNewVersion() {
        showUpdateDialog();
    }

    private void showUpdateDialog() {
        verName = VersionUtil.getAppVersionName(SplashActivity.this);
        new AlertDialog.Builder(SplashActivity.this)
                .setTitle("软件更新")
                .setCancelable(false)
                .setMessage("当前版本"+verName+",发现有新版本请及时更新")
                .setNegativeButton(
                        "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which) {
                                int permission = ActivityCompat.checkSelfPermission(SplashActivity.this,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                                if (permission != PackageManager.PERMISSION_GRANTED) {
                                    // We don't have permission so prompt the user
                                    ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS_STORAGE,
                                            REQUEST_EXTERNAL_STORAGE);
                                } else {
                                    String downurl = "http://gyxz.hwm6b6.cn/a31/rj_hm1/weixin6.6.5.apk";
                                    downFile(downurl);
                                }
                            }
                        }).show();
    }

    private void downFile(String downurl) {
        pBar = new ProgressDialog(SplashActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
        pBar.setProgress(0);
        pBar.setCancelable(false);
        pBar.show();
        String apkName = downurl.substring(downurl.lastIndexOf("/") + 1);// 接口名称
        filepath = BBConfig.YYW_FILE_PATH + apkName;
        RequestParams requestParams = new RequestParams(downurl);
        requestParams.setSaveFilePath(filepath);
        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Toast.makeText(SplashActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                installApk(filepath);
                pBar.dismiss();
                pBar = null;
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(SplashActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
                pBar.dismiss();
                pBar = null;
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
            @Override
            public void onWaiting() {

            }
            @Override
            public void onStarted() {

            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                System.out.println("downloadProgress -- " + total + "  " + current);
                int p = (int) (((float) current / total) * 100);
                pBar.setProgress(p);
            }
        });
    }

    private void installApk(String apkpath) {
        Log.i("","FFFFFFFFFFFFFFFFF=" + apkpath);
        File file = new File(apkpath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            String auth =  getApplication().getPackageName()+".fileProvider";
            Uri apkUri = FileProvider.getUriForFile(SplashActivity.this, auth, file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    private void initTools(){
        String out_file_path = BBConfig.YYW_FILE_PATH;
        File dir = new File(out_file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private void goActivity() {
        if (TextUtils.isEmpty(SPUtils.getString(SPUtils.isLogin))) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
