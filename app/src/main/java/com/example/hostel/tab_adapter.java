package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class tab_adapter extends FragmentStateAdapter {
    public tab_adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1:
                return new fragment_2();
            case 2:
                return new fragment_3();
            default:
                return new fragment_1();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
