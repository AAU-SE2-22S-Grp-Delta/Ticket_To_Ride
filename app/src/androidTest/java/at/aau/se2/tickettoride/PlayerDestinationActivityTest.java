package at.aau.se2.tickettoride;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.aau.se2.tickettoride.activities.DeskDestinationActivity;
import at.aau.se2.tickettoride.activities.PlayerDestinationActivity;

@RunWith(JUnit4.class)
public class PlayerDestinationActivityTest
{
    @Rule
    public ActivityScenarioRule<PlayerDestinationActivity> mapActivityRule = new ActivityScenarioRule<>(PlayerDestinationActivity.class);
}
