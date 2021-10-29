package com.arafa.mohamed.darsidraapp.adapter;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.arafa.mohamed.darsidraapp.fragments.RatingDetailsFragment;
import com.arafa.mohamed.darsidraapp.fragments.SubscriptionDetailsFragment;

public class TabsAdapter extends FragmentStateAdapter {
    FragmentActivity fragmentActivity;

    public TabsAdapter(@NonNull  FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new RatingDetailsFragment();

            case 1:
                return new  SubscriptionDetailsFragment();
            default:
                Toast.makeText(fragmentActivity, "No Page", Toast.LENGTH_SHORT).show();
        }
        return new SubscriptionDetailsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
