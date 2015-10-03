package com.ayoprez.preferences;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.deilyquote.R;

/**
 * Created by AyoPrez on 23/08/15.
 */
public class LegalFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.preferences_legal_layout, container, false);
    }

}