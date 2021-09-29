package com.softtech.hafy.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softtech.hafy.R;
import com.softtech.hafy.adapter.AEducationCategory;
import com.softtech.hafy.adapter.AFeaturedArticle;
import com.softtech.hafy.adapter.AHomeArticle;
import com.softtech.hafy.adapter.AProfessionalAccount;
import com.softtech.hafy.model.MAccount;
import com.softtech.hafy.model.MArticle;
import com.softtech.hafy.model.MEducationCategory;
import com.softtech.hafy.viewholder.VHEducationCategory;
import com.softtech.hafy.viewholder.VHFeaturedArticle;
import com.softtech.hafy.viewholder.VHHomeArticle;
import com.softtech.hafy.viewholder.VHProfessionalAccount;


public class Beranda extends Fragment {

    public Beranda() {
        // Required empty public constructor
    }

    //declare view
    AppBarLayout appBarLayout;
    MaterialToolbar collapsingToolbar;
    MaterialToolbar toolbar;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    //featured article
    FirestoreRecyclerOptions<MArticle> optionsFeaturedArticle;
    FirestoreRecyclerAdapter<MArticle, VHFeaturedArticle> adapterFeaturedArticle;
    RecyclerView recyclerViewFeaturedArticle;
    Query queryFeaturedArticle;
    //professional account
    FirestoreRecyclerOptions<MAccount> optionsAccountPro;
    FirestoreRecyclerAdapter<MAccount, VHProfessionalAccount> adapterAccountPro;
    RecyclerView recyclerViewAccountPro;
    Query queryAccountPro;
    //kategori edukasi
    FirestoreRecyclerOptions<MEducationCategory> optionsEducationCategory;
    FirestoreRecyclerAdapter<MEducationCategory, VHEducationCategory> adapterEducationCategory;
    RecyclerView recyclerViewEducationCategory;
    Query queryEducationCategory;
    //artikel beranda
    FirestoreRecyclerOptions<MArticle> optionsHomeArticle;
    FirestoreRecyclerAdapter<MArticle, VHHomeArticle> adapterHomeArticle;
    RecyclerView recyclerViewHomeArticle;
    Query queryHomeArticle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        //auth n db
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

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
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener());

        //INFLATE DATA FEATURED ARTICLE
        //option
        //query
        queryFeaturedArticle = firestore.collection("articles").whereEqualTo("featured",true);
        optionsFeaturedArticle = new FirestoreRecyclerOptions.Builder<MArticle>()
                .setLifecycleOwner(Beranda.this)
                .setQuery(queryFeaturedArticle,MArticle.class).build();
        //adapter
        adapterFeaturedArticle = new AFeaturedArticle(optionsFeaturedArticle);
        //recyclerview
        recyclerViewFeaturedArticle = rootView.findViewById(R.id.item_beranda_featuredarticle_recyclerview);
        recyclerViewFeaturedArticle.setAdapter(adapterFeaturedArticle);
        //recyclerview snap
        SnapHelper snapHelperFeaturedArticle = new LinearSnapHelper();
        snapHelperFeaturedArticle.attachToRecyclerView(recyclerViewFeaturedArticle);

        //INFLATE DATA PROFESSIONAL ACCOUNT
        //option
        //query
        queryAccountPro = firestore.collection("account").whereEqualTo("accountType","Professional");
        optionsAccountPro = new FirestoreRecyclerOptions.Builder<MAccount>()
                .setLifecycleOwner(Beranda.this)
                .setQuery(queryAccountPro,MAccount.class).build();
        //adapter
        adapterAccountPro = new AProfessionalAccount(optionsAccountPro);
        //recyclerview
        recyclerViewAccountPro = rootView.findViewById(R.id.item_beranda_proaccount_recyclerview);
        recyclerViewAccountPro.setAdapter(adapterAccountPro);
        //recyclerview snap
//        SnapHelper snapHelperAccountPro = new LinearSnapHelper();
//        snapHelperAccountPro.attachToRecyclerView(recyclerViewFeaturedArticle);

        //INFLATE DATA EDUCATION CATEGORY
        //option
        //query
        queryEducationCategory = firestore.collection("education_category");
        optionsEducationCategory = new FirestoreRecyclerOptions.Builder<MEducationCategory>()
                .setLifecycleOwner(Beranda.this)
                .setQuery(queryEducationCategory,MEducationCategory.class).build();
        //adapter
        adapterEducationCategory = new AEducationCategory(optionsEducationCategory);
        recyclerViewEducationCategory = rootView.findViewById(R.id.item_beranda_edukasi_recyclerview);
        recyclerViewEducationCategory.setAdapter(adapterEducationCategory);

        //INFLATE DATA EDUCATION CATEGORY
        //option
        //query
        queryHomeArticle = firestore.collection("articles").limit(10);
        optionsHomeArticle = new FirestoreRecyclerOptions.Builder<MArticle>()
                .setLifecycleOwner(Beranda.this)
                .setQuery(queryHomeArticle,MArticle.class).build();
        //adapter
        adapterHomeArticle = new AHomeArticle(optionsHomeArticle);
        recyclerViewHomeArticle = rootView.findViewById(R.id.item_beranda_article_recyclerview);
        recyclerViewHomeArticle.setAdapter(adapterHomeArticle);

        return rootView;
    }

    AppBarLayout.OnOffsetChangedListener onOffsetChangedListener() {
        return new AppBarLayout.OnOffsetChangedListener() {
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
        };
    }
}