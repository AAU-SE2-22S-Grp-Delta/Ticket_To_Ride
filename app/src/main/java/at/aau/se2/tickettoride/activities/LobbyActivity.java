package at.aau.se2.tickettoride.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.ActivityLobbyBinding;
import at.aau.se2.tickettoride.fragments.DrawDestinationCardsFragment;
import at.aau.se2.tickettoride.fragments.ListElementGameFragment;

public class LobbyActivity extends AppCompatActivity {
    private ActivityLobbyBinding  binding;
    LinkedList<View> listElements = new LinkedList<>();
    LinkedList<String> testVals = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        binding = ActivityLobbyBinding.inflate(getLayoutInflater());

        Button btn_connect = binding.btnConnect;
        Button btn_newGame = binding.btnNewGame;
        Button btn_refresh = binding.btnRefresh;


//        ListView listView = binding.gamesList;
//        for (int i = 0; i < 5; i++) {
//            TextView textView = new TextView(this);
//            textView.setText("test" + i);
//            listView.addView(textView);
//        }


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, , R.id.game, testVals);
//        binding.gamesList.setAdapter(adapter);
//        binding.gamesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);



        btn_newGame.setOnClickListener(view -> {
//            ListElementGameFragment listElement = new ListElementGameFragment();
//            Intent intent = new Intent(this, ListElementGameFragment.class);
//            listElement.startActivity(intent);
//            View newEntry = View.inflate(getApplicationContext(), R.layout.fragment_list_element_game, null);
//            this.listElements.add(newEntry);
//
//            View[] array = new View[listElements.size()];
//            int counter = 0;
//            for (View element : listElements)  array[counter++] = element;

//            testVals.add("test");
//            adapter.setNotifyOnChange(true);
//            binding.gamesList.setAdapter(adapter);
//            adapter.notifyDataSetChanged();

//            binding.gamesList.addView(new Button(this));



//            binding.gamesList.addView(newEntry);

//            TextView testtext = new TextView(binding.gamesList.getContext());
//            testtext.setText("test");
//            binding.gamesList.addView(testtext);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}