package wb.com.cctm.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import ch.ielse.view.imagewatcher.ImageWatcher;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.commons.utils.BBConfig;
import wb.com.cctm.commons.utils.BitmapUtils;
import wb.com.cctm.commons.utils.CommonUtils;
import wb.com.cctm.commons.utils.FileUtil;
import wb.com.cctm.commons.utils.GlideImageLoader;
import wb.com.cctm.commons.utils.ImageLoader;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.widget.ActionSheet;

public class UserInfoActivity extends BaseActivity {
    Dialog dialog;
    @BindView(R.id.v_image_watcher)
    ImageWatcher v_image_watcher;
    private String filepath;
    @BindView(R.id.iv_img_head)
    ImageView iv_img_head;
    private List<ImageView> groupList;
    private List<String> urlList;
    private List<ImageItem> images;
    public static final int REQUEST_CODE_SELECT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_user_info);
        appendTopBody(R.layout.activity_top_text);
        setTopLeftDefultListener();
        setTopBarTitle("个人信息");
        ButterKnife.bind(this);
        initView();
        initImagePicker();
    }

    @Override
    public void onResume() {
        super.onResume();
        String headpath = SPUtils.getString(SPUtils.headimgpath);
        if (!TextUtils.isEmpty(headpath)) {
            ImageLoader.load(headpath,iv_img_head);
        }
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
        TextView take_picture = dialog.findViewById(R.id.take_picture);
        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent = new Intent(UserInfoActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }
        });
        TextView choose_local = dialog.findViewById(R.id.choose_local);
        choose_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent1 = new Intent(UserInfoActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });

        // 配置error图标
        v_image_watcher.setErrorImageRes(R.mipmap.error_picture);
        filepath = SPUtils.getString(SPUtils.headimgpath);
        if (TextUtils.isEmpty(filepath)) {
            try {
                Bitmap bitmap = BitmapUtils.readBitMap(this,R.mipmap.head);
                filepath = BBConfig.YYW_FILE_PATH + System.currentTimeMillis() + "save.png";
                FileUtil.saveBitmap(bitmap,filepath);
                SPUtils.putString(SPUtils.headimgpath,filepath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        groupList = new ArrayList<>();
        urlList = new ArrayList<>();
        groupList.add(iv_img_head);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setMultiMode(false);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
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
                urlList.clear();
                urlList.add(SPUtils.getString(SPUtils.headimgpath));
                v_image_watcher.show(iv_img_head,groupList,urlList);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size()>0) {
                    ImageItem imageItem = images.get(0);
                    ImagePicker.getInstance().getImageLoader().displayImage(UserInfoActivity.this, imageItem.path, iv_img_head, 0, 0);
                    SPUtils.putString(SPUtils.headimgpath,imageItem.path);
                }
            }
        }
    }


}
