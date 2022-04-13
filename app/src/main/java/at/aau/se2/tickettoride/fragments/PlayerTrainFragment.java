package at.aau.se2.tickettoride.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentPlayerTrainBinding;

public class PlayerTrainFragment extends Fragment {
    private FragmentPlayerTrainBinding binding;
    private LinearLayout linearLayout;

    public static PlayerTrainFragment newInstance() {
        return new PlayerTrainFragment();
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

        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutTrainCards);

        for(int i=1;i<=10;i++)
        {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10,20,10,20);

            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.getLayoutParams().height = 600;
            imageView.getLayoutParams().width = 300;
            imageView.setBackgroundColor(Color.RED);

            linearLayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}