package com.example.zomatoapp.activities;

import android.os.Bundle;

import com.example.zomatoapp.R;
import com.example.zomatoapp.databinding.ActivityHomeBinding;
import com.example.zomatoapp.fragments.DiningFragment;
import com.example.zomatoapp.fragments.NightLifeFragment;
import com.example.zomatoapp.fragments.ProfileFragment;
import com.example.zomatoapp.viewModel.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getName();
    private ActivityHomeBinding mBinding;
    private HomeViewModel mViewModel;

    private NestedScrollView scrollView;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    private List<String> labels = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    private static final String LABEL_DINING = "Dining";
    private static final String LABEL_NIGHT_LEFT = "Nightlife";
    private static final String LABEL_PROFILE = "Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new HomeViewModel(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mBinding.setViewModel(mViewModel);

        initView();

        mViewModel.retrieveCollections();
        mViewModel.getSearchResult();
    }

    private void initView() {
        scrollView = mBinding.nsvScrollView;
        viewPager = mBinding.vpFragmentViewPager;
        tabLayout = mBinding.tlTabLayout;

        scrollView.setFillViewport(true);

        fragments.add(DiningFragment.newInstance());
        fragments.add(NightLifeFragment.newInstance());
        fragments.add(ProfileFragment.newInstance());

        initViewPager();
        initIndicator();
    }

    private void initViewPager() {
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });

        viewPager.setUserInputEnabled(false);
        viewPager.setOffscreenPageLimit(2);
    }

    private void initIndicator() {
        labels.add(LABEL_DINING);
        labels.add(LABEL_NIGHT_LEFT);
        labels.add(LABEL_PROFILE);

        new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> tab.setText(labels.get(position))).attach();
    }
}
