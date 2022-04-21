package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;
import at.aau.se2.tickettoride.databinding.FragmentMapBinding;
import at.aau.se2.tickettoride.eventListeners.MapOnTouchListener;

public class MapFragment extends Fragment
{
    private FragmentMapBinding binding;
    private Destination firstDest = null;

    public static MapFragment newInstance()
    {
        return new MapFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot(); //the root is this view

        MapOnTouchListener mapOnTouchListener = new MapOnTouchListener(binding.mapFragment, binding.mapPanel);
        binding.mapFragment.setOnTouchListener(mapOnTouchListener);
        binding.mapFragment.addOnLayoutChangeListener(mapOnTouchListener);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


        Destination dest1 = new Destination("dest1", binding.dest1);
        Destination dest2 = new Destination("dest2", binding.dest2);
        Destination dest3 = new Destination("dest3", binding.dest3);
        Destination dest4 = new Destination("dest4", binding.dest4);
        Destination dest5 = new Destination("dest5", binding.dest5);
        Destination dest6 = new Destination("dest6", binding.dest6);
        Destination dest7 = new Destination("dest7", binding.dest7);
        Destination dest8 = new Destination("dest8", binding.dest8);
        Destination dest9 = new Destination("dest9", binding.dest9);
        Destination dest10 = new Destination("dest10", binding.dest10);
        Destination dest11 = new Destination("dest11", binding.dest11);
        Destination dest12 = new Destination("dest12", binding.dest12);

        RailroadLine r1 = new RailroadLine(dest1, dest2, Color.GRAY, 1);
        RailroadLine r2 = new RailroadLine(dest2, dest3, Color.BLUE, 2);
        RailroadLine r3 = new RailroadLine(dest3, dest4, Color.GREEN, 3);
        RailroadLine r4 = new RailroadLine(dest4, dest6, Color.BLUE, 2);
        RailroadLine r5 = new RailroadLine(dest6, dest7, Color.YELLOW, 3);
        RailroadLine r6 = new RailroadLine(dest5, dest7, Color.RED, 3);
        RailroadLine r7 = new RailroadLine(dest5, dest8, Color.GREEN, 3);
        RailroadLine r8 = new RailroadLine(dest8, dest9, Color.RED, 1);
        RailroadLine r9 = new RailroadLine(dest1, dest5, Color.GRAY, 2);
        RailroadLine r10 = new RailroadLine(dest8, dest12, Color.GRAY, 2);
        RailroadLine r11 = new RailroadLine(dest12, dest10, Color.GREEN, 3);
        RailroadLine r12 = new RailroadLine(dest11, dest10, Color.RED, 1);
        RailroadLine r13 = new RailroadLine(dest10, dest6, Color.RED, 1);
        RailroadLine r14 = new RailroadLine(dest9, dest12, Color.YELLOW, 2);
        RailroadLine r15 = new RailroadLine(dest7, dest9, Color.GRAY, 2);
        RailroadLine r16 = new RailroadLine(dest6, dest11, Color.GREEN, 3);
        RailroadLine r17 = new RailroadLine(dest6, dest8, Color.RED, 1);
        RailroadLine r18 = new RailroadLine(dest2, dest5, Color.GRAY, 2);
        RailroadLine r19 = new RailroadLine(dest5, dest7, Color.MAGENTA, 3);
        RailroadLine r20 = new RailroadLine(dest4, dest7, Color.RED, 1);
        RailroadLine r21 = new RailroadLine(dest11, dest9, Color.BLUE, 1);
        RailroadLine r22 = new RailroadLine(dest5, dest3, Color.MAGENTA, 2);


        Bitmap bm = Bitmap.createBitmap(3840, 2160, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        canvas.drawColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setAntiAlias(true);

        ArrayList<RailroadLine> railroads = new ArrayList<>();
        railroads.add(r1);
        railroads.add(r2);
        railroads.add(r3);
        railroads.add(r4);
        railroads.add(r5);
        railroads.add(r6);
        railroads.add(r7);
        railroads.add(r8);
        railroads.add(r9);
        railroads.add(r10);
        railroads.add(r11);
        railroads.add(r12);
        railroads.add(r13);
        railroads.add(r14);
        railroads.add(r15);
        railroads.add(r16);
        railroads.add(r17);
        railroads.add(r18);
        railroads.add(r19);
        railroads.add(r20);
        railroads.add(r21);
        railroads.add(r22);


        Player player1 = new Player("player one", 1, Color.RED);

        Destination[] destinations = {dest1, dest2, dest3, dest4, dest5, dest6, dest7, dest8, dest9, dest10, dest11, dest12};

        for (
                Destination d : destinations)

        {
            d.getButton().setOnClickListener(view1 -> {
                try
                {
                    if (firstDest != null)
                    {
                        RailroadLine rl = new RailroadLine(firstDest, d);
                        if (railroads.contains(rl))
                        {
                            RailroadLine currentRoad = railroads.get(railroads.indexOf(rl));
                            paint.setXfermode(null);
                            currentRoad.buildRoad(canvas, paint, bm, binding.drawView);
                            builder.setTitle("Build Road");
                            builder.setMessage("Do you wish to build the railroad from " + currentRoad.getDestination1().getName() + " to " + currentRoad.getDestination2().getName()
                                    + " with a cost of: " + currentRoad.getDistance() + "?");
                            builder.setPositiveButton("Confirm", (dialogInterface, i) -> {

                                currentRoad.buildRoad(canvas, paint, bm, binding.drawView, player1);
                                firstDest = null;
                            });
                            builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                                currentRoad.buildRoad(canvas, paint, bm, binding.drawView);
                                firstDest = null;
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } else
                        {
                            Log.d("Error", "not a valid road");
                            firstDest = null;
                        }
                    } else
                        firstDest = d;
                } catch (Exception e)
                {
                    //handle errors via popup?
                    firstDest = null;
                    System.out.println(e.getMessage());
                }
            });
        }
        binding.buttonAbove.setOnClickListener(view1 ->

        {
        });

        binding.buttonDownside.setOnClickListener(view1 ->

        {

        });

        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    public void initFields()
    {
        //for later, cleaner code
    }
}

//    private class MapOnTouchListener implements View.OnTouchListener
//    {
//        private float mx, my, curX, curY;
//        private float zoomLength0, zoomLength;
//        float scale = 1f;
//        int smoothScale = 0;
//        private boolean scrollingStarted = false, zoomingStarted = false;
//
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent)
//        {
////            Log.d("onTouchListener.Action", "" + motionEvent.getAction());
//            //0=Action_Down, 1=Action_UP, 2=Action_Move, 6=ActionPointer_UP, 261=2ndPointerUp?
//
//
////            Log.d("onTouchListener", "curX:" + curX);
////            Log.d("onTouchListener", "curY:" + curX);
////            Log.d("onTouchListener", "mx:" + mx);
////            Log.d("onTouchListener", "my:" + my);
//
//            switch (motionEvent.getPointerCount())
//            {
//                case 1:
//                {
//                    curX = motionEvent.getX();
//                    curY = motionEvent.getY();
//                    int dx = (int) (mx - curX);
//                    int dy = (int) (my - curY);
//
//                    switch (motionEvent.getAction())
//                    {
//                        case MotionEvent.ACTION_MOVE:
//                            if (scrollingStarted)
//                            {
//                                binding.mapPanel.scrollBy(dx, dy);
//                            } else
//                            {
//                                scrollingStarted = true;
//                            }
//                            mx = curX;
//                            my = curY;
//                            break;
//                        case MotionEvent.ACTION_UP:
//                            scrollingStarted = false;
////                            Log.d("mapPanel.scrollingPos", "scrollX=" + binding.mapPanel.getScrollX() + ", scrollY=" + binding.mapPanel.getScrollY());
//                            break;
//                    }
//                }
//                break;
//                case 2:
//                {
//                    Log.d("onTouchListener.Action", "" + motionEvent.getAction());
//                    float x0 = motionEvent.getX(0);
//                    float y0 = motionEvent.getY(0);
//                    float x1 = motionEvent.getX(1);
//                    float y1 = motionEvent.getY(1);
//
//                    switch (motionEvent.getAction())
//                    {
//                        case MotionEvent.ACTION_MOVE:
//                            if (zoomingStarted)
//                            {
//                                zoomLength = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
//                                Log.d("onTouchListener.zoom", "zoomFactor=" + zoomLength / zoomLength0 + ", l0=" + zoomLength0 + ", l1=" + zoomLength);
//                                smoothScale++;
////                                if (smoothScale == 1) {
//                                float tempScale = scale * zoomLength / zoomLength0;
//                                binding.mapPanel.setScaleX(tempScale);
//                                binding.mapPanel.setScaleY(tempScale);
////                                    smoothScale = 0;
////                                }
//                            } else
//                            {
//                                zoomLength0 = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
//                                zoomingStarted = true;
//                            }
//                            break;
//                        case MotionEvent.ACTION_POINTER_UP:
//                            zoomingStarted = false;
//                            scale *= zoomLength / zoomLength0;
//                            binding.mapPanel.setScaleX(scale);
//                            binding.mapPanel.setScaleY(scale);
//                            Log.d("onTouchListener.zoom", "stop zooming, scale=" + scale);
//                            break;
//                    }
//                }
//                break;
//            }
//            return true;
//        }
//    }
//}