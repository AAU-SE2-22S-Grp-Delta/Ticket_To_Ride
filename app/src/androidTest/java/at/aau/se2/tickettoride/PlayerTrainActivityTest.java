package at.aau.se2.tickettoride;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.aau.se2.tickettoride.activities.PlayerTrainActivity;

@RunWith(JUnit4.class)
public class PlayerTrainActivityTest
{
    @Rule
    public ActivityScenarioRule<PlayerTrainActivity> mapActivityRule = new ActivityScenarioRule<>(PlayerTrainActivity.class);

    @Test
    public void defaultTest()
    {

    }
}