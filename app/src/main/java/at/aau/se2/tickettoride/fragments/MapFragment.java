package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

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

        binding.mapPanel.setOnTouchListener(new MapOnTouchListener());

//        view.setOnTouchListener(this);

        binding.buttonAbove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.mapPanel.setScaleX(binding.mapPanel.getScaleX() + .2f);
                binding.mapPanel.setScaleY(binding.mapPanel.getScaleY() + .2f);
                Log.d("buttonPos", "x=" + binding.buttonAbove.getX() + ", y=" + binding.buttonAbove.getY());
                Log.d("mapPanelPos", "x=" + binding.mapPanel.getX() + ", y=" + binding.mapPanel.getY());
                Log.d("mapPanelSize", "x=" + binding.mapPanel.getWidth() + ", y=" + binding.mapPanel.getHeight());
            }
        });

        binding.buttonDownside.setOnClickListener(view1 -> {
            binding.mapPanel.setScaleX(binding.mapPanel.getScaleX() - .2f);
            binding.mapPanel.setScaleY(binding.mapPanel.getScaleY() - .2f);
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

    private class MapOnTouchListener implements View.OnTouchListener {
        private float mx, my, curX, curY;
        private float zoomLength0, zoomLength;
        float scale = 1f;
        int smoothScale = 0;
        private boolean scrollingStarted = false, zoomingStarted = false;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
//            Log.d("onTouchListener.Action", "" + motionEvent.getAction());
            //0=Action_Down, 1=Action_UP, 2=Action_Move, 6=ActionPointer_UP, 261=2ndPointerUp?


//            Log.d("onTouchListener", "curX:" + curX);
//            Log.d("onTouchListener", "curY:" + curX);
//            Log.d("onTouchListener", "mx:" + mx);
//            Log.d("onTouchListener", "my:" + my);

            switch (motionEvent.getPointerCount()) {

                case 1: {
                    curX = motionEvent.getX();
                    curY = motionEvent.getY();
                    int dx = (int) (mx - curX);
                    int dy = (int) (my - curY);

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            if (scrollingStarted) {
                                binding.mapPanel.scrollBy(dx, dy);
                            } else {
                                scrollingStarted = true;
                            }
                            mx = curX;
                            my = curY;
                            break;
                        case MotionEvent.ACTION_UP:
                            scrollingStarted = false;
//                            Log.d("mapPanel.scrollingPos", "scrollX=" + binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
                            break;
                    }

                }
                break;
                case 2: {


                    Log.d("onTouchListener.Action", "" + motionEvent.getAction());
                    float x0 = motionEvent.getX(0);
                    float y0 = motionEvent.getY(0);
                    float x1 = motionEvent.getX(1);
                    float y1 = motionEvent.getY(1);

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            if (zoomingStarted) {
                                zoomLength = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
                                Log.d("onTouchListener.zoom", "zoomFactor=" + zoomLength / zoomLength0 + ", l0=" + zoomLength0 + ", l1=" + zoomLength);
                                smoothScale++;
//                                if (smoothScale == 1) {
                                    float tempScale = scale * zoomLength/zoomLength0;
                                    binding.mapPanel.setScaleX(tempScale);
                                    binding.mapPanel.setScaleY(tempScale);
//                                    smoothScale = 0;
//                                }
                            } else {
                                zoomLength0 = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
                                zoomingStarted = true;
                            }
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            zoomingStarted = false;
                            scale *= zoomLength / zoomLength0;
                            binding.mapPanel.setScaleX(scale);
                            binding.mapPanel.setScaleY(scale);
                            Log.d("onTouchListener.zoom", "stop zooming, scale=" + scale);
                            break;
                    }
                }
                break;
            }
            return true;
        }
    }
}