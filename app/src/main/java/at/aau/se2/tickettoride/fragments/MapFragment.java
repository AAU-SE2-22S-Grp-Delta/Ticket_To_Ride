package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.datastructures.Destination;
import at.aau.se2.tickettoride.datastructures.Player;
import at.aau.se2.tickettoride.datastructures.RailroadLine;
import at.aau.se2.tickettoride.databinding.FragmentMapBinding;
import at.aau.se2.tickettoride.listeners.MapOnTouchListener;
import at.aau.se2.tickettoride.models.GameModel;

public class MapFragment extends Fragment {
    private FragmentMapBinding binding;
    private Destination firstDest = null;
    private final Set<Destination> destinations = GameModel.getInstance().getMap().getDestinations();
    private Set<RailroadLine> railroads = GameModel.getInstance().getMap().getRailroadLines();

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    Bitmap bm = Bitmap.createBitmap(3840, 2160, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bm);
    Paint paint = new Paint();
    Boolean drawn = false;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle.getString("refresh_map", "0").equals("1")) {
                updateMaps();
            }
        }
    };

    Player currentPlayer;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot(); //the root is this view

        binding.mapPanel.setScaleX(1f);
        binding.mapPanel.setScaleY(1f);

        MapOnTouchListener mapOnTouchListener = new MapOnTouchListener(binding.mapFragment, binding.mapPanel);
        binding.mapFragment.setOnTouchListener(mapOnTouchListener);
        binding.mapFragment.addOnLayoutChangeListener(mapOnTouchListener);

        ImageView drawView = binding.drawView;

        initButtons();
        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).registerReceiver(receiver, new IntentFilter("server"));
        canvas.drawColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setAntiAlias(true);



        Player player1 = new Player("player one", Color.RED);
        //Set currentPlayer to whoevers turn it is
        currentPlayer = player1;

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> railroads.forEach(railroadLine ->
        {
            railroadLine.updateRoads(canvas, paint, bm, drawView);
            drawView.setImageBitmap(bm);
            drawn = true;
        }), 250);

        for (Destination d : destinations) {
            d.getButton().setOnClickListener(view1 -> selector(d, currentPlayer));
        }
        return view;
    }




    private void updateMaps()
    {
        Log.d("IMPORTANT", "updating roads");
        railroads = GameModel.getInstance().getMap().getRailroadLines();
        for(RailroadLine r : railroads)
        {
            if(r.isBuilt())
                Log.d("IMPORTANT", "ROAD BUILT FROM " + r.getDestination1().getName() + "TO " + r.getDestination2().getName());
            r.updateRoads(canvas, paint, bm, binding.drawView);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    List<Destination> reachable = new ArrayList<>();

    public void selector(Destination secondDest, Player player) {
        if (firstDest == null)
            firstDestHandler(secondDest);
        else if (firstDest.equals(secondDest)) {
            firstDest = null;
            resetButtons();
        } else if (reachable.contains(secondDest)) {
            secondDest.getButton().setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.seldestination2, null));
            secondDest.getButton().setOnClickListener(view -> railBuilder(secondDest, player));
        } else {
            resetButtons();
            firstDestHandler(secondDest);
        }
    }

    public void firstDestHandler(Destination secondDest) {
        firstDest = secondDest;
        for (RailroadLine rl : railroads) {
            if (rl.getDestination1().equals(firstDest))
                reachable.add(rl.getDestination2());
            else if (rl.getDestination2().equals(firstDest))
                reachable.add(rl.getDestination1());
        }
        btnHighlighter(firstDest, reachable);
    }

    public void btnHighlighter(Destination d, List<Destination> reachable) {
        d.getButton().setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.seldestination2, null));
        for (Destination rd : reachable)
            rd.getButton().setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.seldestination, null));
    }

    public void railBuilder(Destination secondDest, Player player) {
        RailroadLine currentRoad = GameModel.getInstance().getRailroadLineByName(firstDest.getName(), secondDest.getName());
        currentRoad.checkBuild(canvas, paint, bm, binding.drawView);
        resetButtons();
        firstDest = null;
    }

    public void resetButtons() {
        reachable.clear();
        for (Destination d : destinations) {
            d.getButton().setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.destination, null));
            d.getButton().setOnClickListener(view1 -> selector(d, currentPlayer));
        }
    }



    private void initButtons() {
        HashMap<String, Button> destButtons = new HashMap<>();

        destButtons.put("Atlanta",binding.atlanta);
        destButtons.put("Boston",binding.boston);
        destButtons.put("Calgary",binding.calgary);
        destButtons.put("Chicago",binding.chicago);
        destButtons.put("Dallas",binding.dallas);
        destButtons.put("Denver",binding.denver);
        destButtons.put("Duluth",binding.duluth);
        destButtons.put("El Paso",binding.elpaso);
        destButtons.put("Helena",binding.helena);
        destButtons.put("Houston",binding.houston);
        destButtons.put("Kansas City",binding.kansasCity);
        destButtons.put("Little Rock",binding.littleRock);
        destButtons.put("Los Angeles",binding.losangeles);
        destButtons.put("Miami",binding.miami);
        destButtons.put("Montreal",binding.montreal);
        destButtons.put("Nashville",binding.nashville);
        destButtons.put("New Orleans",binding.newOrleans);
        destButtons.put("New York",binding.newYork);
        destButtons.put("Oklahoma City",binding.oklahomaCity);
        destButtons.put("Phoenix",binding.phoenix);
        destButtons.put("Pittsburgh",binding.pittsburgh);
        destButtons.put("Portland",binding.portland);
        destButtons.put("Salt Lake City",binding.saltLakeCity);
        destButtons.put("San Francisco",binding.sanfrancisco);
        destButtons.put("Santa Fe",binding.santaFe);
        destButtons.put("Sault St Marie",binding.saultStMarie);
        destButtons.put("Seattle",binding.seattle);
        destButtons.put("Toronto",binding.toronto);
        destButtons.put("Vancouver",binding.vancouver);
        destButtons.put("Winnipeg",binding.winnipeg);
        destButtons.put("Omaha",binding.omaha);
        destButtons.put("Washington",binding.washington);
        destButtons.put("Las Vegas",binding.lasVegas);
        destButtons.put("Charleston",binding.charleston);
        destButtons.put("Saint Louis",binding.saintLouis);
        destButtons.put("Raleigh",binding.raileigh);

        for (Destination d : this.destinations) {
            Log.d("DrawMap", d.getName());
            Button button = destButtons.get(d.getName());
            d.setButton(button);
        }
        Log.d("DrawMap", "Buttons initialized");
    }


//    private class ButtonRememberer{
//        ArrayList<String> keys = new ArrayList<>();
//        ArrayList<Button> values = new ArrayList<>();
//
//        private void put(String key, Button value) {
//            keys.add(key);
//            values.add(value);
//        }
//        private Button get(String key) {
//            int index = 0;
//            for (String k : keys) {
//                index++;
//                if (k.equals(key)) return values.get(index);
//            }
//            return null;
//        }
//    }
}
