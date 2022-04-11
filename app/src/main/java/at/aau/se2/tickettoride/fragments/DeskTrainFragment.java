package at.aau.se2.tickettoride.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.aau.se2.tickettoride.databinding.FragmentDeskTrainBinding;
//Fragment Stapel und offene Zugkarten
public class DeskTrainFragment extends Fragment {
    private FragmentDeskTrainBinding binding;

    public static DeskTrainFragment newInstance() {
        return new DeskTrainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Instead of findViewById
        binding = FragmentDeskTrainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}