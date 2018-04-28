package wb.com.cctm.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmj.mypwdinputlibrary.InputPwdView;
import com.lmj.mypwdinputlibrary.MyInputPwdUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wb.com.cctm.R;
import wb.com.cctm.adapter.CheckAdpter;
import wb.com.cctm.base.BaseFragment;
import wb.com.cctm.base.OnItemClickListener;
import wb.com.cctm.commons.utils.ToastUtils;


public class CheckFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recyc_list)
    RecyclerView recyc_list;
    private MyInputPwdUtil myInputPwdUtil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        appendMainBody(this,R.layout.fragment_check);
        unbinder = ButterKnife.bind(this,view);
        initview(view);
        return view;
    }

    private void initview(View view) {
        List<String> dates = new ArrayList<>();
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        CheckAdpter adpter = new CheckAdpter();
        adpter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onClick(String s, View view, int position) {
                myInputPwdUtil.show();
            }
        });
        recyc_list.setLayoutManager(new LinearLayoutManager(getContext()));
        recyc_list.setAdapter(adpter);
        myInputPwdUtil = new MyInputPwdUtil(getActivity());
        myInputPwdUtil.getMyInputDialogBuilder().setAnimStyle(R.style.dialog_anim);
        myInputPwdUtil.setListener(new InputPwdView.InputPwdListener() {
            @Override
            public void hide() {
                myInputPwdUtil.hide();
            }

            @Override
            public void forgetPwd() {
                ToastUtils.toastutils("忘记密码",getContext());
            }

            @Override
            public void finishPwd(String pwd) {
                ToastUtils.toastutils(pwd,getContext());
            }
        });
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
