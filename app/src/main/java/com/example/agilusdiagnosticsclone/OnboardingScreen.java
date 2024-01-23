package com.example.agilusdiagnosticsclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.agilusdiagnosticsclone.*;
import com.example.agilusdiagnosticsclone.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingScreen extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private MaterialButton buttonOnboardingAction;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);
        layoutOnboardingIndicator = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnBoardingAction);

        setOnboardingItem();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setOnboadingIndicator();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), LocationScreen.class));
                    finish();
                }
            }
        });
    }
    private void setOnboadingIndicator() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.baseline_circle_24
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ringbutton));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_circle_24));
            }
        }
        if (index == onboardingAdapter.getItemCount()-1){
            buttonOnboardingAction.setText("Go Home");
        }else {
            buttonOnboardingAction.setText("Next");
        }
    }
    private void setOnboardingItem() {
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        OnBoardingItem itemFastFood = new OnBoardingItem();
        itemFastFood.setTitle("Fast Delivery to your home");
        itemFastFood.setDescription("Our delivery partner is on the way to your home!");
        itemFastFood.setImage(R.drawable.onboardimg1);

        OnBoardingItem itemPayOnline = new OnBoardingItem();
        itemPayOnline.setTitle("E-Pay your bill online");
        itemPayOnline.setDescription("Electric bill payment is a feature of online, mobile and net banking!");
        itemPayOnline.setImage(R.drawable.onboardimg2);

        OnBoardingItem itemEatTogether = new OnBoardingItem();
        itemEatTogether.setTitle("Eat together");
        itemEatTogether.setDescription("Enjoy your meal and have a great day. Don’t forget to rate us!");
        itemEatTogether.setImage(R.drawable.onboardimg3);

        onBoardingItems.add(itemFastFood);
        onBoardingItems.add(itemPayOnline);
        onBoardingItems.add(itemEatTogether);

        onboardingAdapter = new OnboardingAdapter(onBoardingItems);


    }
}
