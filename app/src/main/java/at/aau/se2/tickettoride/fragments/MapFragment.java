package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;

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
        View view = binding.getRoot(); //the root is this view?,

        binding.mapPanel.setScaleX(1.f);
        binding.mapPanel.setScaleY(1.f);

        MapOnTouchListener mapOnTouchListener = new MapOnTouchListener();
        binding.mapPanel.setOnTouchListener(mapOnTouchListener);
        binding.map.addOnLayoutChangeListener(mapOnTouchListener);

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
//                int width = binding.mapPanel.getWidth();
//                int height = binding.mapPanel.getHeight();
//                int widthFragment = binding.map.getWidth();
//                int heightFragment = binding.map.getHeight();
//
//                binding.mapPanel.scrollTo((width - widthFragment) / 2, (height - heightFragment) / 2);
//                Log.d("scroll.pos", "" + binding.mapPanel.getScrollX() + ", " + binding.mapPanel.getScrollY());
                binding.mapPanel.scrollBy(100,0);
                Log.d("mapPanel.scrollingPos", "scrollX=" + binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
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

    private class MapOnTouchListener implements View.OnTouchListener, View.OnLayoutChangeListener {
        //Attributes for scrolling
        private float mx, my, curX, curY;
        private boolean scrollingStarted = false;
        private int scrollBoundX, scrollBoundY;

        //Attributes for zooming
        private float zoomLength0, zoomLength;
        private float scale = 1f, minScale = -1, maxScale = 2; //TODO: what is a good max scale?
        private boolean zoomingStarted = false;


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
//            Log.d("onTouchListener.Action", "" + motionEvent.getAction());
            //0=Action_Down, 1=Action_UP, 2=Action_Move, 6=ActionPointer_UP, 261=2ndPointerUp?


//            Log.d("onTouchListener", "curX:" + curX);
//            Log.d("onTouchListener", "curY:" + curX);
//            Log.d("onTouchListener", "mx:" + mx);
//            Log.d("onTouchListener", "my:" + my);

            switch (motionEvent.getPointerCount()) {
                case 1: { //occurs when there is one finger on the screen
                    curX = motionEvent.getX();
                    curY = motionEvent.getY();
                    int dx = (int) (mx - curX);
                    int dy = (int) (my - curY);

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            if (scrollingStarted) {
                                View mapPanel = binding.mapPanel;
                                if (mapPanel.getScrollX() + dx < -scrollBoundX)
                                    mapPanel.setScrollX(-scrollBoundX);
                                else if (mapPanel.getScrollX() + dx > scrollBoundX)
                                    mapPanel.setScrollX(scrollBoundX);
                                else mapPanel.scrollBy(dx, 0);
                                if (mapPanel.getScrollY() + dy < -scrollBoundY)
                                    mapPanel.setScrollY(-scrollBoundY);
                                else if (mapPanel.getScrollY() + dy > scrollBoundY)
                                    mapPanel.setScrollY(scrollBoundY);
                                else mapPanel.scrollBy(0, dy);
//                                mapPanel.scrollBy(dx, dy);
                            } else {
                                scrollingStarted = true;
                            }
                            mx = curX;
                            my = curY;
                            break;
                        case MotionEvent.ACTION_UP:
                            scrollingStarted = false;
                            Log.d("mapPanel.scrollingPos", "scrollX=" + binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
                            break;
                    }

                }
                break;

                case 2: {  //when there are two fingers on the screen
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

                                float tempScale = scale * zoomLength / zoomLength0;
                                if (tempScale < minScale) tempScale = minScale;
                                if (tempScale > maxScale) tempScale = maxScale;
                                binding.mapPanel.setScaleX(tempScale);
                                binding.mapPanel.setScaleY(tempScale);
                            } else {
                                zoomLength0 = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
                                zoomingStarted = true;
                            }
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            zoomingStarted = false;
                            scale *= zoomLength / zoomLength0;
                            if (scale < minScale) scale = minScale;
                            if (scale > maxScale) scale = maxScale;
                            binding.mapPanel.setScaleX(scale);
                            binding.mapPanel.setScaleY(scale);
                            calculateScrollBounds();
                            Log.d("onTouchListener.zoom", "stop zooming, scale=" + scale);
                            break;
                    }
                }
                break;
            }
            return true;
        }

        private void calculateMinScale() {
            int widthMapPanel = binding.mapPanel.getWidth();
            int heightMapPanel = binding.mapPanel.getHeight();
            int widthMapFragment = binding.map.getWidth();
            int heightMapFragment = binding.map.getHeight();
            if (widthMapPanel == 0 || heightMapPanel == 0 || widthMapFragment == 0 || heightMapFragment == 0)
                return;
            Log.d("onTouch.minScale", "widthMapPanel=" + widthMapPanel + ", heightMapPanel=" + heightMapPanel + ", widthMapFragment=" + widthMapFragment + ", heightMapFragment=" + heightMapFragment);

            float minScaleX = (float) widthMapFragment / widthMapPanel;
            float minScaleY = (float) heightMapFragment / heightMapPanel;
            Log.d("onTouch.minScale", "minScaleX=" + minScaleX + ", minSaleY=" + minScaleY);

            this.minScale = Math.min(minScaleX, minScaleY);
            Log.d("onTouch.minScale", "minScale=" + minScale);
        }

        private void calculateScrollBounds() {
            int width = binding.mapPanel.getWidth();
            int height = binding.mapPanel.getHeight();
            int widthFragment = binding.map.getWidth();
            int heightFragment = binding.map.getHeight();
            Log.d("scroll.messurement", width + ", " + height + ", " + widthFragment + ", " + heightFragment);

            scrollBoundX = (int) (Math.sqrt(width*scale)/2 - widthFragment/2);
            scrollBoundY = (int) ((height * scale) / 2 - heightFragment / 2);
            if (scrollBoundX < 0) scrollBoundX = 0;
            if (scrollBoundY < 0) scrollBoundY = 0;
            Log.d("scroll.bounds@1f", "scale=" + scale + ", bounds: " + scrollBoundX + ", " + scrollBoundY);
        }

        @Override
        public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
            calculateMinScale();
            calculateScrollBounds();
        }
    }
}