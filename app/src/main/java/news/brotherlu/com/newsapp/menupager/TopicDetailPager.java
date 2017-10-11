package news.brotherlu.com.newsapp.menupager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import news.brotherlu.com.newsapp.menupager.base.BaseMenuPager;

/**
 * Created by Administrator on 2017/9/30.
 */

public class TopicDetailPager extends BaseMenuPager {

    private TextView textView;

    public TopicDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("话题详情页");
    }
}
