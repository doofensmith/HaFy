package com.softlaboratory.hafy.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softlaboratory.hafy.ChatRoom;
import com.softlaboratory.hafy.Edukasi;
import com.softlaboratory.hafy.Konsultasi;
import com.softlaboratory.hafy.Notification;
import com.softlaboratory.hafy.Pencarian;
import com.softlaboratory.hafy.Pengacara;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.adapter.AEducationCategory;
import com.softlaboratory.hafy.adapter.AFeaturedArticle;
import com.softlaboratory.hafy.adapter.AHomeArticle;
import com.softlaboratory.hafy.adapter.AHomeContainer;
import com.softlaboratory.hafy.adapter.AProfessionalAccount;
import com.softlaboratory.hafy.model.MAccount;
import com.softlaboratory.hafy.model.MArticle;
import com.softlaboratory.hafy.model.MEducationCategory;
import com.softlaboratory.hafy.model.MHomeContainer;
import com.softlaboratory.hafy.viewholder.VHEducationCategory;
import com.softlaboratory.hafy.viewholder.VHFeaturedArticle;
import com.softlaboratory.hafy.viewholder.VHHomeArticle;
import com.softlaboratory.hafy.viewholder.VHHomeContainer;
import com.softlaboratory.hafy.viewholder.VHProfessionalAccount;

import java.util.Arrays;


public class Beranda extends Fragment {

    public Beranda() {
        // Required empty public constructor
    }

    //declare view
    AppBarLayout appBarLayout;
    MaterialToolbar collapsingToolbar;
    //MaterialToolbar toolbar;
    MaterialCardView menuKonsultasi;
    MaterialCardView menuPengacara;
    MaterialCardView menuEdukasi;

    //firebase
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    //firestore
    FirestoreRecyclerOptions<MHomeContainer> optionsContainer;
    FirestoreRecyclerAdapter<MHomeContainer, VHHomeContainer> adapterContainer;
    RecyclerView recyclerViewContainer;
    Query queryContainer;
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
        //toolbar = rootView.findViewById(R.id.fr_beranda_toolbar);
        menuKonsultasi = rootView.findViewById(R.id.menu_beranda_konsultasi);
        menuPengacara = rootView.findViewById(R.id.menu_beranda_pengacara);
        menuEdukasi = rootView.findViewById(R.id.menu_beranda_edukasi);

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
        //toolbar action option
        //toolbar.setOnMenuItemClickListener(onMenuItemClickListener(rootView));
        collapsingToolbar.setOnMenuItemClickListener(onMenuItemClickListener(rootView));

        //main menu
        mainMenu();

        //INFLATE RECYCLER VIEW
        //option
        //query
        queryContainer = firestore.collection("home")
                .orderBy("groupId", Query.Direction.ASCENDING);
        optionsContainer = new FirestoreRecyclerOptions.Builder<MHomeContainer>()
                .setLifecycleOwner((LifecycleOwner) rootView.getContext())
                .setQuery(queryContainer,MHomeContainer.class)
                .build();
        //adapter
        adapterContainer = new AHomeContainer(optionsContainer,rootView.getContext(),auth,firestore);
        //recyclerview
        recyclerViewContainer = rootView.findViewById(R.id.fragment_home_recyclerview);
        recyclerViewContainer.setAdapter(adapterContainer);

//        //INFLATE DATA FEATURED ARTICLE
//        //option
//        //query
//        queryFeaturedArticle = firestore.collection("articles").whereEqualTo("featured",true);
//        optionsFeaturedArticle = new FirestoreRecyclerOptions.Builder<MArticle>()
//                .setLifecycleOwner(Beranda.this)
//                .setQuery(queryFeaturedArticle,MArticle.class).build();
//        //adapter
//        adapterFeaturedArticle = new AFeaturedArticle(optionsFeaturedArticle);
//        //recyclerview
//        recyclerViewFeaturedArticle = rootView.findViewById(R.id.item_beranda_featuredarticle_recyclerview);
//        recyclerViewFeaturedArticle.setAdapter(adapterFeaturedArticle);
//        //recyclerview snap
//        SnapHelper snapHelperFeaturedArticle = new LinearSnapHelper();
//        snapHelperFeaturedArticle.attachToRecyclerView(recyclerViewFeaturedArticle);
//
//        //INFLATE DATA PROFESSIONAL ACCOUNT
//        //option
//        //query
//        queryAccountPro = firestore.collection("account")
//                .whereNotIn("keyAccount", Arrays.asList(auth.getUid()))
//                .whereEqualTo("professional",true)
//                .limit(5);
//        optionsAccountPro = new FirestoreRecyclerOptions.Builder<MAccount>()
//                .setLifecycleOwner(Beranda.this)
//                .setQuery(queryAccountPro,MAccount.class).build();
//        //adapter
//        adapterAccountPro = new AProfessionalAccount(optionsAccountPro,Beranda.this.getContext());
//        //recyclerview
//        recyclerViewAccountPro = rootView.findViewById(R.id.item_beranda_proaccount_recyclerview);
//        recyclerViewAccountPro.setAdapter(adapterAccountPro);
//        //recyclerview snap
////        SnapHelper snapHelperAccountPro = new LinearSnapHelper();
////        snapHelperAccountPro.attachToRecyclerView(recyclerViewFeaturedArticle);
//
//        //INFLATE DATA EDUCATION CATEGORY
//        //option
//        //query
//        queryEducationCategory = firestore.collection("education_category");
//        optionsEducationCategory = new FirestoreRecyclerOptions.Builder<MEducationCategory>()
//                .setLifecycleOwner(Beranda.this)
//                .setQuery(queryEducationCategory,MEducationCategory.class).build();
//        //adapter
//        adapterEducationCategory = new AEducationCategory(optionsEducationCategory);
//        recyclerViewEducationCategory = rootView.findViewById(R.id.item_beranda_edukasi_recyclerview);
//        recyclerViewEducationCategory.setAdapter(adapterEducationCategory);
//
//        //INFLATE DATA EDUCATION CATEGORY
//        //option
//        //query
//        queryHomeArticle = firestore.collection("articles").limit(10);
//        optionsHomeArticle = new FirestoreRecyclerOptions.Builder<MArticle>()
//                .setLifecycleOwner(Beranda.this)
//                .setQuery(queryHomeArticle,MArticle.class).build();
//        //adapter
//        adapterHomeArticle = new AHomeArticle(optionsHomeArticle);
//        recyclerViewHomeArticle = rootView.findViewById(R.id.item_beranda_article_recyclerview);
//        recyclerViewHomeArticle.setAdapter(adapterHomeArticle);

        return rootView;
    }

    //toolbar menu item
    Toolbar.OnMenuItemClickListener onMenuItemClickListener(View view) {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_toolbar_beranda_cari:
                        Intent intentSearch = new Intent(view.getContext(), Pencarian.class);
                        startActivity(intentSearch);
                        return true;
                    case R.id.menu_toolbar_beranda_pesan:
                        Intent intentMessage = new Intent(view.getContext(), ChatRoom.class);
                        startActivity(intentMessage);
                        return true;
                    case R.id.menu_toolbar_beranda_notifikasi:
                        Intent intentNotification = new Intent(view.getContext(), Notification.class);
                        startActivity(intentNotification);
                        return true;
                }
                return false;
            }
        };
    }

    AppBarLayout.OnOffsetChangedListener onOffsetChangedListener() {
        return new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0
                //verticalOffset <= -250
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0) {
                    //set theme
                    collapsingToolbar.setTitleTextColor(Color.BLACK);
                    collapsingToolbar.getMenu().getItem(0).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.getMenu().getItem(1).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.getMenu().getItem(2).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    //collapsingToolbar.getOverflowIcon().setColorFilter(Color.BLACK);
                    //set elevation
                    appBarLayout.setElevation(8);

                    //warna status bar putih
                    if (Build.VERSION.SDK_INT>=21) {
                        Window window = Beranda.this.getActivity().getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(Beranda.this.getResources().getColor(R.color.white));
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                }else {
                    //expanded
                    //set theme
                    collapsingToolbar.setTitleTextColor(Color.WHITE);
                    collapsingToolbar.getMenu().getItem(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.getMenu().getItem(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.getMenu().getItem(2).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //set elevation
                    appBarLayout.setElevation(0);
                    //warna status bar merah
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

    //fungsi menu
    void mainMenu() {

        menuKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Konsultasi.class);
                startActivity(intent);
            }
        });

        menuPengacara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pengacara.class);
                startActivity(intent);
            }
        });

        menuEdukasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Edukasi.class);
                startActivity(intent);
            }
        });
    }
}