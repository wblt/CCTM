package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.xutils.http.RequestParams;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.adapter.FrendsAdapter;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.bean.FrendsBean;
import wb.com.cctm.commons.utils.SPUtils;
import wb.com.cctm.commons.utils.ToastUtils;
import wb.com.cctm.net.CommonCallbackImp;
import wb.com.cctm.net.FlowAPI;
import wb.com.cctm.net.MXUtils;

public class FrendsActivity extends BaseActivity {

    @BindView(R.id.recyc_list)
    RecyclerView recyc_list;
    private FrendsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_frends);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("我的好友");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        friends();
    }

    private void recyc_frends(List<FrendsBean> list) {
        if (adapter == null) {
            adapter = new FrendsAdapter(list,FrendsActivity.this);
        }
        recyc_list.setLayoutManager(new LinearLayoutManager(this));
        recyc_list.setAdapter(adapter);
    }

    private void friends() {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.friends);
        requestParams.addParameter("USER_NAME", SPUtils.getString(SPUtils.username));
        MXUtils.httpPost(requestParams,new CommonCallbackImp("INDEX - 我的好友",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    List<FrendsBean> beanList = JSONArray.parseArray(pd,FrendsBean.class);
                    recyc_frends(beanList);
                } else {
                    ToastUtils.toastutils(message,FrendsActivity.this);
                }

            }
        });
    }
}
