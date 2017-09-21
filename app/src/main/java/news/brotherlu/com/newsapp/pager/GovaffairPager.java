package news.brotherlu.com.newsapp.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import news.brotherlu.com.newsapp.pager.base.BasePager;

/**
 * Created by Administrator on 2017/9/21.
 */

public class GovaffairPager extends BasePager {

    public GovaffairPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        title.setText("网络");

        //主要内容
        TextView textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        content.addView(textView);
        textView.setText("网络内容");
    }
}
