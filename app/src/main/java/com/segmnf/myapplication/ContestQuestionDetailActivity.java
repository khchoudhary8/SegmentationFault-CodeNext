package com.segmnf.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.segmnf.myapplication.databinding.ActivityContestQuestionDetailBinding;
import com.segmnf.myapplication.databinding.ActivityMainBinding;

public class ContestQuestionDetailActivity extends AppCompatActivity {
    ActivityContestQuestionDetailBinding binding;
    private FirebaseDatabase database;


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideStatus() {
        /* To make the status bar transparent*/

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityContestQuestionDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            hideStatus();

        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        TabLayout tabLayout = findViewById(R.id.tab_question);
        ViewPager2 viewPager2 = findViewById(R.id.profile_view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("QUESTION"));
        tabLayout.addTab(tabLayout.newTab().setText("EDITOR"));
        tabLayout.addTab(tabLayout.newTab().setText("OUTPUT"));
        tabLayout.setTabRippleColor(null);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.setAdapter(new QuestionPagerAdapter(getSupportFragmentManager(), getLifecycle()));

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FBC820"));
                    tabLayout.selectTab(tabLayout.getTabAt(position));
                } else {
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFA233"));

                    tabLayout.selectTab(tabLayout.getTabAt(position));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}