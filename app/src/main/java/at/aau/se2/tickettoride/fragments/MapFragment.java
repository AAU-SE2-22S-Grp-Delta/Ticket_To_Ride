package at.aau.se2.tickettoride.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;
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

    Bitmap bm = Bitmap.createBitmap(3840, 2160, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bm);
    Paint paint = new Paint();
    Boolean drawn = false;

    final int PINK = Color.rgb(188, 143, 143);
    final int ORANGE = Color.rgb(255, 127, 0);

    Player currentPlayer;

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

        Destination atlanta = new Destination("Atlanta", binding.atlanta);
        Destination boston = new Destination("Boston", binding.boston);
        Destination calgary = new Destination("Calgary", binding.calgary);
        Destination chicago = new Destination("Chicago", binding.chicago);
        Destination dallas = new Destination("Dallas", binding.dallas);
        Destination denver = new Destination("Denver", binding.denver);
        Destination duluth = new Destination("Duluth", binding.duluth);
        Destination elpaso = new Destination("El Paso", binding.elpaso);
        Destination helena = new Destination("Helena", binding.helena);
        Destination houston = new Destination("Houston", binding.houston);
        Destination kansascity = new Destination("Kansas City", binding.kansasCity);
        Destination littlerock = new Destination("Little Rock", binding.littleRock);
        Destination losangeles = new Destination("Los Angeles", binding.losangeles);
        Destination miami = new Destination("Miami", binding.miami);
        Destination montréal = new Destination("Montréal", binding.montreal);
        Destination nashville = new Destination("Nashville", binding.nashville);
        Destination neworleans = new Destination("New Orleans", binding.newOrleans);
        Destination newyork = new Destination("New York", binding.newYork);
        Destination oklahomacity = new Destination("Oklahoma City", binding.oklahomaCity);
        Destination phoenix = new Destination("Phoenix", binding.phoenix);
        Destination pittsburgh = new Destination("Pittsburgh", binding.pittsburgh);
        Destination portland = new Destination("Portland", binding.portland);
        Destination saltlakecity = new Destination("Salt Lake City", binding.saltLakeCity);
        Destination sanfrancisco = new Destination("San Francisco", binding.sanfrancisco);
        Destination santafe = new Destination("Santa Fe", binding.santaFe);
        Destination saultstmarie = new Destination("Sault St. Marie", binding.saultStMarie);
        Destination seattle = new Destination("Seattle", binding.seattle);
        Destination toronto = new Destination("Toronto", binding.toronto);
        Destination vancouver = new Destination("Vancouver", binding.vancouver);
        Destination winnipeg = new Destination("Winnipeg", binding.winnipeg);
        Destination omaha = new Destination("Omaha", binding.omaha);
        Destination washington = new Destination("Washington", binding.washington);
        Destination lasvegas = new Destination("Las Vegas", binding.lasVegas);
        Destination charleston = new Destination("Charleston", binding.charleston);
        Destination saintlouis = new Destination("Saint Louis", binding.saintLouis);
        Destination raleigh = new Destination("Raleigh", binding.raileigh);


        destinations = new ArrayList<>();
        destinations.add(raleigh);
        destinations.add(charleston);
        destinations.add(saintlouis);
        destinations.add(lasvegas);
        destinations.add(washington);
        destinations.add(omaha);
        destinations.add(atlanta);
        destinations.add(boston);
        destinations.add(calgary);
        destinations.add(chicago);
        destinations.add(dallas);
        destinations.add(denver);
        destinations.add(duluth);
        destinations.add(elpaso);
        destinations.add(helena);
        destinations.add(houston);
        destinations.add(kansascity);
        destinations.add(littlerock);
        destinations.add(losangeles);
        destinations.add(miami);
        destinations.add(montréal);
        destinations.add(nashville);
        destinations.add(neworleans);
        destinations.add(newyork);
        destinations.add(oklahomacity);
        destinations.add(phoenix);
        destinations.add(pittsburgh);
        destinations.add(portland);
        destinations.add(saltlakecity);
        destinations.add(sanfrancisco);
        destinations.add(santafe);
        destinations.add(saultstmarie);
        destinations.add(seattle);
        destinations.add(toronto);
        destinations.add(vancouver);
        destinations.add(winnipeg);


        railroads = new ArrayList<>();
        railroads.add(new RailroadLine(vancouver, calgary, Color.GRAY, 3));
        railroads.add(new RailroadLine(calgary, winnipeg, Color.WHITE, 6));
        railroads.add(new RailroadLine(winnipeg, saultstmarie, Color.GRAY, 6));
        railroads.add(new RailroadLine(saultstmarie, montréal, Color.BLACK, 5));
//        railroads.add(new DoubleRailroadLine(montréal, boston, Color.GRAY, 2, Color.GRAY));
        railroads.add(new RailroadLine(montréal, boston, Color.GRAY, 2));

        railroads.add(new RailroadLine(montréal, newyork, Color.BLUE, 3));
        railroads.add(new RailroadLine(montréal, toronto, Color.GRAY, 3));

//        railroads.add(new DoubleRailroadLine(newyork, boston, Color.YELLOW, 2, Color.RED));
//        railroads.add(new DoubleRailroadLine(newyork, pittsburgh, Color.YELLOW, 2, Color.GREEN));
        railroads.add(new RailroadLine(newyork, boston, Color.YELLOW, 2));
        railroads.add(new RailroadLine(newyork, pittsburgh, Color.YELLOW, 2));

        railroads.add(new RailroadLine(toronto, pittsburgh, Color.GRAY, 2));
        railroads.add(new RailroadLine(toronto, saultstmarie, Color.GRAY, 2));
        railroads.add(new RailroadLine(toronto, duluth, PINK, 6));
        railroads.add(new RailroadLine(saultstmarie, duluth, Color.GRAY, 3));
        railroads.add(new RailroadLine(duluth, winnipeg, Color.BLACK, 4));
        railroads.add(new RailroadLine(winnipeg, helena, Color.BLUE, 4));
        railroads.add(new RailroadLine(helena, calgary, Color.GRAY, 4));
        railroads.add(new RailroadLine(helena, duluth, ORANGE, 6));
        railroads.add(new RailroadLine(helena, seattle, Color.YELLOW, 6));

//        railroads.add(new DoubleRailroadLine(seattle, vancouver, Color.GRAY, 1, Color.GRAY));
        railroads.add(new RailroadLine(seattle, vancouver, Color.GRAY, 1));

        railroads.add(new RailroadLine(seattle, calgary, Color.GRAY, 4));

//        railroads.add(new DoubleRailroadLine(seattle, portland, Color.GRAY, 1, Color.GRAY));
//        railroads.add(new DoubleRailroadLine(portland, sanfrancisco, Color.GREEN, 5, Color.PINK));
//        railroads.add(new DoubleRailroadLine(sanfrancisco, saltlakecity, Color.ORANGE, 5, Color.WHITE));
        railroads.add(new RailroadLine(seattle, portland, Color.GRAY, 1));
        railroads.add(new RailroadLine(portland, sanfrancisco, Color.GREEN, 5));
        railroads.add(new RailroadLine(sanfrancisco, saltlakecity, ORANGE, 5));

        railroads.add(new RailroadLine(saltlakecity, portland, Color.BLUE, 6));
        railroads.add(new RailroadLine(saltlakecity, helena, PINK, 3));
        railroads.add(new RailroadLine(helena, omaha, Color.RED, 5));

//        railroads.add(new DoubleRailroadLine(omaha, duluth, Color.GRAY, 2, Color.GRAY));
        railroads.add(new RailroadLine(omaha, duluth, Color.GRAY, 2));

        railroads.add(new RailroadLine(duluth, chicago, Color.RED, 3));
        railroads.add(new RailroadLine(chicago, toronto, Color.WHITE, 4));

//        railroads.add(new DoubleRailroadLine(newyork, washington, Color.ORANGE, 2, Color.BLACK));
        railroads.add(new RailroadLine(newyork, washington, ORANGE, 2));

        railroads.add(new RailroadLine(washington, pittsburgh, Color.GRAY, 2));
        railroads.add(new RailroadLine(pittsburgh, chicago, Color.BLACK, 3));
        railroads.add(new RailroadLine(chicago, omaha, Color.BLUE, 4));
        railroads.add(new RailroadLine(omaha, denver, PINK, 4));
        railroads.add(new RailroadLine(denver, helena, Color.GREEN, 4));
        railroads.add(new RailroadLine(denver, saltlakecity, Color.YELLOW, 3));
        railroads.add(new RailroadLine(saltlakecity, lasvegas, ORANGE, 3));
        railroads.add(new RailroadLine(lasvegas, losangeles, Color.GRAY, 2));
        railroads.add(new RailroadLine(losangeles, phoenix, Color.GRAY, 3));
        railroads.add(new RailroadLine(phoenix, elpaso, Color.GRAY, 3));
        railroads.add(new RailroadLine(elpaso, losangeles, Color.BLACK, 6));

//        railroads.add(new DoubleRailroadLine(losangeles, sanfrancisco, Color.YELLOW, 3, Color.PINK));
        railroads.add(new RailroadLine(losangeles, sanfrancisco, Color.YELLOW, 3));

        railroads.add(new RailroadLine(santafe, denver, Color.GRAY, 2));
        railroads.add(new RailroadLine(santafe, oklahomacity, Color.BLUE, 3));
        railroads.add(new RailroadLine(santafe, elpaso, Color.GRAY, 2));
        railroads.add(new RailroadLine(santafe, phoenix, Color.GRAY, 3));
        railroads.add(new RailroadLine(elpaso, oklahomacity, Color.YELLOW, 5));
        railroads.add(new RailroadLine(elpaso, dallas, Color.RED, 4));
        railroads.add(new RailroadLine(elpaso, houston, Color.GREEN, 6));
        railroads.add(new RailroadLine(houston, neworleans, Color.RED, 2));

//        railroads.add(new DoubleRailroadLine(houston, dallas, Color.GRAY, 1, Color.GRAY));
        railroads.add(new RailroadLine(houston, dallas, Color.GRAY, 1));

        railroads.add(new RailroadLine(dallas, littlerock, Color.GRAY, 2));

//        railroads.add(new DoubleRailroadLine(dallas, oklahomacity, Color.GRAY, 2, Color.GRAY));
        railroads.add(new RailroadLine(dallas, oklahomacity, Color.GRAY, 2));

        railroads.add(new RailroadLine(oklahomacity, littlerock, Color.GRAY, 2));

//        railroads.add(new DoubleRailroadLine(oklahomacity, kansascity, Color.GRAY, 2, Color.GRAY));
        railroads.add(new RailroadLine(oklahomacity, kansascity, Color.GRAY, 2));

        railroads.add(new RailroadLine(oklahomacity, denver, Color.RED, 4));
        railroads.add(new RailroadLine(littlerock, neworleans, Color.GREEN, 3));
        railroads.add(new RailroadLine(littlerock, nashville, Color.WHITE, 3));
        railroads.add(new RailroadLine(littlerock, saintlouis, Color.GRAY, 2));
        railroads.add(new RailroadLine(neworleans, miami, Color.RED, 6));

//        railroads.add(new DoubleRailroadLine(neworleans, atlanta, Color.ORANGE, 4, Color.YELLOW));
        railroads.add(new RailroadLine(neworleans, atlanta, ORANGE, 4));

        railroads.add(new RailroadLine(atlanta, miami, Color.BLUE, 5));
        railroads.add(new RailroadLine(atlanta, charleston, Color.GRAY, 2));

//        railroads.add(new DoubleRailroadLine(atlanta, raleigh, Color.GRAY, 2, Color.GRAY));
        railroads.add(new RailroadLine(atlanta, raleigh, Color.GRAY, 2));

        railroads.add(new RailroadLine(atlanta, nashville, Color.GRAY, 1));
        railroads.add(new RailroadLine(miami, charleston, PINK, 4));
        railroads.add(new RailroadLine(charleston, raleigh, Color.GRAY, 2));

//        railroads.add(new DoubleRailroadLine(raleigh, washington, Color.GRAY, 2, Color.GRAY));
        railroads.add(new RailroadLine(raleigh, washington, Color.GRAY, 2));

        railroads.add(new RailroadLine(raleigh, pittsburgh, Color.GRAY, 2));
        railroads.add(new RailroadLine(raleigh, nashville, Color.BLACK, 3));
        railroads.add(new RailroadLine(nashville, pittsburgh, Color.YELLOW, 4));
        railroads.add(new RailroadLine(nashville, saintlouis, Color.GRAY, 2));
        railroads.add(new RailroadLine(saintlouis, pittsburgh, Color.GREEN, 5));

//        railroads.add(new DoubleRailroadLine(saintlouis, chicago, Color.GREEN, 2, Color.WHITE));
//        railroads.add(new DoubleRailroadLine(saintlouis, kansascity, Color.BLUE, 2, Color.PINK));
//        railroads.add(new DoubleRailroadLine(kansascity, omaha, Color.GRAY, 1, Color.GRAY));
//        railroads.add(new DoubleRailroadLine(kansascity, denver, Color.BLACK, 4, Color.ORANGE));
        railroads.add(new RailroadLine(saintlouis, chicago, Color.GREEN, 2));
        railroads.add(new RailroadLine(saintlouis, kansascity, Color.BLUE, 2));
        railroads.add(new RailroadLine(kansascity, omaha, Color.GRAY, 1));
        railroads.add(new RailroadLine(kansascity, denver, Color.BLACK, 4));

        railroads.add(new RailroadLine(denver, phoenix, Color.WHITE, 5));


        canvas.drawColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setAntiAlias(true);

        Player player1 = new Player("player one", Color.RED);
        //Set currentPlayer to whoevers turn it is
        currentPlayer = player1;

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> railroads.forEach((railroadLine) ->
        {
            railroadLine.buildRoad(canvas, paint, bm, binding.drawView);
            binding.drawView.setImageBitmap(bm);
            drawn = true;
        }), 250);

        for (Destination d : destinations)
        {
            d.getButton().setOnClickListener(view1 -> selector(d, currentPlayer));
        }
        return view;
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    List<Destination> reachable = new ArrayList<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    public void selector(Destination secondDest, Player player)
    {
        if (firstDest == null)
            firstDestHandler(secondDest);
        else if(firstDest.equals(secondDest))
        {
            firstDest = null;
            resetButtons();
        }
        else if(reachable.contains(secondDest))
        {
            secondDest.getButton().setBackground(getResources().getDrawable(R.drawable.seldestination2));
            secondDest.getButton().setOnClickListener(view -> railBuilder(secondDest, player));
        }
        else
        {
            resetButtons();
            firstDestHandler(secondDest);
        }
    }

    public void firstDestHandler(Destination secondDest)
    {
        firstDest = secondDest;
        for (RailroadLine rl : railroads)
        {
            if (rl.getDestination1().equals(firstDest))
                reachable.add(rl.getDestination2());
            else if (rl.getDestination2().equals(firstDest))
                reachable.add(rl.getDestination1());
        }
        btnHighlighter(firstDest, reachable);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void btnHighlighter(Destination d, List<Destination> reachable)
    {
        d.getButton().setBackground(getResources().getDrawable(R.drawable.seldestination2));
        for (Destination rd : reachable)
            rd.getButton().setBackground(getResources().getDrawable(R.drawable.seldestination));
    }

    public void railBuilder(Destination secondDest, Player player)
    {
        RailroadLine rl = new RailroadLine(firstDest, secondDest);
        if (railroads.contains(rl))
        {
            RailroadLine currentRoad = railroads.get(railroads.indexOf(rl));
            currentRoad.buildRoad(canvas, paint, bm, binding.drawView, player);
        }
        resetButtons();
        firstDest = null;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void resetButtons()
    {
        reachable.clear();
        for (Destination d : destinations)
        {
            d.getButton().setBackground(getResources().getDrawable(R.drawable.destination));
            d.getButton().setOnClickListener(view1 -> selector(d, currentPlayer));
        }
    }
}