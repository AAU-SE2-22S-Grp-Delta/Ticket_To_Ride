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
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.databinding.FragmentPlayerTrainBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class PlayerTrainFragment extends Fragment {
    private FragmentPlayerTrainBinding binding;
    private GameModel gameModel;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle.getString("refresh_player_train", "0").equals("1")) {
                displayTrainCards();
            }
        }
    };

    public static PlayerTrainFragment newInstance() {
        return new PlayerTrainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameModel = GameModel.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerTrainBinding.inflate(inflater, container, false);

        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).registerReceiver(receiver, new IntentFilter("server"));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayTrainCards();
    }

    public void displayTrainCards() {
        binding.linearLayoutTrainCards.removeAllViews();

        List<TrainCard> heldTrainCards = gameModel.getPlayerTrainCards();
        for(TrainCard card : heldTrainCards)
        {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(5,10,5,10);

            ImageView imageView = new ImageView(binding.linearLayoutTrainCards.getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(getTrainResource(card));

            binding.linearLayoutTrainCards.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).unregisterReceiver(receiver);
        binding = null;
    }
}