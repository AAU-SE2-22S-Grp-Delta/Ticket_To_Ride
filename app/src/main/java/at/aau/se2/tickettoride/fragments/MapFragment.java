package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        View view = binding.getRoot(); //the root is this view

        binding.mapPanel.setScaleX(1.f);
        binding.mapPanel.setScaleY(1.f);

        binding.mapPanel.setOnTouchListener(new View.OnTouchListener() {
            private float mx, my, curX, curY;
            private boolean started = false;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                curX = motionEvent.getX();
                curY = motionEvent.getY();
                int dx = (int) (mx - curX);
                int dy = (int) (my - curY);
                Log.d("onTouchListener", "curX:" + curX);
                Log.d("onTouchListener", "curY:" + curX);
                Log.d("onTouchListener", "mx:" + mx);
                Log.d("onTouchListener", "my:" + my);

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (started) {
                            binding.mapPanel.scrollBy(dx,dy);
                        } else {
                            started = true;
                        }
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        started = false;
                        Log.d("onTouchListner=stopp", "scrollX="+binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
                        break;
                }


                return true;
            }
        });
//        view.setOnTouchListener(this);

        binding.buttonAbove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.mapPanel.setScaleX(binding.mapPanel.getScaleX()+.2f);
                binding.mapPanel.setScaleY(binding.mapPanel.getScaleY()+.2f);
                Log.d("buttonPos", "x=" + binding.buttonAbove.getX() + ", y=" + binding.buttonAbove.getY());
                Log.d("mapPanelPos", "x=" + binding.mapPanel.getX() + ", y=" + binding.mapPanel.getY());
                Log.d("mapPanelSize", "x=" + binding.mapPanel.getWidth() + ", y=" + binding.mapPanel.getHeight());
            }
        });

        binding.buttonDownside.setOnClickListener(view1 -> {
            binding.mapPanel.setScaleX(binding.mapPanel.getScaleX()-.2f);
            binding.mapPanel.setScaleY(binding.mapPanel.getScaleY()-.2f);
            Log.d("buttonPos", "x=" + binding.buttonDownside.getX() + ", y=" + binding.buttonDownside.getY());
            Log.d("buttonSize", "x=" + binding.buttonDownside.getWidth() + ", y=" + binding.buttonDownside.getHeight());
            Log.d("mapPanelPos", "x=" + binding.mapPanel.getX() + ", y=" + binding.mapPanel.getY());
            Log.d("mapPanelSize", "x=" + binding.mapPanel.getWidth() + ", y=" + binding.mapPanel.getHeight());
            View mapPanel = binding.mapPanel;
        });


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    @Override
//    /**
//     * prints the motion event to a textbox to visualize what is happening
//     */
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        String message = "";
//        message += motionEvent.toString() + ", x: " + motionEvent.getX() + ", y: " + motionEvent.getY();
//        binding.message.setText(message);
//        return true;
//    }
}