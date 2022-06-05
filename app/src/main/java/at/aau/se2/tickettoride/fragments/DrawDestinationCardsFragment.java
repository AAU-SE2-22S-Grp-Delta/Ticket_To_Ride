package at.aau.se2.tickettoride.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import at.aau.se2.tickettoride.activities.GameActivity;
import at.aau.se2.tickettoride.databinding.FragmentDrawDestinationCardsBinding;
import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class DrawDestinationCardsFragment extends Fragment implements View.OnClickListener {
    private FragmentDrawDestinationCardsBinding binding;
    private GameModel gameModel;

    public DrawDestinationCardsFragment() {
    }

    public DrawDestinationCardsFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    public static DrawDestinationCardsFragment newInstance() {
        return new DrawDestinationCardsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameModel = GameModel.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrawDestinationCardsBinding.inflate(inflater, container, false);

        binding.button9.setVisibility(View.INVISIBLE);

        List<Integer> cards = gameModel.getChooseMissionCards();
        binding.imageView.addView(ResourceHelper.getMissionView(requireContext(), cards.get(0)));
        binding.imageView.setTag(cards.get(0));
        binding.imageView2.addView(ResourceHelper.getMissionView(requireContext(), cards.get(1)));
        binding.imageView2.setTag(cards.get(1));
        binding.imageView3.addView(ResourceHelper.getMissionView(requireContext(), cards.get(2)));
        binding.imageView3.setTag(cards.get(2));

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
            if (binding.imageView.getBackground() != null) {
                binding.imageView.setBackground(null);
            } else {
                binding.imageView.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.imageView2.getId()) {
            if (binding.imageView2.getBackground() != null) {
                binding.imageView2.setBackground(null);
            } else {
                binding.imageView2.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.imageView3.getId()) {
            if (binding.imageView3.getBackground() != null) {
                binding.imageView3.setBackground(null);
            } else {
                binding.imageView3.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == binding.button6.getId()) {
            ArrayList<Integer> selectedMissions = new ArrayList<>();
            ArrayList<Integer> discardedMissions = new ArrayList<>();

            int card = (int) binding.imageView.getTag();
            if (binding.imageView.getBackground() != null) {
                selectedMissions.add(card);
            } else {
                discardedMissions.add(card);
            }

            card = (int) binding.imageView2.getTag();
            if (binding.imageView2.getBackground() != null) {
                selectedMissions.add(card);
            } else {
                discardedMissions.add(card);
            }

            card = (int) binding.imageView3.getTag();
            if (binding.imageView3.getBackground() != null) {
                selectedMissions.add(card);
            } else {
                discardedMissions.add(card);
            }

            if (selectedMissions.size() < 2) {
                return;
            }

            gameModel.setPlayerDestinationCards(selectedMissions);
            if (discardedMissions.size() > 0) {
                gameModel.addDiscardedMissionCards(discardedMissions);
            }

            binding.imageView.setBackground(null);
            binding.imageView.setOnClickListener(null);
            binding.imageView2.setBackground(null);
            binding.imageView2.setOnClickListener(null);
            binding.imageView3.setBackground(null);
            binding.imageView3.setOnClickListener(null);

            if (selectedMissions.size() == 2) {
                card = selectedMissions.get(0);
                binding.imageView.removeAllViews();
                binding.imageView.addView(ResourceHelper.getMissionView(requireContext(), card));
                binding.imageView.setTag(card);

                card = selectedMissions.get(1);
                binding.imageView2.removeAllViews();
                binding.imageView2.addView(ResourceHelper.getMissionView(requireContext(), card));
                binding.imageView2.setTag(card);

                binding.imageView3.removeAllViews();
            }

            binding.button6.setVisibility(View.GONE);

            binding.button9.setVisibility(View.VISIBLE);
            binding.button9.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            });
        }
    }
}
