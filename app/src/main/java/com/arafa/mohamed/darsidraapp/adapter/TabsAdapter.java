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
    String codeStudent, nameStudent, classStudent;

    public TabsAdapter(@NonNull  FragmentActivity fragmentActivity, String codeStudent, String nameStudent, String classStudent) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.codeStudent = codeStudent;
        this.nameStudent = nameStudent;
        this.classStudent = classStudent;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new RatingDetailsFragment(codeStudent);

            case 1:
                return new  SubscriptionDetailsFragment(codeStudent, nameStudent, classStudent);
            default:
                Toast.makeText(fragmentActivity, "No Page", Toast.LENGTH_SHORT).show();
        }
        return new SubscriptionDetailsFragment(codeStudent, nameStudent, classStudent);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
