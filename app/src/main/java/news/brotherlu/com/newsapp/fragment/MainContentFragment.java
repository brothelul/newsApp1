package news.brotherlu.com.newsapp.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.fragment.base.BaseFragment;
import news.brotherlu.com.newsapp.pager.GovaffairPager;
import news.brotherlu.com.newsapp.pager.HomePager;
import news.brotherlu.com.newsapp.pager.NewsPager;
import news.brotherlu.com.newsapp.pager.SettingPager;
import news.brotherlu.com.newsapp.pager.SmartServicePager;
import news.brotherlu.com.newsapp.pager.base.BasePager;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MainContentFragment extends BaseFragment {

    @ViewInject(R.id.content_viewpager)
    private ViewPager viewPager;

    @ViewInject(R.id.radiogroup)
    private RadioGroup radioGroup;

    private List<BasePager> contentPagers;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment,null);
//        viewPager = view.findViewById(R.id.content_viewpager);
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
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return contentPagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = contentPagers.get(position);
            basePager.initData();
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
