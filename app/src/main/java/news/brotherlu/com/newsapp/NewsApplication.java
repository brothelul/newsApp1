package news.brotherlu.com.newsapp;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/9/21.
 * 整个应用
 */

public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
