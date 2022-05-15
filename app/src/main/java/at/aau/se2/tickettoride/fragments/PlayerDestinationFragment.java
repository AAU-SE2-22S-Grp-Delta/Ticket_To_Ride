package at.aau.se2.tickettoride.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.databinding.FragmentPlayerDestinationBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class PlayerDestinationFragment extends DialogFragment {
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

        initComponents();

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayDestinationCards();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Set dialog to full screen size
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    private void initComponents() {
        binding.button.setOnClickListener(view -> dismiss());

        getParentFragmentManager().setFragmentResultListener("refresh",this,((requestKey, result) -> displayDestinationCards()));
    }

    public void displayDestinationCards()
    {
        List<Integer> heldDestinationCards = gameModel.getPlayerDestinationCards();
        binding.linearLayoutTrackCards.removeAllViews();

        for (int card : heldDestinationCards)
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
            imageView.setImageDrawable(ResourceHelper.getMissionResource(getContext(), card));

            binding.linearLayoutTrackCards.addView(imageView);
        }
    }
}