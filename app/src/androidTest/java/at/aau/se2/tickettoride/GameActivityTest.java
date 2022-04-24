package at.aau.se2.tickettoride;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.aau.se2.tickettoride.activities.DeskDestinationActivity;
import at.aau.se2.tickettoride.activities.GameActivity;

@RunWith(JUnit4.class)

public class GameActivityTest
{
    @Rule
    public ActivityScenarioRule<GameActivity> mapActivityRule = new ActivityScenarioRule<>(GameActivity.class);

    @Test
    public void defaultTest()
    {

    }
}
