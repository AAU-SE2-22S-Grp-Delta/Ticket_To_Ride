package at.aau.se2.tickettoride;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.widget.Button;
import android.widget.ScrollView;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.aau.se2.tickettoride.activities.MapActivity;
import at.aau.se2.tickettoride.eventListeners.MapOnTouchListener;

@RunWith(AndroidJUnit4.class)
public class MapActivityTest
{
    @Rule
    public ActivityScenarioRule<MapActivity> mapActivityRule = new ActivityScenarioRule<>(MapActivity.class);

    @Test
    public void mapActivityTest()
    {
        onView(withId(R.id.atlanta)).perform(click());
    }
}
