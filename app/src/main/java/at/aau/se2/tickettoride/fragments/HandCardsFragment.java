package at.aau.se2.tickettoride.fragments;

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
import at.aau.se2.tickettoride.databinding.FragmentHandCardsBinding;

public class HandCardsFragment extends Fragment {
    private FragmentHandCardsBinding binding;
    private LinearLayout linearLayout;

    public static HandCardsFragment newInstance() {
        return new HandCardsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHandCardsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //List<Integer> heldTrainCards = gameModel.getPlayerTrainCards();

        // für Testzwecke
        List<Integer> heldTrainCards = new ArrayList<>();
        heldTrainCards.add(1);
        heldTrainCards.add(3);
        heldTrainCards.add(4);

        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutTrainCards);

        for(int i=0;i<=heldTrainCards.size()-1;i++)
        {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(20,30,20,30);

            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setLayoutParams(layoutParams);
//            imageView.getLayoutParams().height = 600;
//            imageView.getLayoutParams().width = 350;
            switch (heldTrainCards.get(i)) {
                case 0:
                    imageView.setImageResource(R.drawable.ic_train_black);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.ic_train_blue);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.ic_train_green);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.ic_train_orange);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.ic_train_purpur);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.ic_train_red);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.ic_train_white);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.ic_train_yellow);
                    break;
            }
            linearLayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}