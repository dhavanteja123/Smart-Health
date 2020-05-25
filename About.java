package com.example.daavan.smarthealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class About extends Fragment {

    private String previousTitle;
    private String previousSubTitle;
    private ActionBar actionBar;
    private ScrollView scrollView;
    private boolean isHomeAsUpShown = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about, container, false);

        scrollView = (ScrollView) rootView.findViewById(R.id.about_container);
        scrollView.startAnimation(
                AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in)
        );
        scrollView.setVisibility(View.VISIBLE);
//        SET ACTION BAR
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            isHomeAsUpShown = (actionBar.getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) != 0;
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            previousTitle = actionBar.getTitle().toString();
            previousSubTitle = actionBar.getSubtitle().toString();
            actionBar.setTitle(R.string.about);
            actionBar.setSubtitle(R.string.dhavan_teja);
        }
////        JUST ADD android:autoLink="web|email" IN CORRESPONDING XML LAYOUT FILE
////        AND YOU DON'T NEED THE FOLLOWING SNIPPET!
//
//          CustomTextViewSemiLight emailLink = (CustomTextViewSemiLight) rootView.findViewById(R.id.email_link);
//        emailLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.fromParts(
//                        "mailto","dhavan.teja@gmail.com", null));
//                emailIntent.setType("text/email");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, R.string.email);
//                startActivity(Intent.createChooser(emailIntent, "Send email"));
//            }
//        });
        TextView dhavanteja_link = (TextView) rootView.findViewById(R.id.dhavanteja_link);
        dhavanteja_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=dhavanteja")));
                } catch (android.content.ActivityNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "No app found for this action!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_about, menu);
    }

    @Override
    public void onDestroy() {
        if (!isHomeAsUpShown){
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        actionBar.setTitle(previousTitle);
        actionBar.setSubtitle(previousSubTitle);
        scrollView.setVisibility(View.INVISIBLE);
        super.onDestroy();
    }
}
