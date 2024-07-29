package com.w1412x.health1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.w1412x.health1.main_page_activity_pages.MainExmaineFragment;
import com.w1412x.health1.main_page_activity_pages.MainHomeFragment;
import com.w1412x.health1.main_page_activity_pages.MainNewsFragment;
import com.w1412x.health1.main_page_activity_pages.MainRoomFragment;
import com.w1412x.health1.main_page_activity_pages.MainSelfFragment;

public class MainPageActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment always_live_main_fragment,always_live_data_fragment,always_live_self_fragment;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        init_view();
    }
    void init_view() {
        always_live_main_fragment = new MainHomeFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.menu_home) {
                selectedFragment = fragmentManager.findFragmentByTag("MainHomeFragment");
                if (selectedFragment == null) {
                    selectedFragment = new MainHomeFragment();
                    fragmentTransaction.add(R.id.main_fragment_container, selectedFragment, "MainHomeFragment");
                }
            } else if (item.getItemId() == R.id.menu_exmine) {
                selectedFragment = fragmentManager.findFragmentByTag("MainExmaineFragment");
                if (selectedFragment == null) {
                    selectedFragment = new MainExmaineFragment();
                    fragmentTransaction.add(R.id.main_fragment_container, selectedFragment, "MainExmaineFragment");
                }
            } else if (item.getItemId() == R.id.menu_news) {
                selectedFragment = fragmentManager.findFragmentByTag("MainNewsFragment");
                if (selectedFragment == null) {
                    selectedFragment = new MainNewsFragment();
                    fragmentTransaction.add(R.id.main_fragment_container, selectedFragment, "MainNewsFragment");
                }
            } else if (item.getItemId() == R.id.menu_self){
                selectedFragment = fragmentManager.findFragmentByTag("MainSelfFragment");
                if (selectedFragment == null) {
                    selectedFragment = new MainSelfFragment();
                    fragmentTransaction.add(R.id.main_fragment_container, selectedFragment, "MainSelfFragment");
                }
            } else if (item.getItemId() == R.id.menu_room){
                selectedFragment = fragmentManager.findFragmentByTag("MainRoomFragment");
                if (selectedFragment == null) {
                    selectedFragment = new MainRoomFragment();
                    fragmentTransaction.add(R.id.main_fragment_container, selectedFragment, "MainRoomFragment");
                }
            }
            // 隐藏其他的 Fragment
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment != selectedFragment) {
                    fragmentTransaction.hide(fragment);
                }
            }
            fragmentTransaction.show(selectedFragment);
            fragmentTransaction.commit();
            return true;
        });
    }
}