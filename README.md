# HomePage
高仿京东商城首页，下拉刷新搜索栏隐藏，上滑渐变
## 图片效果
![images] {https://github.com/TurnTears/HomePage/blob/master/img/1.gif}

## 思路
* 渐变：监听RecyclerView的滑动距离，改变搜索栏的alpha值
* 隐藏：下拉刷新时隐藏搜索栏

##主要代码
### 渐变
'''

    int mDistanceY=0;
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = searchView.getBottom();
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    searchView.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    searchView.setBackgroundResource(R.color.white);
                }
            }
        });
        
'''
