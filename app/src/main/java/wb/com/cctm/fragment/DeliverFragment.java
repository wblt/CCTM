package wb.com.cctm.fragment;
import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.base.BaseFragment;

public class DeliverFragment extends BaseFragment {

    @BindView(R.id.tv_look_friends)
    TextView tv_look_friends;
    Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        appendMainBody(this,R.layout.fragment_deliver);
        unbinder = ButterKnife.bind(this,view);
        initview(view);
        return view;
    }

    private void initview(View view) {
        tv_look_friends.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_look_friends.getPaint().setAntiAlias(true);//抗锯齿
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
