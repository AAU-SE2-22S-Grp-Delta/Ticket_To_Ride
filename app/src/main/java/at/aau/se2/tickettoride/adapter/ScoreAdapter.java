package at.aau.se2.tickettoride.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.datastructures.Player;

public class ScoreAdapter extends ArrayAdapter<Player> {
    List<Player> players;
    Activity context;

    public ScoreAdapter(Activity context, List<Player> players){
        super(context, R.layout.list_score, players);
        this.players = players;
        this.context = context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_score, null, true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewWinner);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewNamePoints);

        if (i > 0) imageView.setVisibility(View.INVISIBLE);
        Player player = players.get(i);
        textView.setText((i+1)+". "+player.getName()+": "+player.getPoints());
        return rowView;
    }
}
