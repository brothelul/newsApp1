package news.brotherlu.com.newsapp.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import news.brotherlu.com.newsapp.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MainContentFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("主页面");
    }
}
