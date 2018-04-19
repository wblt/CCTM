package wb.com.cctm.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.activity.CompoundActivity;
import wb.com.cctm.activity.MyorderActivity;
import wb.com.cctm.activity.SettingActivity;
import wb.com.cctm.activity.UserInfoActivity;
import wb.com.cctm.base.BaseFragment;
import wb.com.cctm.commons.utils.ImageLoader;
import wb.com.cctm.commons.utils.SPUtils;

public class MineFragment extends BaseFragment {

    @BindView(R.id.top_right_icon)
    ImageButton top_right_icon;
    @BindView(R.id.top_left)
    ImageButton top_left;
    private Unbinder unbinder;
    @BindView(R.id.iv_head_img)
    ImageView iv_head_img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        appendMainBody(this,R.layout.fragment_mine);
        appendTopBody(R.layout.activity_top_icon);
        setTopBarTitle("我的");
        unbinder = ButterKnife.bind(this,view);
        initview(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String headpath = SPUtils.getString(SPUtils.headimgpath);
        if (!TextUtils.isEmpty(headpath)) {
            ImageLoader.load(headpath,iv_head_img);
        }
    }

    private void initview(View view) {
        top_left.setVisibility(View.INVISIBLE);
        top_right_icon.setImageResource(R.mipmap.setting_icon);
        top_right_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.ll_user_info,R.id.ll_my_order,R.id.ll_fuli_setting})
    void viewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_user_info:
                intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_order:
                intent = new Intent(getActivity(), MyorderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_fuli_setting:
                intent = new Intent(getActivity(), CompoundActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
