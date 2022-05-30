package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import at.aau.se2.tickettoride.R;

public class HelpDialogFragment extends DialogFragment {

    private AlertDialog dialog; // class variable
    private Button buttonClose;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_help, null);
        builder.setView(dialogLayout);
        builder.setTitle("Game Instructions");
        builder.setMessage("Preparation:\nEach player gets 45 wagons of his color and 4 traincards. On the table there are 5 open traincards and a concealed stack. In the next step, each player gets 3 destination cards, of which at least 2 must be selected.\n" +
                "\nGoal:\nThe player with the highest number of points wins.\nYou get points:\n" +
                "○ From railroad lines (1-6 wagons per railroad line (1-15 points))\n" +
                "○ From destination cards (railroad line from destination A to destination B) (+ or -)\n" +
                "○ From bonus card (10 points for the longest railroad line)\n"+
                "\nRound:\nOne of the three possibilities must be selected: \n"+
                "○ Build exactly one railroad line\n"+
                "○ Draw new destination card(s) (3, from which you must choose at least one)\n"+
                "○ Draw 2 traincards:\n" +
                "   • 2 concealed\n" +
                "   • 2 open\n" +
                "   • 1 open + 1 concealed\n"+
                "\nEnd:\nWhen a player only has one or two wagons left, each player has one more turn.");
        builder.setNegativeButton("Close",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HelpDialogFragment.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

}
