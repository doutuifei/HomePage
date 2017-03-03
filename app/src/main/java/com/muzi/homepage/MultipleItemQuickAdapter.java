package com.muzi.homepage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 李鹏 on 2017/2/23.
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Context context;
    private ViewPager viewPager;
    private ImageView[] mImageView;
    private LinearLayout mIndicator;
    private MyViewPagerAdapter mAdapter;
    List<String> url;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public MultipleItemQuickAdapter(List<MultipleItem> data, Context context) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.TYPE_0, R.layout.head);
        addItemType(MultipleItem.TYPE_1, R.layout.item_type1);
        addItemType(MultipleItem.TYPE_2, R.layout.item_type2_divider_h);
        addItemType(MultipleItem.TYPE_3, R.layout.item_type3);
        addItemType(MultipleItem.TYPE_4, R.layout.item_type4);
        addItemType(MultipleItem.TYPE_5, R.layout.item_type5);
        addItemType(MultipleItem.TYPE_6, R.layout.item_type6);
        addItemType(MultipleItem.TYPE_7, R.layout.item_type7);
        addItemType(MultipleItem.TYPE_8, R.layout.item_type8);
        addItemType(MultipleItem.TYPE_9, R.layout.item_type9);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_0:
                initViewPager(helper, item);
                if (!handler.hasMessages(0)) {
                    handler.sendEmptyMessage(0);
                }
                break;
            case MultipleItem.TYPE_1:
                helper.setText(R.id.item_type1_text, item.getContent());
                helper.setImageResource(R.id.item_type1_img, item.getImage());
                helper.setBackgroundRes(R.id.item_type1_img, item.getBakground());
                break;
            case MultipleItem.TYPE_2:
                //  helper.setBackgroundRes(R.id.item_type2_divider_h,item.getBakground());
                break;
            case MultipleItem.TYPE_3:
                helper.setBackgroundRes(R.id.item_type3_img, item.getBakground());
                break;
            case MultipleItem.TYPE_4:
                helper.setText(R.id.item_type_4_text3, item.getContent());
                break;
            case MultipleItem.TYPE_6:
                helper.setBackgroundRes(R.id.item_type6_ll, item.getBakground());
                helper.setText(R.id.item_type6_text1, item.gettitle());
                helper.setText(R.id.item_type6_text2, item.getContent());
                helper.setImageResource(R.id.item_type6_img, item.getImage());
                break;
        }
    }


    private void initViewPager(BaseViewHolder helper, MultipleItem item) {
        url = item.getList();
        mIndicator = (LinearLayout) helper.getView(R.id.indicator);
        viewPager = (ViewPager) helper.getView(R.id.head_viewPager);

        mAdapter = new MyViewPagerAdapter(context, url);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(5000 * (url.size() + 1));

        if (mImageView == null) {
            initIndicator();
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator() {
        mImageView = new ImageView[url.size()];
        for (int i = 0; i < url.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.indicator_image, null);
            view.findViewById(R.id.indicator_iamge).setBackgroundResource(R.mipmap.hong_indicator);
            mImageView[i] = new ImageView(context);
            if (i == 0) {
                mImageView[i].setBackgroundResource(R.mipmap.hong_indicator);
            } else {
                mImageView[i].setBackgroundResource(R.mipmap.hui_indicator);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 0, 0, 0);
                mImageView[i].setLayoutParams(layoutParams);
            }
            mIndicator.addView(mImageView[i]);
        }
    }

    private void setIndicator(int position) {
        position %= url.size();
        for (int i = 0; i < url.size(); i++) {
            mImageView[i].setBackgroundResource(R.mipmap.hong_indicator);
            if (position != i) {
                mImageView[i].setBackgroundResource(R.mipmap.hui_indicator);
            }
        }
    }
}
