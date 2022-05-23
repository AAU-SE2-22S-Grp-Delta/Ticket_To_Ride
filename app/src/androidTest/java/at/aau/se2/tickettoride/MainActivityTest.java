package at.aau.se2.tickettoride;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.aau.se2.tickettoride.activities.DeskDestinationActivity;
import at.aau.se2.tickettoride.activities.MainActivity;

@RunWith(JUnit4.class)

public class MainActivityTest
{
    @Rule
    public ActivityScenarioRule<MainActivity> mapActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavButtons()
    {
        onView(withId(R.id.button)).perform(click());
        Espresso.pressBack();
    }
}
