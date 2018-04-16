package wb.com.cctm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.com.cctm.R;
import wb.com.cctm.adapter.NewsAdapter;
import wb.com.cctm.base.BaseActivity;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.recyc_list)
    RecyclerView recyc_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(this,R.layout.activity_news);
        appendTopBody(R.layout.activity_top_text);
        setTopBarTitle("新闻");
        setTopLeftDefultListener();
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        NewsAdapter newsAdapter = new NewsAdapter(stringList,NewsActivity.this);
        recyc_list.setLayoutManager(new LinearLayoutManager(this));
        recyc_list.setAdapter(newsAdapter);
    }
}
