package news.brotherlu.com.newsapp.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import news.brotherlu.com.newsapp.activity.ContentActivity;
import news.brotherlu.com.newsapp.domin.NewsData;
import news.brotherlu.com.newsapp.fragment.LeftMenuFragment;
import news.brotherlu.com.newsapp.menupager.InteracDetailPager;
import news.brotherlu.com.newsapp.menupager.NewsDetailPager;
import news.brotherlu.com.newsapp.menupager.PhotosDetailPager;
import news.brotherlu.com.newsapp.menupager.TopicDetailPager;
import news.brotherlu.com.newsapp.menupager.base.BaseMenuPager;
import news.brotherlu.com.newsapp.pager.base.BasePager;

/**
 * Created by Administrator on 2017/9/21.
 */

public class NewsPager extends BasePager {

    private NewsData newsData;

    public NewsPager(Context context) {
        super(context);
    }
    private List<BaseMenuPager> baseMenuPagers;

    @Override
    public void initData() {
        super.initData();
        title.setText("新闻中心");

        //主要内容
        TextView textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        content.addView(textView);
        textView.setText("新闻内容");

        //设置button为可见的
        button.setVisibility(View.VISIBLE);
        getDataFromNet();
    }

    /**
     * 请求数据不能放在主线程中，会阻塞程序，此处xutils已经封装
     */
    public void getDataFromNet(){
        RequestParams params = new RequestParams("http://192.168.191.1:8080/AroundYou/web_home/static/api/news/categories.json");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(NewsPager.class.getName(),result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"请求数据失败，请检查网络设置",Toast.LENGTH_SHORT).show();
                Log.e(NewsPager.class.getName(),ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i(NewsPager.class.getName(),cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.i(NewsPager.class.getName(),"请求完成");
            }
        });
    }

    private void processData(String json) {
        newsData = parseJson(json);
        Log.i(NewsPager.class.getName(),"解析JSON成功>>>>>>>>>>>>>>>>>>>"+newsData.getData().get(0).getTitle());
        //将解析数据传输给左侧菜单栏
        ContentActivity contentActivity = (ContentActivity) context;
        LeftMenuFragment leftMenuFragment = contentActivity.findLeftMenuFragment();
        //初始化新闻详情页面
        baseMenuPagers = new ArrayList<>();
        baseMenuPagers.add(new NewsDetailPager(context));
        baseMenuPagers.add(new TopicDetailPager(context));
        baseMenuPagers.add(new PhotosDetailPager(context));
        baseMenuPagers.add(new InteracDetailPager(context));

        leftMenuFragment.setData(newsData.getData());
    }

    private NewsData parseJson(String json) {
        return JSON.parseObject(json,NewsData.class);
    }

    /**
     * 根据详细位置切换页面
     * @param prePostion
     */
    public void swichPager(int prePostion) {
        //设置标题
        title.setText(newsData.getData().get(prePostion).getTitle());
        //移除之前的内容
        content.removeAllViews();
        //添加view
        BaseMenuPager menuPager = baseMenuPagers.get(prePostion);
        menuPager.initData();
        View rootView = menuPager.rootView;
        content.addView(rootView);
    }
}
