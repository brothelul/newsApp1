package news.brotherlu.com.newsapp.utils;

import android.content.Context;

/**
 * px 和 dip 互相转化的工具
 * Created by Administrator on 2017/9/18.
 */

public class DensityUtil {
    /**
     * 根据手机的分辨率将dip转化为px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale + 0.5f);
    }

    /**
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale + 0.5f);
    }
}
