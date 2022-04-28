package com.example.cis436_proj4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

//import com.example.cis436_proj4.ui.main.MainFragment;
import com.example.cis436_proj4.ui.main.MainFragment;
import com.example.cis436_proj4.ui.main.VPAdapter;
import com.example.cis436_proj4.ui.main.dogImageFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements dogImageFragment.dogListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private VPAdapter vpAdapter;
    private Fragment dogImageFrag = new dogImageFragment();
    private Fragment mainFrag = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(dogImageFrag, "Dogs");
        vpAdapter.addFragment(mainFrag, "DogInfo");
        viewPager.setAdapter(vpAdapter);

    }

    public void onDogSelection(JSONObject data) throws JSONException{
        MainFragment dialog = (MainFragment)mainFrag;
        dialog.showDog(data);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();
//        dialog.show(getSupportFragmentManager(),"MainFragment");
    }
}