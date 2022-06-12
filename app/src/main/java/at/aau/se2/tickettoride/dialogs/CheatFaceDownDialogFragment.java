package at.aau.se2.tickettoride.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Random;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.Mission;
import at.aau.se2.tickettoride.models.GameModel;
import at.aau.se2.tickettoride.models.Missions;

public class CheatFaceDownDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    public Dialog dialog;
    Button action1;
    Button action2;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_face_down,null);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);

        action1 = view.findViewById(R.id.buttonAction1);
        action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int rand = random.nextInt(5);
                gameModel.drawOpenTrainCard(rand);
                Bundle result = new Bundle();
                getParentFragmentManager().setFragmentResult("RefreshDeskOpenTrain", result);
                getParentFragmentManager().setFragmentResult("RefreshPlayerTrain", result);
                dialog.dismiss();
            }
        });


        action2 = view.findViewById(R.id.buttonAction2);
        action2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Mission> list = Missions.getMissions();
                ArrayList<Integer> newMissions = new ArrayList<>();
                if (gameModel.getPlayerDestinationCards().size() == 2) {
                    int id;
                    for (int i = 0; i < 2; i++) {
                        Random random = new Random();
                        int rand = random.nextInt(30-1)+1;
                        id = list.get(rand).getId();
                        newMissions.add(id);
                    }
                    gameModel.addDiscardedMissionCards(gameModel.getPlayerDestinationCards());
                    gameModel.setPlayerDestinationCards(newMissions);
                    dialog.dismiss();
                }
                if (gameModel.getPlayerDestinationCards().size() == 3) {
                    int id;
                    for (int i = 0; i < 3; i++) {
                        Random random = new Random();
                        int rand = random.nextInt(30-1)+1;
                        id = list.get(rand).getId();
                        newMissions.add(id);
                    }
                    gameModel.addDiscardedMissionCards(gameModel.getPlayerDestinationCards());
                    gameModel.setPlayerDestinationCards(newMissions);
                    dialog.dismiss();
                }
            }
        });
        dialog.setContentView(view);
        dialog.create();
        return dialog;
    }
}
