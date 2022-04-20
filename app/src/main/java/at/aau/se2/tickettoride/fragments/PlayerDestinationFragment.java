package at.aau.se2.tickettoride.fragments;

import android.graphics.Color;
import android.graphics.RecordingCanvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentPlayerDestinationBinding;

public class PlayerDestinationFragment extends Fragment implements View.OnClickListener {
    private FragmentPlayerDestinationBinding binding;

    public static PlayerDestinationFragment newInstance() {
        return new PlayerDestinationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerDestinationBinding.inflate(inflater, container, false);

        ArrayList<DestinationCard> randomCards = new ArrayList<>();

        DestinationCard calSal = new DestinationCard("Calgary","Salt Lake City",7, R.drawable.calgary_saltlakecity);
        DestinationCard kanHou = new DestinationCard("Kansas City","Houston",5,R.drawable.kansascity_houston);
        DestinationCard losChi = new DestinationCard("Los Angeles","Houston", 16,R.drawable.losangeles_chicago);
        DestinationCard newAtl = new DestinationCard("New York","Atlanta", 6,R.drawable.newyork_atlanta);
        DestinationCard porPho = new DestinationCard("Portland","Phoenix",11,R.drawable.portland_phoenix);
        DestinationCard seaNew = new DestinationCard("Seattle","New York", 22,R.drawable.seattle_newyork);

        randomCards.add(calSal);
        randomCards.add(kanHou);
        randomCards.add(losChi);
        randomCards.add(newAtl);
        randomCards.add(porPho);
        randomCards.add(seaNew);


        Random randomCard1 = new Random();
        int card1 = randomCard1.nextInt(randomCards.size());
        binding.imageView.setImageResource(randomCards.get(card1).getImage());

        Random randomCard2 = new Random();
        int card2 = randomCard2.nextInt(randomCards.size());
        while (card1 == card2) {
            randomCard2 = new Random();
            card2 = randomCard2.nextInt(randomCards.size());
        }
        binding.imageView2.setImageResource(randomCards.get(card2).getImage());

        Random randomCard3 = new Random();
        int card3 = randomCard3.nextInt(randomCards.size());
        while (card1 == card3 || card2 == card3) {
            randomCard3 = new Random();
            card3 = randomCard3.nextInt(randomCards.size());
        }
        binding.imageView3.setImageResource(randomCards.get(card3).getImage());

        Random randomCard4 = new Random();
        int card4 = randomCard4.nextInt(randomCards.size());
        while (card1 == card4 || card2 == card4 || card3 == card4) {
            randomCard4 = new Random();
            card4 = randomCard4.nextInt(randomCards.size());
        }
        binding.imageView4.setImageResource(randomCards.get(card4).getImage());

        binding.button6.setOnClickListener(this);
        binding.imageView.setOnClickListener(this);
        binding.imageView2.setOnClickListener(this);
        binding.imageView3.setOnClickListener(this);
        binding.imageView4.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.imageView.getId()) {
            if (((ColorDrawable) binding.imageView.getBackground()).getColor() == Color.GREEN) {
                binding.imageView.setBackgroundColor(Color.BLACK);
            } else {
                binding.imageView.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.imageView2.getId()) {
            if (((ColorDrawable) binding.imageView2.getBackground()).getColor() == Color.GREEN) {
                binding.imageView2.setBackgroundColor(Color.BLACK);
            } else {
                binding.imageView2.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.imageView3.getId()) {
            if (((ColorDrawable) binding.imageView3.getBackground()).getColor() == Color.GREEN) {
                binding.imageView3.setBackgroundColor(Color.BLACK);
            } else {
                binding.imageView3.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.imageView4.getId()) {
            if (((ColorDrawable) binding.imageView4.getBackground()).getColor() == Color.GREEN) {
                binding.imageView4.setBackgroundColor(Color.BLACK);
            } else {
                binding.imageView4.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.button6.getId()) {

            ArrayList<ImageView> cards = new ArrayList<>();

            if (((ColorDrawable) binding.imageView.getBackground()).getColor() == Color.GREEN) {
                cards.add(binding.imageView);
            }
            if (((ColorDrawable) binding.imageView3.getBackground()).getColor() == Color.GREEN) {
                cards.add(binding.imageView3);
            }
            if (((ColorDrawable) binding.imageView2.getBackground()).getColor() == Color.GREEN) {
                cards.add(binding.imageView2);
            }
            if (((ColorDrawable) binding.imageView4.getBackground()).getColor() == Color.GREEN) {
                cards.add(binding.imageView4);
            }

            ArrayList<Drawable> roadCards = new ArrayList<>();
            if (cards.size() == 2) {
                Drawable roadCard1 = cards.get(0).getDrawable();
                Drawable roadCard2 = cards.get(1).getDrawable();

                binding.imageView.setImageDrawable(roadCard1);
                binding.imageView3.setImageDrawable(roadCard2);
                binding.imageView2.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.GONE);
                binding.button6.setVisibility(View.GONE);

                roadCards.add(roadCard1);
                roadCards.add(roadCard2);
            } else if (cards.size() == 3) {
                Drawable roadCard1 = cards.get(0).getDrawable();
                Drawable roadCard2 = cards.get(1).getDrawable();
                Drawable roadCard3 = cards.get(2).getDrawable();

                binding.imageView.setImageDrawable(roadCard1);
                binding.imageView3.setImageDrawable(roadCard2);
                binding.imageView2.setImageDrawable(roadCard3);
                binding.imageView4.setVisibility(View.GONE);
                binding.button6.setVisibility(View.GONE);

                roadCards.add(roadCard1);
                roadCards.add(roadCard2);
                roadCards.add(roadCard3);
            } else if (cards.size() == 4) {
                Drawable roadCard1 = cards.get(0).getDrawable();
                Drawable roadCard2 = cards.get(1).getDrawable();
                Drawable roadCard3 = cards.get(2).getDrawable();
                Drawable roadCard4 = cards.get(3).getDrawable();

                binding.imageView.setImageDrawable(roadCard1);
                binding.imageView3.setImageDrawable(roadCard2);
                binding.imageView2.setImageDrawable(roadCard3);
                binding.imageView4.setImageDrawable(roadCard4);
                binding.button6.setVisibility(View.GONE);

                roadCards.add(roadCard1);
                roadCards.add(roadCard2);
                roadCards.add(roadCard3);
                roadCards.add(roadCard4);
            }


        }
    }
}


