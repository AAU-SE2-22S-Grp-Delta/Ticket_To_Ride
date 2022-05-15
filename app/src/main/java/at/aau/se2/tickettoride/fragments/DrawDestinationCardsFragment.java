package at.aau.se2.tickettoride.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentDrawDestinationCardsBinding;

public class DrawDestinationCardsFragment extends Fragment implements View.OnClickListener {
    private FragmentDrawDestinationCardsBinding binding;

    ArrayList<DestinationCard> cardsList = new ArrayList<>();

    DestinationCard calSal = new DestinationCard("Calgary","Salt Lake City",7, R.drawable.calgary_saltlakecity);
    DestinationCard kanHou = new DestinationCard("Kansas City","Houston",5,R.drawable.kansascity_houston);
    DestinationCard losChi = new DestinationCard("Los Angeles","Houston", 16,R.drawable.losangeles_chicago);
    DestinationCard newAtl = new DestinationCard("New York","Atlanta", 6,R.drawable.newyork_atlanta);
    DestinationCard porPho = new DestinationCard("Portland","Phoenix",11,R.drawable.portland_phoenix);
    DestinationCard seaNew = new DestinationCard("Seattle","New York", 22,R.drawable.seattle_newyork);

    public static DrawDestinationCardsFragment newInstance() {
        return new DrawDestinationCardsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrawDestinationCardsBinding.inflate(inflater, container, false);

        cardsList.add(calSal);
        cardsList.add(kanHou);
        cardsList.add(losChi);
        cardsList.add(newAtl);
        cardsList.add(porPho);
        cardsList.add(seaNew);

        Random randomCard1 = new Random();
        int card1 = randomCard1.nextInt(cardsList.size());
        binding.imageView.setImageResource(cardsList.get(card1).getImage());
        binding.imageView.setTag(cardsList.get(card1));

        Random randomCard2 = new Random();
        int card2 = randomCard2.nextInt(cardsList.size());
        while (card1 == card2) {
            randomCard2 = new Random();
            card2 = randomCard2.nextInt(cardsList.size());
        }
        binding.imageView2.setImageResource(cardsList.get(card2).getImage());
        binding.imageView2.setTag(cardsList.get(card2));

        Random randomCard3 = new Random();
        int card3 = randomCard3.nextInt(cardsList.size());
        while (card1 == card3 || card2 == card3) {
            randomCard3 = new Random();
            card3 = randomCard3.nextInt(cardsList.size());
        }
        binding.imageView3.setImageResource(cardsList.get(card3).getImage());
        binding.imageView3.setTag(cardsList.get(card3));


        binding.button6.setOnClickListener(this);
        binding.imageView.setOnClickListener(this);
        binding.imageView2.setOnClickListener(this);
        binding.imageView3.setOnClickListener(this);


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
        if (view.getId() == binding.button6.getId()) {

            ArrayList<DestinationCard> cards = new ArrayList<>();

            if (((ColorDrawable) binding.imageView.getBackground()).getColor() == Color.GREEN) {
                cards.add((DestinationCard) binding.imageView.getTag());
            }
            if (((ColorDrawable) binding.imageView2.getBackground()).getColor() == Color.GREEN) {
                cards.add((DestinationCard) binding.imageView2.getTag());
            }
            if (((ColorDrawable) binding.imageView3.getBackground()).getColor() == Color.GREEN) {
                cards.add((DestinationCard) binding.imageView3.getTag());
            }

            ArrayList<DestinationCard> roadCards = new ArrayList<>();

            if (cards.size() == 2) {
                DestinationCard roadCard1 = cards.get(0);
                DestinationCard roadCard2 = cards.get(1);

                binding.imageView.setImageResource(roadCard1.getImage());
                binding.imageView2.setImageResource(roadCard2.getImage());
                binding.imageView3.setVisibility(View.GONE);
                binding.button6.setVisibility(View.GONE);

                roadCards.add(roadCard1);
                roadCards.add(roadCard2);
            }
            else if (cards.size() == 3) {
                DestinationCard roadCard1 = cards.get(0);
                DestinationCard roadCard2 = cards.get(1);
                DestinationCard roadCard3 = cards.get(2);

                binding.imageView.setImageResource(roadCard1.getImage());
                binding.imageView2.setImageResource(roadCard2.getImage());
                binding.imageView3.setImageResource(roadCard3.getImage());
                binding.button6.setVisibility(View.GONE);

                roadCards.add(roadCard1);
                roadCards.add(roadCard2);
                roadCards.add(roadCard3);
            }
        }
    }
}



