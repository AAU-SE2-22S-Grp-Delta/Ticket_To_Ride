package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;
import at.aau.se2.tickettoride.databinding.FragmentMapBinding;
import at.aau.se2.tickettoride.eventListeners.MapOnTouchListener;

public class MapFragment extends Fragment
{
    private FragmentMapBinding binding;
    private Destination firstDest = null;
    private ArrayList<Destination> destinations = new ArrayList<>();
    private ArrayList<RailroadLine> railroads = new ArrayList<>();

    public static MapFragment newInstance()
    {
        return new MapFragment();
    }

    Destination dest1 = null;
    Destination dest2 = null;
    Destination dest3 = null;
    Destination dest4 = null;
    Destination dest5 = null;
    Destination dest6 = null;
    Destination dest7 = null;
    Destination dest8 = null;
    Destination dest9 = null;
    Destination dest10 = null;
    Destination dest11 = null;
    Destination dest12 = null;
    RailroadLine r1 = null;
    RailroadLine r2 = null;
    RailroadLine r3 = null;
    RailroadLine r4 = null;
    RailroadLine r5 = null;
    RailroadLine r6 = null;
    RailroadLine r7 = null;
    RailroadLine r8 = null;
    RailroadLine r9 = null;
    RailroadLine r10 = null;
    RailroadLine r11 = null;
    RailroadLine r12 = null;
    RailroadLine r13 = null;
    RailroadLine r14 = null;
    RailroadLine r15 = null;
    RailroadLine r16 = null;
    RailroadLine r17 = null;
    RailroadLine r18 = null;
    RailroadLine r19 = null;
    RailroadLine r20 = null;
    RailroadLine r21 = null;
    RailroadLine r22 = null;
    Bitmap bm = Bitmap.createBitmap(3840, 2160, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bm);
    Paint paint = new Paint();
    Boolean drawn = false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot(); //the root is this view

        binding.mapPanel.setScaleX(1f);
        binding.mapPanel.setScaleY(1f);

        MapOnTouchListener mapOnTouchListener = new MapOnTouchListener(binding.mapFragment, binding.mapPanel);
        binding.mapFragment.setOnTouchListener(mapOnTouchListener);
        binding.mapFragment.addOnLayoutChangeListener(mapOnTouchListener);

        ImageView dv = binding.drawView;

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        dest1 = new Destination("dest1", binding.dest1);
        dest2 = new Destination("dest2", binding.dest2);
        dest3 = new Destination("dest3", binding.dest3);
        dest4 = new Destination("dest4", binding.dest4);
        dest5 = new Destination("dest5", binding.dest5);
        dest6 = new Destination("dest6", binding.dest6);
        dest7 = new Destination("dest7", binding.dest7);
        dest8 = new Destination("dest8", binding.dest8);
        dest9 = new Destination("dest9", binding.dest9);
        dest10 = new Destination("dest10", binding.dest10);
        dest11 = new Destination("dest11", binding.dest11);
        dest12 = new Destination("dest12", binding.dest12);

        destinations = new ArrayList<>();
        destinations.add(dest1);
        destinations.add(dest2);
        destinations.add(dest3);
        destinations.add(dest4);
        destinations.add(dest5);
        destinations.add(dest6);
        destinations.add(dest7);
        destinations.add(dest8);
        destinations.add(dest9);
        destinations.add(dest10);
        destinations.add(dest11);
        destinations.add(dest12);

        r1 = new RailroadLine(dest1, dest2, Color.GRAY, 1);
        r2 = new RailroadLine(dest2, dest3, Color.BLUE, 2);
        r3 = new RailroadLine(dest3, dest4, Color.GREEN, 3);
        r4 = new RailroadLine(dest4, dest6, Color.BLUE, 2);
        r5 = new RailroadLine(dest6, dest7, Color.YELLOW, 3);
        r6 = new RailroadLine(dest5, dest7, Color.RED, 3);
        r7 = new RailroadLine(dest5, dest8, Color.GREEN, 3);
        r8 = new RailroadLine(dest8, dest9, Color.RED, 1);
        r9 = new RailroadLine(dest1, dest5, Color.GRAY, 2);
        r10 = new RailroadLine(dest8, dest12, Color.GRAY, 2);
        r11 = new RailroadLine(dest12, dest10, Color.GREEN, 3);
        r12 = new RailroadLine(dest11, dest10, Color.RED, 1);

        r14 = new RailroadLine(dest9, dest12, Color.YELLOW, 2);
        r15 = new RailroadLine(dest7, dest9, Color.GRAY, 2);
        r16 = new RailroadLine(dest6, dest11, Color.GREEN, 3);

        r18 = new RailroadLine(dest2, dest5, Color.GRAY, 2);
        r19 = new RailroadLine(dest5, dest7, Color.MAGENTA, 3);
        r20 = new RailroadLine(dest4, dest7, Color.RED, 1);
        r21 = new RailroadLine(dest11, dest9, Color.BLUE, 1);
        r22 = new RailroadLine(dest5, dest3, Color.MAGENTA, 2);


        canvas.drawColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setAntiAlias(true);

        railroads = new ArrayList<>();
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
        railroads.add(r14);
        railroads.add(r15);
        railroads.add(r16);
        railroads.add(r18);
        railroads.add(r19);
        railroads.add(r20);
        railroads.add(r21);
        railroads.add(r22);

        Player player1 = new Player("player one", Color.RED);
        //Set currentPlayer to whoever's turn it is
        Player currentPlayer = player1;
        Destination[] destinations = {dest1, dest2, dest3, dest4, dest5, dest6, dest7, dest8, dest9, dest10, dest11, dest12};


        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                railroads.forEach((railroadLine) ->
                {
                    railroadLine.buildRoad(canvas, paint, bm, binding.drawView);
                    binding.drawView.setImageBitmap(bm);
                    drawn = true;
                });
            }
        }, 50);

        for (Destination d : destinations)
        {
            d.getButton().setOnClickListener(view1 -> {
                railBuilder(d, currentPlayer);
            });
        }
        return view;
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void railBuilder(Destination secondDest, Player player)
    {
        if (firstDest == null)
        {
            firstDest = secondDest;
            firstDest.getButton().setBackground(getResources().getDrawable(R.drawable.seldestination));
        }
        else if (firstDest.equals(secondDest))
        {
            firstDest.getButton().setBackground(getResources().getDrawable(R.drawable.destination));
            firstDest = null;
        }
        else
        {
            secondDest.getButton().setBackground(getResources().getDrawable(R.drawable.seldestination));
            //TODO Confirmation Dialog or confirmation button???
            RailroadLine rl = new RailroadLine(firstDest, secondDest);
            if (railroads.contains(rl))
            {
                RailroadLine currentRoad = railroads.get(railroads.indexOf(rl));
                currentRoad.buildRoad(canvas, paint, bm, binding.drawView, player);
            }
            firstDest.getButton().setBackground(getResources().getDrawable(R.drawable.destination));
            secondDest.getButton().setBackground(getResources().getDrawable(R.drawable.destination));
            firstDest = null;
        }
    }
}