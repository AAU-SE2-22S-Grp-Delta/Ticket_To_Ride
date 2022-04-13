package at.aau.se2.tickettoride.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentPlayerTrainBinding;

public class PlayerTrainFragment extends Fragment {
    private FragmentPlayerTrainBinding binding;

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

        /*for(int i=1;i<5;i++)
        {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
            imageView.setMaxHeight(20);
            imageView.setMaxWidth(20);

            layout.addView(imageView);
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}