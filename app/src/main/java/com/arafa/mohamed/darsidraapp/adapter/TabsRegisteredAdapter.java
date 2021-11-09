package com.arafa.mohamed.darsidraapp.adapter;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.arafa.mohamed.darsidraapp.fragments.RegisteredStudentsFragment;
import com.arafa.mohamed.darsidraapp.fragments.RegisteredTeachersFragment;

public class TabsRegisteredAdapter extends FragmentStateAdapter {
    FragmentActivity fragmentActivity;

    public TabsRegisteredAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new RegisteredStudentsFragment(fragmentActivity);

            case 1:
                return new RegisteredTeachersFragment(fragmentActivity);
            default:
                Toast.makeText(fragmentActivity, "No Page", Toast.LENGTH_SHORT).show();
        }
        return new RegisteredStudentsFragment(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
