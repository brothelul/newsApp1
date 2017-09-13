package news.brotherlu.com.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(MainActivity.this,"动画播放完毕",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
