package news.brotherlu.com.newsapp.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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

        getDataFromNet();
    }

    /**
     * 请求数据不能放在主线程中，会阻塞程序，此处xutils已经封装
     */
    public void getDataFromNet(){
        RequestParams params = new RequestParams("http://192.168.1.6:8080/AroundYou/user/loadCategory.do");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(NewsPager.class.getName(),result);
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
}
