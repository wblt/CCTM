package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.adapter.AllReleaseAdapter;
import wb.com.cctm.base.BaseActivity;

public class AllReleaseActivity extends BaseActivity {
    @BindView(R.id.recyc_list)
    RecyclerView recyc_list;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.ll_no_data)
    LinearLayout ll_no_data;
    @BindView(R.id.sm_refreshLayout)
    SmartRefreshLayout sm_refreshLayout;
    private AllReleaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_all_release);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("全部记录");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initView();
    }

    private void recyc_init() {
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        if (adapter == null) {
            adapter = new AllReleaseAdapter(stringList,this);
            recyc_list.setLayoutManager(new LinearLayoutManager(this));
            recyc_list.setAdapter(adapter);
        }
    }

    private void initView() {
        recyc_init();
    }
}
