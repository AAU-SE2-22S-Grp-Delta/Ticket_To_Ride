package at.aau.se2.tickettoride.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import at.aau.se2.tickettoride.databinding.FragmentDeskTrainBinding;
import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;

//Fragment Stapel und offene Zugkarten
public class DeckCardsFragment extends Fragment {
    private FragmentDeskTrainBinding binding;

    public static DeckCardsFragment newInstance() {
        return new DeckCardsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Instead of findViewById
        binding = FragmentDeskTrainBinding.inflate(inflater, container, false);

        //TODO DELETE COMMENT
        //Wagenkarten nehmen
        // Der Spieler darf zwei Karten nehmen. Er kann eine der offen ausliegenden Karten oder die oberste vom verdeckten Stapel ziehen.
        // Wenn er eine offene Karte wählt, wird diese sofort durch die oberste vom Stapel ersetzt.
        // Dann nimmt der Spieler seine zweite Karte – entweder eine der offen ausliegenden Karten oder die oberste vom Stapel.
        // (Siehe die speziellen Regeln für Lokomotivkarten im Abschnitt„Wagen- und Lokomotivkarten“).
        binding.imageViewTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment trainDialog = new TrainDialogFragment();
                trainDialog.show(getFragmentManager(), "trainDialog");
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