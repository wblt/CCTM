package wb.com.cctm.fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.activity.InvitingFriendsActivity;
import wb.com.cctm.activity.MoveWalletActivity;
import wb.com.cctm.activity.NewsActivity;
import wb.com.cctm.activity.WalletRecordActivity;
import wb.com.cctm.base.BaseFragment;
import wb.com.cctm.commons.step.UpdateUiCallBack;
import wb.com.cctm.commons.step.service.StepService;
import wb.com.cctm.commons.step.utils.SharedPreferencesUtils;
import wb.com.cctm.commons.step.view.StepArcView;
import wb.com.cctm.commons.utils.ToastUtils;

public class DeliverFragment extends BaseFragment {

    @BindView(R.id.tv_look_friends)
    TextView tv_look_friends;
    Unbinder unbinder;
    @BindView(R.id.cc)
    StepArcView cc;
    private SharedPreferencesUtils sp;
    @BindView(R.id.loadingIv)
    ImageView loadingIv;
    private AnimationDrawable mAnimation;
    @BindView(R.id.ll_look_friende)
    LinearLayout ll_look_friende;
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
        loadingIv.setBackgroundResource(R.drawable.frame2);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) loadingIv.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        loadingIv.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
        initData();
    }

    private void initData() {
        sp = new SharedPreferencesUtils(getActivity());
        //获取用户设置的计划锻炼步数，没有设置过的话默认7000
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        //设置当前步数为0
        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 0);
//        tv_isSupport.setText("计步中...");
        setupService();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
        if (isBind) {
            getActivity().unbindService(conn);
        }
    }

    @OnClick({R.id.ll_invate_friends,R.id.xingfeng,R.id.ll_move_wallet,R.id.ll_change_wallet})
    void viewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_invate_friends:
                intent = new Intent(getActivity(), InvitingFriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.xingfeng:
                intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_move_wallet:
                intent = new Intent(getActivity(), MoveWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_look_friende:
                ToastUtils.toastutils("开发中",getContext());
                break;
            case R.id.ll_change_wallet:
                intent = new Intent(getActivity(), WalletRecordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private boolean isBind = false;
    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(getActivity(), StepService.class);
        isBind = getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
    }

    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            //设置初始化数据
            String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
            cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepService.getStepCount());
            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
                    cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepCount);
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
