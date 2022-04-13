package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.aau.se2.tickettoride.eventListeners.MapOnTouchListener;
import at.aau.se2.tickettoride.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {
    private FragmentMapBinding binding;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot(); //the root is this view?,

        binding.mapPanel.setScaleX(1f);
        binding.mapPanel.setScaleY(1f);

        MapOnTouchListener mapOnTouchListener = new MapOnTouchListener(binding.mapFragment, binding.mapPanel);
        binding.mapFragment.setOnTouchListener(mapOnTouchListener);
        binding.mapFragment.addOnLayoutChangeListener(mapOnTouchListener);

        //How to add buttons at runtime
        Button button = new Button(binding.mapPanel.getContext());
        button.setText("Added at runtime!");
        button.setLayoutParams(new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 500, 500));
        button.setOnClickListener(view1 -> {
            binding.mapPanel.setScrollX(-0);
            binding.mapPanel.setScrollY(-0);
            Log.d("mapPanel.scrollingPos", "scrollX=" + binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
        });
        binding.mapPanel.addView(button);

        binding.buttonAbove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.mapPanel.setScaleX(.5f);
//                binding.mapPanel.setScaleY(.5f);
//                binding.mapPanel.scrollTo(-1362,-522);
//                Log.d("mapPanel.scrollingPos", "scrollX=" + binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
                binding.mapFragment.scrollBy(-100, -100);
            }
        });

        binding.buttonDownside.setOnClickListener(view1 -> {
//            int width = binding.mapPanel.getWidth();
//            int height = binding.mapPanel.getHeight();
//            int widthFragment = binding.map.getWidth();
//            int heightFragment = binding.map.getHeight();
            binding.mapPanel.scrollTo(0, 0);
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}