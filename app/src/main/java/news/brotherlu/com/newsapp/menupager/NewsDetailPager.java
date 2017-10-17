package news.brotherlu.com.newsapp.menupager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.menupager.base.BaseMenuPager;

/**
 * Created by Administrator on 2017/9/30.
 */

public class NewsDetailPager extends BaseMenuPager {

    @ViewInject(R.id.news_tail)
    private ViewPager viewPager;

    public NewsDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.news_detail_pager,null);
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
