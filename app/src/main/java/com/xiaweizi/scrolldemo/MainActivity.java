package com.xiaweizi.scrolldemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.xiaweizi.scrolldemo.PullToRefreshLayout.SUCCEED;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PullToRefreshLayout refreshLayout;
    private MyCoordinatorLayout coordinatorLayout;
    private int verticalOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRefreshLayout();
        mViewPager = findViewById(R.id.viewPager);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                MainActivity.this.verticalOffset = verticalOffset;
            }
        });
        findViewById(R.id.scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TouchEvent.setMoveToBottom(-verticalOffset, MainActivity.this);
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> data = new ArrayList<>();
        String[] titles = new String[]{"fragment1", "fragment2", "fragment3"};
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            for (int i = 0; i < 3; i++) {
                data.add(new ContentFragment());
            }
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private void initRefreshLayout() {
        refreshLayout = findViewById(R.id.pull_refresh);
        refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.refreshFinish(SUCCEED);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });
    }
}
