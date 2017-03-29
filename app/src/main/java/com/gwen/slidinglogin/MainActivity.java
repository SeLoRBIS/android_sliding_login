package com.gwen.slidinglogin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

public class MainActivity extends AppCompatActivity {

    private static final int[] mResources = {
            R.drawable.login_bg_001,
            R.drawable.login_bg_002,
            R.drawable.login_bg_003
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        LoginViewPagerAdapter adapterViewPager = new LoginViewPagerAdapter(this);
        viewPager.setAdapter(adapterViewPager);

        CirclePageIndicator mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);

        ((Button) findViewById(R.id.login_btn_signup)).setText(R.string.btn_signup);
        ((Button) findViewById(R.id.login_btn_signin)).setText(R.string.btn_signin);
    }

    /**
     * Login pager adapter
     */
    public static class LoginViewPagerAdapter extends PagerAdapter {

        private Context mContext;

        public LoginViewPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View itemView = LayoutInflater.from(mContext).inflate(R.layout.viewpager_login, container, false);
            itemView.setBackground(ContextCompat.getDrawable(mContext, mResources[position]));
            TextView title = (TextView) itemView.findViewById(R.id.login_viewpager_title_tv);
            title.setText(mContext.getResources().getStringArray(R.array.login_titles)[position]);
            TextView subtitle = (TextView) itemView.findViewById(R.id.login_viewpager_subtitle_tv);
            subtitle.setText(mContext.getResources().getStringArray(R.array.login_subtitles)[position]);

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            container.removeView((View) view);
        }

        @Override
        public int getCount() {
            return mContext.getResources().getStringArray(R.array.login_titles).length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }
    }
}
