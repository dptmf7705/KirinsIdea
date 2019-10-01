package com.kirinsidea.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kirinsidea.R;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.databinding.ActivityMainBinding;
import com.kirinsidea.extension.Injection;
import com.kirinsidea.ui.BaseActivity;
import com.kirinsidea.ui.bookmarklist.BookmarkListFragment;
import com.kirinsidea.ui.profile.ProfileFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements
        ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        initViewPager();
        initNavigationView();
    }

    private void initViewPager() {
        binding.viewPager.setAdapter(
                new ViewPagerAdapter(getSupportFragmentManager()));
        binding.viewPager.addOnPageChangeListener(this);
    }

    private void initNavigationView() {
        binding.navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                binding.navigation.setSelectedItemId(R.id.navigation_bookmark);
                break;
            case 1:
                binding.navigation.setSelectedItemId(R.id.navigation_recommend);
                break;
            case 2:
                binding.navigation.setSelectedItemId(R.id.navigation_profile);
                break;
            case 3:
                binding.navigation.setSelectedItemId(R.id.navigation_history);
                break;
            case 4:
                binding.navigation.setSelectedItemId(R.id.navigation_setting);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_bookmark:
                binding.viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_recommend:
                binding.viewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_profile:
                binding.viewPager.setCurrentItem(2);
                return true;
            case R.id.navigation_history:
                binding.viewPager.setCurrentItem(3);
                return true;
            case R.id.navigation_setting:
                binding.viewPager.setCurrentItem(4);
                return true;
        }
        return false;
    }


    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BookmarkListFragment.newInstance();
                case 1:
                    return BookmarkListFragment.newInstance();
                case 2:
                    return BookmarkListFragment.newInstance();
                case 3:
                    return BookmarkListFragment.newInstance();
                case 4:
                    return BookmarkListFragment.newInstance();
            }
            return BookmarkListFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
