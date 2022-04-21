package at.aau.se2.tickettoride.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.aau.se2.tickettoride.databinding.FragmentDeskOpenTrainBinding;
import at.aau.se2.tickettoride.enums.TrainCards;
import at.aau.se2.tickettoride.models.GameModel;

public class DeskOpenTrainFragment extends Fragment {
    private FragmentDeskOpenTrainBinding binding;
    private GameModel gameModel;

    public static DeskOpenTrainFragment newInstance() {
        return new DeskOpenTrainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeskOpenTrainBinding.inflate(inflater, container, false);
        gameModel = GameModel.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        displayData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displayData() {
        Integer[] cards = gameModel.getDeskOpenTrainCards();
        for (int i = 0; i < cards.length; i++) {
            Integer card = cards[i];
            if (card != null) {
                switch (i) {
                    case 0:
                        binding.card1.setText(TrainCards.valueOf(card).toString());
                        break;
                    case 1:
                        binding.card2.setText(TrainCards.valueOf(card).toString());
                        break;
                    case 2:
                        binding.card3.setText(TrainCards.valueOf(card).toString());
                        break;
                    case 3:
                        binding.card4.setText(TrainCards.valueOf(card).toString());
                        break;
                    case 4:
                        binding.card5.setText(TrainCards.valueOf(card).toString());
                        break;
                }
            }
        }
    }
}