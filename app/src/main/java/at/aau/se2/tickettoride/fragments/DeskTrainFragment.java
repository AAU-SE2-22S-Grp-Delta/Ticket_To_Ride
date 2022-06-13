package at.aau.se2.tickettoride.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.FragmentDeskTrainBinding;
import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;

public class DeskTrainFragment extends Fragment {
    private FragmentDeskTrainBinding binding;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String card = bundle.getString("card_drawn");
            if (card != null) {
                DialogFragment trainDialog = TrainDialogFragment.newInstance(card);
                trainDialog.show(getParentFragmentManager(), "trainDialog");
            }
        }
    };

    public static DeskTrainFragment newInstance() {
        return new DeskTrainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeskTrainBinding.inflate(inflater, container, false);

        binding.imageViewTrain.setOnClickListener(view -> {
            ClientConnection client = ClientConnection.getInstance();
            client.sendCommand("cardStack");
        });

        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).registerReceiver(receiver, new IntentFilter("server"));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).unregisterReceiver(receiver);
        binding = null;
    }
}