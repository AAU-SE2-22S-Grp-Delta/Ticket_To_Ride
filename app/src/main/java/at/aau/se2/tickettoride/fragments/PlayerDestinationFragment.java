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

import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentPlayerDestinationBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class PlayerDestinationFragment extends Fragment {
    private FragmentPlayerDestinationBinding binding;
    private GameModel gameModel;

    public static PlayerDestinationFragment newInstance() {
        return new PlayerDestinationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameModel = GameModel.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerDestinationBinding.inflate(inflater, container, false);
        getParentFragmentManager().setFragmentResultListener("refresh",this,((requestKey, result) -> displayDestinationCards()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayDestinationCards();
    }

    public void displayDestinationCards()
    {
        List<Integer> heldDestinationCards = gameModel.getPlayerDestinationCards();

        /* für Testzwecke
        List<Integer> heldDestinationCards = new ArrayList<>();
        heldDestinationCards.add(1);
        heldDestinationCards.add(5);
        heldDestinationCards.add(2);*/

        binding.linearLayoutTrackCards.removeAllViews();

        for(int i=0;i<=heldDestinationCards.size()-1;i++)
        {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(5,10,5,10);

            ImageView imageView = new ImageView(binding.linearLayoutTrackCards.getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.getLayoutParams().height = 160;
            imageView.getLayoutParams().width = 250;

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
            binding.linearLayoutTrackCards.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}