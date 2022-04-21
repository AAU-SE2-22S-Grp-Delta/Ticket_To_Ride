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

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.FragmentPlayerMissionBinding;

public class PlayerMissionFragment extends Fragment {
    private FragmentPlayerMissionBinding binding;
    private LinearLayout linearLayout;

    public static PlayerMissionFragment newInstance() {
        return new PlayerMissionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerMissionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);

        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutTrackCards);

        for(int i=0;i<=test.size()-1;i++)
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
            imageView.setBackgroundColor(test.get(i));

            linearLayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}