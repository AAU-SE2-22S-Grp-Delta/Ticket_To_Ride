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
import at.aau.se2.tickettoride.databinding.FragmentDeskDestinationBinding;
import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;

public class DeskDestinationFragment extends Fragment {
    private FragmentDeskDestinationBinding binding;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle.getString("card_mission", "0").equals("1")) {
                DialogFragment destinationDialog = new DestinationDialogFragment();
                destinationDialog.show(getParentFragmentManager(), "destinationDialog");
            }
        }
    };

    public static DeskDestinationFragment newInstance() {
        return new DeskDestinationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeskDestinationBinding.inflate(inflater, container, false);

        binding.imageViewDestination.setOnClickListener(view -> {
            ClientConnection client = ClientConnection.getInstance();
            client.sendCommand("drawMission");
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