package news.brotherlu.com.newsapp.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.activity.ContentActivity;
import news.brotherlu.com.newsapp.fragment.base.BaseFragment;
import news.brotherlu.com.newsapp.pager.GovaffairPager;
import news.brotherlu.com.newsapp.pager.HomePager;
import news.brotherlu.com.newsapp.pager.NewsPager;
import news.brotherlu.com.newsapp.pager.SettingPager;
import news.brotherlu.com.newsapp.pager.SmartServicePager;
import news.brotherlu.com.newsapp.pager.base.BasePager;
import news.brotherlu.com.newsapp.ui.NoScrollViewPager;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MainContentFragment extends BaseFragment {

    @ViewInject(R.id.content_viewpager)
    private NoScrollViewPager viewPager;

    @ViewInject(R.id.radiogroup)
    private RadioGroup radioGroup;

    private List<BasePager> contentPagers;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment,null);
//        viewPager = view.findViewById(R.id.content_viewpager);  通过注解形式获取对象
//        radioGroup = view.findViewById(R.id.radiogroup);
        x.view().inject(MainContentFragment.this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //初始化页面
        contentPagers = new ArrayList<>();
        contentPagers.add(new HomePager(context));
        contentPagers.add(new NewsPager(context));
        contentPagers.add(new GovaffairPager(context));
        contentPagers.add(new SettingPager(context));
        contentPagers.add(new SmartServicePager(context));

        radioGroup.check(R.id.rb_home);
        //设置viewPager适配器
        viewPager.setAdapter(new MyPagerAdapter());
        //设置对应的点击按钮切换事件
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //监听页面选中时再加载页面数据，而不是页面预加载时便初始化数据
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    public void isEnableSlidingMenu(boolean enable){
        ContentActivity contentActivity = (ContentActivity) context;
        SlidingMenu slidingMenu = contentActivity.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    public NewsPager getNewsPager() {
        return (NewsPager) contentPagers.get(1);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 页面选中时进行页面数据的预加载
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            contentPagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_home:
                    viewPager.setCurrentItem(0,false);
                    isEnableSlidingMenu(false);
                    break;
                case R.id.rb_newscenter:
                    viewPager.setCurrentItem(1,false);
                    isEnableSlidingMenu(true);
                    break;
                case R.id.rb_govaffair:
                    viewPager.setCurrentItem(2,false);
                    isEnableSlidingMenu(false);
                    break;
                case R.id.rb_setting:
                    viewPager.setCurrentItem(3,false);
                    isEnableSlidingMenu(false);
                    break;
                case R.id.rb_smartservice:
                    viewPager.setCurrentItem(4,false);
                    isEnableSlidingMenu(false);
                    break;
                default:
                    isEnableSlidingMenu(false);
                    break;
            }
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return contentPagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = contentPagers.get(position);
//            basePager.initData();   将数据初始化移到页面加载时进行，避免多余的预加载数据
            View view = basePager.rootView;
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            viewPager.removeView((View) object);
        }
    }
}
