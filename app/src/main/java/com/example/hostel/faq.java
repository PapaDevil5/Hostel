package com.example.hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

public class faq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ViewPager2 viewPager2 =(ViewPager2) (findViewById(R.id.viewpager));
        FragmentManager fragmentManager=getSupportFragmentManager();
        tab_adapter adapter=new tab_adapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);
    }
}