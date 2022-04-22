package at.aau.se2.tickettoride;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.aau.se2.tickettoride.activities.DeskDestinationActivity;

@RunWith(JUnit4.class)
public class DeskDestinationActivityTest
{
    @Rule
    public ActivityScenarioRule<DeskDestinationActivity> mapActivityRule = new ActivityScenarioRule<>(DeskDestinationActivity.class);
}
