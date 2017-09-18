package news.brotherlu.com.newsapp.activity;

import android.app.Activity;
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

import news.brotherlu.com.newsapp.R;

/**
 * Created by Administrator on 2017/9/14.
 */
public class GuidActivity extends Activity{

    private ViewPager guidPager;
    private Button guidButton;
    private LinearLayout pointGroup;
    private List<ImageView> guidViews;
    private ImageView redPoint;
    //灰点的间距
    private int leftMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        //初始化组件
        initCompants();
        guidPager.setAdapter(new MyPageAdapter());
        //监听红点的位置
        redPoint.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //viewpager的监听器，用于监听页面位置的变化
        guidPager.addOnPageChangeListener(new MyOnPageChangeListener());
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

        for(int i = 0; i < pointIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(pointIds[i]);
            guidViews.add(imageView);

            //添加点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_gray);
            // TODO: 2017/9/14 10为像素，即sp 不会适配屏幕，后续应当做适配 
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
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
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
