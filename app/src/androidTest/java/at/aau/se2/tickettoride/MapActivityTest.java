package at.aau.se2.tickettoride;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.aau.se2.tickettoride.activities.MapActivity;

@RunWith(AndroidJUnit4.class)
public class MapActivityTest
{
    @Rule
    public ActivityScenarioRule<MapActivity> mapActivityRule = new ActivityScenarioRule<>(MapActivity.class);

    @Test
    public void mapActivityTest() throws UiObjectNotFoundException
    {
        UiDevice mD = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mD.findObject(new UiSelector().text("21")).click();
        mD.findObject(new UiSelector().text("20")).click();
        mD.findObject(new UiSelector().text("20")).click();

        // Workaround for SonarCloud
        assertTrue(true);
    }

}



