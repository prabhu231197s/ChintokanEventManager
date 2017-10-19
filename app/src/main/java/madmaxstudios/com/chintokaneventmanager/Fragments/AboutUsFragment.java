package madmaxstudios.com.chintokaneventmanager.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import madmaxstudios.com.chintokaneventmanager.R;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class AboutUsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_us,container,false);
        return v;
    }
}
