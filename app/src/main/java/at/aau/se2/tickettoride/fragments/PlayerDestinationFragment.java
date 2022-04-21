package at.aau.se2.tickettoride.fragments;

import android.media.Image;
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
import at.aau.se2.tickettoride.databinding.FragmentPlayerDestinationBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class PlayerDestinationFragment extends Fragment {
    private FragmentPlayerDestinationBinding binding;
    private LinearLayout linearLayout;
    private GameModel gameModel;

    public static PlayerDestinationFragment newInstance() {
        return new PlayerDestinationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerDestinationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //List<Integer> heldDestinationCards = gameModel.getPlayerDestinationCards();

        List<Integer> heldDestinationCards = new ArrayList<>();
        heldDestinationCards.add(1);
        heldDestinationCards.add(5);
        heldDestinationCards.add(2);

        linearLayout = view.findViewById(R.id.linearLayoutTrackCards);

        for(int i=0;i<=heldDestinationCards.size()-1;i++)
        {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(20,30,20,30);


            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 350;

            switch (heldDestinationCards.get(i)) {
                case 1:
                    imageView.setImageResource(R.drawable.calgary_saltlakecity);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.kansascity_houston);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.losangeles_chicago);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.newyork_atlanta);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.portland_phoenix);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.seattle_newyork);
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