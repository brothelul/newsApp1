package news.brotherlu.com.newsapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.fragment.LeftMenuFragment;
import news.brotherlu.com.newsapp.fragment.MainContentFragment;
import news.brotherlu.com.newsapp.utils.DensityUtil;

public class ContentActivity extends SlidingActivity {

    public static final String LEFT_MENU = "left_menu";
    public static final String MAIN_CONTENT = "main_content";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //设置左滑动
        setBehindContentView(R.layout.activity_content_left);
        //设置右滑动
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.activity_content_left);
        //设置显示模式
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置滑动的位置
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置占据主页面的宽度
        slidingMenu.setBehindOffset(DensityUtil.dp2px(ContentActivity.this,200));
        //初始化Fragment
        initFragment();
    }

    private void initFragment() {
        //初始化
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //替换Fragment
        ft.replace(R.id.activity_content, new MainContentFragment(), MAIN_CONTENT);
        ft.replace(R.id.activity_content_left, new LeftMenuFragment(), LEFT_MENU);
        //提交事务
        ft.commit();
    }
}
