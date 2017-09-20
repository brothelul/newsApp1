package news.brotherlu.com.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import news.brotherlu.com.newsapp.activity.ContentActivity;
import news.brotherlu.com.newsapp.activity.GuideActivity;
import news.brotherlu.com.newsapp.utils.CacheUtils;

public class MainActivity extends Activity {

    //是否进入过主页面
    public static final String STARTED_MAIN = "started_main";
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.activity_main);
        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setFillAfter(true);

        ScaleAnimation bb = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        bb.setFillAfter(true);

        RotateAnimation cc = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        cc.setFillAfter(true);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(aa);
        set.addAnimation(bb);
        set.addAnimation(cc);
        set.setDuration(2000);

        layout.startAnimation(set);

        set.setAnimationListener(new MyAnimationListener());
    }

    class MyAnimationListener implements Animation.AnimationListener{

        //动画开始前
        @Override
        public void onAnimationStart(Animation animation) {
        }

        //动画结束
        @Override
        public void onAnimationEnd(Animation animation) {
            boolean hasStartedMain = CacheUtils.getStartedMain(MainActivity.this,STARTED_MAIN);
            Intent intent;
            //如果进入过就不再重复进入
            if (hasStartedMain){
                intent = new Intent(MainActivity.this, ContentActivity.class);
            }else{
                //创建intent，开启新页面
                intent= new Intent(MainActivity.this,GuideActivity.class);
            }
            startActivity(intent);
            //关闭主页面
            finish();
//            Toast.makeText(MainActivity.this,"动画播放完毕",Toast.LENGTH_SHORT).show();
        }

        //动画重复播放
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
