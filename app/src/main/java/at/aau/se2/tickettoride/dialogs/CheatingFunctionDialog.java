package at.aau.se2.tickettoride.dialogs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import at.aau.se2.tickettoride.models.GameModel;

public class CheatingFunctionDialog extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
