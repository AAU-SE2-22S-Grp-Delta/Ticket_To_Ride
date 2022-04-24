package at.aau.se2.tickettoride.fragments;

import static at.aau.se2.tickettoride.ResourceHelper.getTrainResource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentPlayerTrainBinding;
import at.aau.se2.tickettoride.enums.TrainCards;
import at.aau.se2.tickettoride.models.GameModel;

public class PlayerTrainFragment extends Fragment {
    private FragmentPlayerTrainBinding binding;
    private LinearLayout linearLayout;
    private GameModel gameModel;

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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Integer> heldTrainCards = gameModel.getPlayerTrainCards();

        /*für Testzwecke
        List<Integer> heldTrainCards = new ArrayList<>();
        heldTrainCards.add(9);
        heldTrainCards.add(1);
        heldTrainCards.add(5);
        heldTrainCards.add(5);
        heldTrainCards.add(7);*/

        linearLayout = view.findViewById(R.id.linearLayoutTrainCards);

        for(Integer card : heldTrainCards)
        {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(5,10,5,10);

            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setLayoutParams(layoutParams);
//            imageView.getLayoutParams().height = 160;
//            imageView.getLayoutParams().width = 160;
            imageView.setImageResource(getTrainResource(card));

            linearLayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}