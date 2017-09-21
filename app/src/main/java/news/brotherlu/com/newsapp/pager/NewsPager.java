package news.brotherlu.com.newsapp.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import news.brotherlu.com.newsapp.pager.base.BasePager;

/**
 * Created by Administrator on 2017/9/21.
 */

public class NewsPager extends BasePager {

    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        title.setText("新闻");

        //主要内容
        TextView textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        content.addView(textView);
        textView.setText("新闻内容");
    }
}
