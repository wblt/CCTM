package wb.com.cctm.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import wb.com.cctm.R;
import wb.com.cctm.base.BaseActivity;
import wb.com.cctm.fragment.DeliverFragment;
import wb.com.cctm.fragment.MarketFragment;
import wb.com.cctm.fragment.MineFragment;

public class MainActivity extends BaseActivity {
    private Button[] mTabs;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        DeliverFragment deliverFragment = new DeliverFragment();
        MarketFragment marketFragment = new MarketFragment();
        MineFragment mineFragment = new MineFragment();
        fragments = new Fragment[] {deliverFragment,marketFragment,mineFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, deliverFragment)
                .add(R.id.fragment_container, marketFragment)
                .add(R.id.fragment_container, mineFragment)
                .hide(deliverFragment)
                .hide(marketFragment)
                .hide(mineFragment)
                .show(deliverFragment)
                .commit();
        mTabs = new Button[5];
        mTabs[0] = (Button) findViewById(R.id.btn_deliver);
        mTabs[1] = (Button) findViewById(R.id.btn_market);
        mTabs[2] = (Button) findViewById(R.id.btn_mine);
        mTabs[0].setSelected(true);
    }
    /**
     * on tab clicked
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_deliver:
                index = 0;
                break;
            case R.id.btn_market:
                index = 1;
                break;
            case R.id.btn_mine:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab selected
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
}
