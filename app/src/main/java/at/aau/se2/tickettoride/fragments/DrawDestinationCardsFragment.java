package at.aau.se2.tickettoride.fragments;

import android.content.Intent;
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

import at.aau.se2.tickettoride.activities.GameActivity;
import at.aau.se2.tickettoride.dataStructures.Mission;
import at.aau.se2.tickettoride.databinding.FragmentDrawDestinationCardsBinding;
import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.models.Missions;

public class DrawDestinationCardsFragment extends Fragment implements View.OnClickListener {
    private FragmentDrawDestinationCardsBinding binding;

    public DrawDestinationCardsFragment() {
    }

    public DrawDestinationCardsFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    ArrayList<Mission> missions = Missions.getMissions();
    ArrayList<Mission> missionCards = new ArrayList<>();

    public static DrawDestinationCardsFragment newInstance() {
        return new DrawDestinationCardsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrawDestinationCardsBinding.inflate(inflater, container, false);

        binding.button9.setVisibility(View.INVISIBLE);

        Random randomCard1 = new Random();
        int card1 = randomCard1.nextInt(missions.size());
        binding.imageView.setImageDrawable(ResourceHelper.getMissionResource(getActivity(), missions.get(card1).getId()));
        binding.imageView.setTag(missions.get(card1));

        Random randomCard2 = new Random();
        int card2 = randomCard2.nextInt(missions.size());
        while (card1 == card2) {
            randomCard2 = new Random();
            card2 = randomCard2.nextInt(missions.size());
        }
        binding.imageView2.setImageDrawable(ResourceHelper.getMissionResource(getActivity(), missions.get(card2).getId()));
        binding.imageView2.setTag(missions.get(card2));

        Random randomCard3 = new Random();
        int card3 = randomCard3.nextInt(missions.size());
        while (card1 == card3 || card2 == card3) {
            randomCard3 = new Random();
            card3 = randomCard3.nextInt(missions.size());
        }
        binding.imageView3.setImageDrawable(ResourceHelper.getMissionResource(getActivity(), missions.get(card3).getId()));
        binding.imageView3.setTag(missions.get(card3));


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

            ArrayList<Mission> cards = new ArrayList<>();

            if (((ColorDrawable) binding.imageView.getBackground()).getColor() == Color.GREEN) {
                cards.add((Mission) binding.imageView.getTag());
            }
            if (((ColorDrawable) binding.imageView2.getBackground()).getColor() == Color.GREEN) {
                cards.add((Mission) binding.imageView2.getTag());
            }
            if (((ColorDrawable) binding.imageView3.getBackground()).getColor() == Color.GREEN) {
                cards.add((Mission) binding.imageView3.getTag());
            }


            if (cards.size() == 2) {
                Mission roadCard1 = cards.get(0);
                Mission roadCard2 = cards.get(1);

                binding.imageView.setImageDrawable(ResourceHelper.getMissionResource(getActivity(),roadCard1.getId()));
                binding.imageView.setBackgroundColor(Color.TRANSPARENT);
                binding.imageView.setOnClickListener(null);
                binding.imageView2.setImageDrawable(ResourceHelper.getMissionResource(getActivity(),roadCard2.getId()));
                binding.imageView2.setBackgroundColor(Color.TRANSPARENT);
                binding.imageView2.setOnClickListener(null);
                binding.imageView3.setVisibility(View.GONE);
                binding.button6.setVisibility(View.GONE);

                missionCards.add(roadCard1);
                missionCards.add(roadCard2);
            }
            else if (cards.size() == 3) {
                Mission roadCard1 = cards.get(0);
                Mission roadCard2 = cards.get(1);
                Mission roadCard3 = cards.get(2);

                binding.imageView.setImageDrawable(ResourceHelper.getMissionResource(getActivity(),roadCard1.getId()));
                binding.imageView.setBackgroundColor(Color.TRANSPARENT);
                binding.imageView.setOnClickListener(null);
                binding.imageView2.setImageDrawable(ResourceHelper.getMissionResource(getActivity(),roadCard2.getId()));
                binding.imageView2.setBackgroundColor(Color.TRANSPARENT);
                binding.imageView2.setOnClickListener(null);
                binding.imageView3.setImageDrawable(ResourceHelper.getMissionResource(getActivity(),roadCard3.getId()));
                binding.imageView3.setBackgroundColor(Color.TRANSPARENT);
                binding.imageView3.setOnClickListener(null);
                binding.button6.setVisibility(View.GONE);

                missionCards.add(roadCard1);
                missionCards.add(roadCard2);
                missionCards.add(roadCard3);
            }
            binding.button9.setVisibility(View.VISIBLE);
            binding.button9.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            });
        }

    }

    public ArrayList<Mission> getMissionCards() {
        return missionCards;
    }
}



