package news.brotherlu.com.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/9/14.
 */
public class CacheUtils {

    public static final String BROTHERLU = "brotherlu";

    //判断用户是否进入过主页面
    public static boolean getStartedMain(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(BROTHERLU,Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
    }

    /**
     * 保存软件的参数
     * @param context
     * @param startedMain
     * @param b
     */
    public static void putHasStartMain(Context context, String startedMain, boolean b) {
        SharedPreferences sp = context.getSharedPreferences(startedMain,Context.MODE_PRIVATE);
        sp.edit().putBoolean(BROTHERLU,b).commit();
    }
}
