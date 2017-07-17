package com.imagineappdev.wallaholic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        /*addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirdFragment);
        addSlide(fourthFragment);
*/
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Welcome to Wallaholic", "It provides a huge collection of HD Wallpapers.", R.drawable.logo, getResources().getColor(R.color.colorStartScreen)));
        addSlide(AppIntroFragment.newInstance("Various Categories", "It gives a wide range of categories to meet everyone's need.", R.drawable.categories, getResources().getColor(R.color.colorStartScreen)));
        addSlide(AppIntroFragment.newInstance("Share Wallpapers", "You can share your wallpapers with your friends.", R.drawable.share_icon, getResources().getColor(R.color.colorStartScreen)));
        addSlide(AppIntroFragment.newInstance("You are all set", "Hope You enjoy Wallaholic.", R.drawable.tick, getResources().getColor(R.color.colorStartScreen)));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);
        setFadeAnimation();
        /*askForPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);*/
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();

    }
}