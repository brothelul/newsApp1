package news.brotherlu.com.newsapp.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MainContentFragment extends BaseFragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment,null);
        viewPager = view.findViewById(R.id.content_viewpager);
        radioGroup = view.findViewById(R.id.radiogroup);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        radioGroup.check(R.id.rb_home);
    }
}
