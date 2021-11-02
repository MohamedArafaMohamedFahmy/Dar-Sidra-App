package com.arafa.mohamed.darsidraapp.adapter;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.arafa.mohamed.darsidraapp.fragments.RatingTeacherFragment;
import com.arafa.mohamed.darsidraapp.fragments.RegisteredStudentsFragment;
import com.arafa.mohamed.darsidraapp.fragments.RegisteredTeachersFragment;
import com.arafa.mohamed.darsidraapp.fragments.SalaryFragment;

public class TabsSalaryRatingAdapter extends FragmentStateAdapter {
    FragmentActivity fragmentActivity;

    public TabsSalaryRatingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new SalaryFragment();

            case 1:
                return new RatingTeacherFragment();
            default:
                Toast.makeText(fragmentActivity, "No Page", Toast.LENGTH_SHORT).show();
        }
        return new SalaryFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
