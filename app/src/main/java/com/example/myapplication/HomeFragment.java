package com.example.myapplication;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Locale;

public class HomeFragment extends Fragment {
    private FloatingActionButton lngBtn;
    private SliderView sliderView;
    int[] images = {R.drawable.st_petersburg_1, R.drawable.st_petersburg_2, R.drawable.st_petersburg_3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sliderView = view.findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();

        lngBtn = view.findViewById(R.id.fab_language);
        adjustFabPosition(lngBtn);
        lngBtn.setOnClickListener(v -> {
            SharedPreferences prefs = requireActivity().getSharedPreferences("Settings", requireActivity().MODE_PRIVATE);
            String currentLanguage = prefs.getString("My_Lang", "en");
            String newLanguage = currentLanguage.equals("en") ? "ar" : "en";
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("My_Lang", newLanguage);
            editor.apply();
            setLocale(newLanguage);
        });
    }

    private void adjustFabPosition(FloatingActionButton fab) {
        fab.post(() -> {
            if (!isAdded()) return; // Ensure fragment is still attached

            float offsetDp = 300f;
            float offsetPx = offsetDp * getResources().getDisplayMetrics().density;
            View parentView = (View) fab.getParent();

            if (getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                // For RTL, calculate the position from the right edge of the parent
                int parentWidth = parentView.getWidth();
                float newX = parentWidth - offsetPx - fab.getWidth();
                fab.setX(newX);
            } else {
                // For LTR, set the FAB's x position using the offset from the left edge
                fab.setX(offsetPx);
            }
        });
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources res = requireActivity().getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(locale);
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        requireActivity().recreate();
    }
}
