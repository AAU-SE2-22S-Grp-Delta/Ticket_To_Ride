package at.aau.se2.tickettoride.fragments;

import static at.aau.se2.tickettoride.helpers.ResourceHelper.getTrainResource;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;

import at.aau.se2.tickettoride.datastructures.TrainCard;
import at.aau.se2.tickettoride.databinding.FragmentDeskOpenTrainBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class DeskOpenTrainFragment extends Fragment {
    private FragmentDeskOpenTrainBinding binding;
    private GameModel gameModel;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle.getString("refresh_desk_open_train", "0").equals("1")) {
                displayData();
            }
        }
    };

    public static DeskOpenTrainFragment newInstance() {
        return new DeskOpenTrainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameModel = GameModel.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeskOpenTrainBinding.inflate(inflater, container, false);

        initComponents();

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

        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).unregisterReceiver(receiver);
        binding = null;
    }

    private void initComponents() {
        binding.card1.setOnClickListener(view -> drawCard(0));

        binding.card2.setOnClickListener(view -> drawCard(1));

        binding.card3.setOnClickListener(view -> drawCard(2));

        binding.card4.setOnClickListener(view -> drawCard(3));

        binding.card5.setOnClickListener(view -> drawCard(4));

        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).registerReceiver(receiver, new IntentFilter("server"));
    }

    private void displayData() {
        List<TrainCard> cards = gameModel.getDeskOpenTrainCards();
        for (int i = 0; i < cards.size(); i++) {
            TrainCard card = cards.get(i);
            if (card != null) {
                switch (i) {
                    case 0:
                        binding.card1.setImageResource(getTrainResource(card));
                        break;
                    case 1:
                        binding.card2.setImageResource(getTrainResource(card));
                        break;
                    case 2:
                        binding.card3.setImageResource(getTrainResource(card));
                        break;
                    case 3:
                        binding.card4.setImageResource(getTrainResource(card));
                        break;
                    case 4:
                        binding.card5.setImageResource(getTrainResource(card));
                        break;
                }
            }
        }
    }

    private void drawCard(int i) {
        gameModel.drawOpenTrainCard(i);
    }
}