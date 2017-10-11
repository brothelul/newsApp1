package news.brotherlu.com.newsapp.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import news.brotherlu.com.newsapp.R;
import news.brotherlu.com.newsapp.activity.ContentActivity;
import news.brotherlu.com.newsapp.domin.NewsData;
import news.brotherlu.com.newsapp.fragment.base.BaseFragment;
import news.brotherlu.com.newsapp.pager.NewsPager;
import news.brotherlu.com.newsapp.utils.DensityUtil;

/**
 * Created by Administrator on 2017/9/18.
 */

public class LeftMenuFragment extends BaseFragment {

    private ListView listView;
    private List<NewsData.DataBean> data;
    private int prePostion;
    private LeftMenuFragmentAdapter leftMenuFragmentAdapter;

    @Override
    public View initView() {
        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dp2px(context,40),0,0); //距离顶部50
        listView.setDividerHeight(0);//分割线高为0
        listView.setCacheColorHint(Color.TRANSPARENT); //高亮
        listView.setSelector(android.R.color.transparent); //按下listview不变色
        //为listview设置item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //记录点击的位置
                prePostion = i;
                //刷新adaptor，刷新其中的getView
                leftMenuFragmentAdapter.notifyDataSetChanged();
                //关闭左侧菜单栏
                ContentActivity contentActivity = (ContentActivity) context;
                contentActivity.getSlidingMenu().toggle();//如果是开着就关闭，若是关着就打开
                //切换到对应的详情页
                switchDetailPager(prePostion);
            }
        });
        return listView;
    }

    private void switchDetailPager(int prePostion) {
        ContentActivity contentActivity = (ContentActivity) context;
        MainContentFragment contentFragment = contentActivity.getMainContentFragment();
        NewsPager newsPager = contentFragment.getNewsPager();
        newsPager.swichPager(prePostion);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setData(List<NewsData.DataBean> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++){
            Log.i(this.getClass().getName(),">>>>>>>>>>>>>>>>>title:"+data.get(i).getTitle());
        }
        leftMenuFragmentAdapter = new LeftMenuFragmentAdapter();
        listView.setAdapter(leftMenuFragmentAdapter);
        switchDetailPager(prePostion);
    }

    class LeftMenuFragmentAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = (TextView) View.inflate(context, R.layout.left_menu_item,null);
            textView.setText(data.get(i).getTitle());
            //判断是否选择
            textView.setEnabled(i == prePostion);
            return textView;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

    }
}
