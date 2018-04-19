package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.adapter.NewsAdapter;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.base.OnItemClickListener;
import wb.com.cctm.bean.NoticeBean;
import wb.com.cctm.commons.utils.ToastUtils;
import wb.com.cctm.net.CommonCallbackImp;
import wb.com.cctm.net.FlowAPI;
import wb.com.cctm.net.MXUtils;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.recyc_list)
    RecyclerView recyc_list;
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_news);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("公告");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initview();
        notice();
    }

    private void recyc_notice(List<NoticeBean> noticeBeen) {
        if (noticeBeen == null) {
            return;
        }
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(noticeBeen,NewsActivity.this);
        }
        newsAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onClick(Object o, View view, int position) {
                ToastUtils.toastutils("开发中",NewsActivity.this);
            }
        });
        recyc_list.setLayoutManager(new LinearLayoutManager(this));
        recyc_list.setAdapter(newsAdapter);
    }

    private void initview() {

    }
    private void notice() {
        RequestParams requestParams= FlowAPI.getRequestParams(FlowAPI.notice);
        MXUtils.httpPost(requestParams,new CommonCallbackImp("USER - 注册短信验证码",requestParams,this){
            @Override
            public void onSuccess(String data) {
                super.onSuccess(data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                String result = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (result.equals(FlowAPI.SUCCEED)) {
                    String pd = jsonObject.getString("pd");
                    List<NoticeBean> beanList = JSONArray.parseArray(pd,NoticeBean.class);
                    recyc_notice(beanList);
                } else {
                    ToastUtils.toastutils(message,NewsActivity.this);
                }

            }
        });
    }
}
