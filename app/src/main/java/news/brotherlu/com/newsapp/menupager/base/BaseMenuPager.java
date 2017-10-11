package news.brotherlu.com.newsapp.menupager.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/9/30.
 * 左侧菜单栏对应的详情页面
 */

public abstract class BaseMenuPager {

    public final Context context;
    public View rootView;

    public BaseMenuPager(Context context){
        this.context = context;
        rootView = initView();
    }

    /**
     * 子类必须实现该方法
     * @return
     */
    public abstract View initView();

    /**
     * 子类初始化数据
     */
    public void initData(){}
}
