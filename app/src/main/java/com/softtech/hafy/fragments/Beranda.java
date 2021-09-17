package com.softtech.hafy.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.softtech.hafy.R;


public class Beranda extends Fragment {

    public Beranda() {
        // Required empty public constructor
    }

    //declare view
    AppBarLayout appBarLayout;
    MaterialToolbar collapsingToolbar;
    MaterialToolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        //init view
        appBarLayout = rootView.findViewById(R.id.fr_beranda_appbar);
        collapsingToolbar = rootView.findViewById(R.id.fr_beranda_coll_toolbar);
        toolbar = rootView.findViewById(R.id.fr_beranda_toolbar);

        //warna status bar
        if (Build.VERSION.SDK_INT>=21) {
            Window window = Beranda.this.getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Beranda.this.getResources().getColor(R.color.primary));
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getWindowSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //app bar collapsed / expanded
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0
                if (verticalOffset <= -250) {
                    //collapsed
                    //toolbar visible
                    toolbar.setVisibility(View.VISIBLE);
                    //set elevation
                    appBarLayout.setElevation(8);

                    //warna status bar
                    if (Build.VERSION.SDK_INT>=21) {
                        Window window = Beranda.this.getActivity().getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(Beranda.this.getResources().getColor(R.color.white));
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                }else {
                    System.out.println(toolbar.getHeight());
                    System.out.println(appBarLayout.getTotalScrollRange());
                    System.out.println(verticalOffset);
                    //expanded
                    //toolbar gone
                    toolbar.setVisibility(View.GONE);
                    //set elevation
                    appBarLayout.setElevation(0);
                    //warna status bar
                    if (Build.VERSION.SDK_INT>=21) {
                        Window window = Beranda.this.getActivity().getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(Beranda.this.getResources().getColor(R.color.primary));
                        window.getDecorView().setSystemUiVisibility(window.getDecorView().getWindowSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                }
            }
        });

        return rootView;
    }
}