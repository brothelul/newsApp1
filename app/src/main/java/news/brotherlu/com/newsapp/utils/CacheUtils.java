package news.brotherlu.com.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/9/14.
 */
public class CacheUtils {

    //判断用户是否进入过主页面
    public static boolean hasStartedMain(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("brotherlu",Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
    }
}
