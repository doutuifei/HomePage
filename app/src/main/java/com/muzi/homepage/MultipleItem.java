package com.muzi.homepage;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 李鹏 on 2017/2/23.
 */

public class MultipleItem implements MultiItemEntity {
    public static final int TYPE_0 = 0;//搜索头
    public static final int TYPE_1 = 1;//超市、服饰、酒水等导购信息
    public static final int TYPE_2 = 2;//横分割线
    public static final int TYPE_3 = 3;//横幅广告
    public static final int TYPE_4 = 4;//头条
    public static final int TYPE_5 = 5;//限购、精选、特价
    public static final int TYPE_6 = 6;//今日更新、一元抢购
    public static final int TYPE_7 = 7;//宽分割线
    public static final int TYPE_8 = 8;//猜你喜欢
    public static final int TYPE_9 = 9;//推荐商品

    //权重
    public static final int TYPE_SPAN_SIZE_4 = 4;
    public static final int TYPE_SPAN_SIZE_5 = 5;
    public static final int TYPE_SPAN_SIZE_10 = 10;
    public static final int TYPE_SPAN_SIZE_20 = 20;

    private int itemType;
    private int image;
    private int bakground;
    private String content;
    private int spanSize;
    private String title;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private List<String> list;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public MultipleItem(int itemType, int image, int bakground, String content, int spanSize, String title) {
        this.itemType = itemType;
        this.image = image;
        this.bakground = bakground;
        this.content = content;
        this.spanSize = spanSize;
        this.title = title;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public MultipleItem() {
    }

    public MultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public MultipleItem(int itemType, List<String> list, int spanSize) {
        this.list = list;
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public MultipleItem(int itemType, String content, int spanSize) {
        this.itemType = itemType;
        this.content = content;
        this.spanSize = spanSize;
    }

    public MultipleItem(int itemType, int bakground, int spanSize) {
        this.itemType = itemType;
        this.bakground = bakground;
        this.spanSize = spanSize;
    }

    public MultipleItem(int itemType, int image, int bakground, String content, int spanSize) {
        this.itemType = itemType;
        this.image = image;
        this.bakground = bakground;
        this.content = content;
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBakground() {
        return bakground;
    }

    public void setBakground(int bakground) {
        this.bakground = bakground;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
