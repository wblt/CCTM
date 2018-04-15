package wb.com.cctm.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.activity.SettingActivity;
import wb.com.cctm.base.BaseFragment;

public class MineFragment extends BaseFragment {

    @BindView(R.id.top_right_icon)
    ImageButton top_right_icon;
    @BindView(R.id.top_left)
    ImageButton top_left;
    private Unbinder unbinder;
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




    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
