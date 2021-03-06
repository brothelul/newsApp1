package news.brotherlu.com.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import news.brotherlu.com.newsapp.MainActivity;
import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.utils.CacheUtils;
import news.brotherlu.com.newsapp.utils.DensityUtil;

/**
 * Created by Administrator on 2017/9/14.
 */
public class GuideActivity extends Activity{

    private static final String TAG = GuideActivity.class.getName();
    private ViewPager guidPager;
    private Button guidButton;
    private LinearLayout pointGroup;
    private List<ImageView> guidViews;
    private ImageView redPoint;
    //灰点的间距
    private int leftMax;
    private int legthDp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //初始化组件
        initCompants();
        guidPager.setAdapter(new MyPageAdapter());
        //监听红点的位置
        redPoint.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //viewpager的监听器，用于监听页面位置的变化
        guidPager.addOnPageChangeListener(new MyOnPageChangeListener());
        //添加button的点击事件
        guidButton.setOnClickListener(new MyOnClickListener());
    }

    //初始化各个组件
    private void initCompants(){
        guidPager = findViewById(R.id.viewpager);
        guidButton = findViewById(R.id.guid_btn);
        pointGroup = findViewById(R.id.point_group);
        redPoint = findViewById(R.id.red_point);
        guidViews = new ArrayList<>();

        int[] pointIds = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        //获取10dp的px
        legthDp = DensityUtil.dp2px(GuideActivity.this,10);

        for(int i = 0; i < pointIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(pointIds[i]);
            guidViews.add(imageView);

            //添加点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(legthDp,legthDp);
            //如果不是第一个则与前面一个单位距离10
            if (i != 0){
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);

            pointGroup.addView(point);
        }
    }

    /**
     * 用于监听全局的layout
     */
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {
            redPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            //获取第一个灰点到第0个的间距
            leftMax = pointGroup.getChildAt(1).getLeft() - pointGroup.getChildAt(0).getLeft();

//            Log.d(TAG,"left:"+leftMax);
        }
    }

    /**
     * 用于展示页面
     */
    class MyPageAdapter extends PagerAdapter{

        //返回总的页面数
        @Override
        public int getCount() {
            return guidViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = guidViews.get(position);
            container.addView(imageView);
            // return position;
            return imageView;
        }

        /**
         *
         * @param view
         * @param object 来自于 instantiateItem 的返回值
         * @return 判断是否为同一个View
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
     //       return view == guidViews.get(Integer.valueOf((String) object));
            return view == object;
        }

        /**
         *
         * @param container viewPager
         * @param position 要销毁的位置
         * @param object 要销毁的对象
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    //用于监听页面的变化
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         *  页面滚动时
         * @param position  位置
         * @param positionOffset 占屏幕百分比
         * @param positionOffsetPixels 占屏幕的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
            //计算到屏幕左边的长度
            int left = position*leftMax + (int) (leftMax*positionOffset);
            //设置距离左边的长度
            params.leftMargin = left;
            redPoint.setLayoutParams(params);

//            Log.d(TAG,"positionOffset:"+positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
            //如果是最后一个页面便显示button，否则不显示
            if (position == (guidViews.size()-1)){
                guidButton.setVisibility(View.VISIBLE);
            }else{
                guidButton.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     *     button的点击事件，点击进入主页面并且保存已经打开引导页面到缓存
     */
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //保存已经打开过的记录
            CacheUtils.putHasStartMain(GuideActivity.this,MainActivity.STARTED_MAIN,true);
            //跳转到主页面
            Intent intent = new Intent(GuideActivity.this,ContentActivity.class);
            //打开新页面
            startActivity(intent);
            //关闭该页面
            finish();
        }
    }
}
