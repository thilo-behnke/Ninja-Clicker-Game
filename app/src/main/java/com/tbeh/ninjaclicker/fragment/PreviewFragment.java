package com.tbeh.ninjaclicker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.view.EndlessGameView;

public class PreviewFragment extends Fragment {

    EndlessGameView view;
    GameEngine gameEngine;

    public PreviewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        gameEngine = new GameEngine(getActivity().getApplicationContext());
        view = new EndlessGameView(getActivity().getApplicationContext());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
