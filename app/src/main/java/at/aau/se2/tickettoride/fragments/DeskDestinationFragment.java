package at.aau.se2.tickettoride.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import at.aau.se2.tickettoride.databinding.FragmentDeskDestinationBinding;
import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;

//Fragemnt Stapel Zielkarten
public class DeskDestinationFragment extends Fragment {
    private FragmentDeskDestinationBinding binding;

    public static DeskDestinationFragment newInstance() {
        return new DeskDestinationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Instead of findViewById
        binding = FragmentDeskDestinationBinding.inflate(inflater, container, false);

        binding.imageViewDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog Destination Cards
                DialogFragment destinationDialog = new DestinationDialogFragment();
                destinationDialog.show(getActivity().getSupportFragmentManager(), "destinationDialog");
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}