package com.muzi.homepage;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MultipleItem> data;
    // private SystemBarTintManager mTintManager;
    private RelativeLayout head_search_rr;
    private SuperSwipeRefreshLayout refreshLayout;
    private int mDistanceY;
    private ViewParent head_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitUI();
        Init();
        // InitActionBar();
    }


    private void InitUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        head_search_rr = (RelativeLayout) findViewById(R.id.head_search_rr);

        //head_viewPager=(ViewPager)findViewById(R.id.head_viewPager);

        /**
         * 滑动标题栏渐变
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = head_search_rr.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    head_search_rr.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    head_search_rr.setBackgroundResource(R.color.white);
                }
            }
        });

        refreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.superlayout);
        View headView = LayoutInflater.from(MainActivity.this).inflate(R.layout.load_more_foot, null);
        refreshLayout.setHeaderView(headView);
        View footView = LayoutInflater.from(MainActivity.this).inflate(R.layout.load_more_foot, null);
        refreshLayout.setFooterView(footView);

        /**
         * 下拉刷新
         */
        refreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 500);
            }

            @Override
            public void onPullDistance(int distance) {
                if (distance > 0) {
                    head_search_rr.setVisibility(View.GONE);
                } else {
                    head_search_rr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPullEnable(boolean enable) {
            }
        });

        refreshLayout.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setLoadMore(false);
                    }
                }, 1000);
            }

            @Override
            public void onPushDistance(int distance) {

            }

            @Override
            public void onPushEnable(boolean enable) {

            }
        });


    }

    private void Init() {
        data = getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(data, MainActivity.this);
        final GridLayoutManager manager = new GridLayoutManager(this, MultipleItem.TYPE_SPAN_SIZE_20);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(multipleItemAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "position:" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

        });
    }


    private List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> data = new ArrayList<>();

        //头信息
        data.add(new MultipleItem(MultipleItem.TYPE_0,InitData(), MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //导购栏
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.chaoshi, R.mipmap.chaoshidi, "超市", MultipleItem.TYPE_SPAN_SIZE_4));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.fushi, R.mipmap.fushidi, "服饰", MultipleItem.TYPE_SPAN_SIZE_4));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.jiushui, R.mipmap.jiushuidi, "酒水", MultipleItem.TYPE_SPAN_SIZE_4));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.xinsou, R.mipmap.xinsoudi, "新手", MultipleItem.TYPE_SPAN_SIZE_4));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.gengduo, R.mipmap.quanbudi, "全部", MultipleItem.TYPE_SPAN_SIZE_4));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //横幅广告
        data.add(new MultipleItem(MultipleItem.TYPE_3, R.mipmap.banner, MultipleItem.TYPE_SPAN_SIZE_20));

        //头条、新手
        data.add(new MultipleItem(MultipleItem.TYPE_4, "新手返福利，专享188元大礼包", MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //限购、精选、特价
        data.add(new MultipleItem(MultipleItem.TYPE_5, MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //今日更新、一元抢购
        data.add(new MultipleItem(MultipleItem.TYPE_6, R.mipmap.shangpin4, R.mipmap.shangpin4di, "上线抢先看", MultipleItem.TYPE_SPAN_SIZE_5, "今日更新"));
        data.add(new MultipleItem(MultipleItem.TYPE_6, R.mipmap.shangpin5, R.mipmap.shangpin5di, "一元购电视", MultipleItem.TYPE_SPAN_SIZE_5, "一元购物"));
        data.add(new MultipleItem(MultipleItem.TYPE_6, R.mipmap.shangpin6, R.mipmap.shangpin6di, "不将就 要好用", MultipleItem.TYPE_SPAN_SIZE_5, "每日十件"));
        data.add(new MultipleItem(MultipleItem.TYPE_6, R.mipmap.shangpin7, R.mipmap.shangpin7di, "券购赢豪礼", MultipleItem.TYPE_SPAN_SIZE_5, "代金券购"));

        //宽分割线
        data.add(new MultipleItem(MultipleItem.TYPE_7, MultipleItem.TYPE_SPAN_SIZE_20));

        //猜你喜欢
        data.add(new MultipleItem(MultipleItem.TYPE_8, MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //推荐商品
        for (int i = 1; i < 10; i++) {
            data.add(new MultipleItem(MultipleItem.TYPE_9, MultipleItem.TYPE_SPAN_SIZE_10));
        }

        return data;
    }

    private List<String> InitData() {
        List<String> urls = new ArrayList<>();
        urls.add("http://img4.imgtn.bdimg.com/it/u=335554504,46277580&fm=23&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=3881482301,3131576041&fm=23&gp=0.jpg");
        urls.add("http://image.tianjimedia.com/uploadImages/2011/264/31GX4T655Q6D.jpg");
        return urls;
    }
//    private void InitActionBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            mTintManager = new SystemBarTintManager(this);
//            mTintManager.setStatusBarTintEnabled(true);
//
//            mTintManager.setStatusBarAlpha(0.0f);
//        }
//    }
//
//    @TargetApi(19)
//    private void setTranslucentStatus(boolean on) {
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }

}
