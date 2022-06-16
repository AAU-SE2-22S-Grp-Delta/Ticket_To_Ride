package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import android.content.Context;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import at.aau.se2.tickettoride.datastructures.TrainCard;
import at.aau.se2.tickettoride.helpers.ResourceHelper;

class ResourceHelperTests
{
    static Context mockedContext = Mockito.mock(Context.class);

    @Test
    void testGetResource()
    {
        assertEquals(R.drawable.ic_train_pink, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.PINK)));
        assertEquals(R.drawable.ic_train_blue, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.BLUE)));
        assertEquals(R.drawable.ic_train_green, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.GREEN)));
        assertEquals(R.drawable.ic_train_yellow, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.YELLOW)));
        assertEquals(R.drawable.ic_train_orange, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.ORANGE)));
        assertEquals(R.drawable.ic_train_black, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.BLACK)));
        assertEquals(R.drawable.ic_train_red, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.RED)));
        assertEquals(R.drawable.ic_train_white, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.WHITE)));
        assertEquals(R.drawable.ic_train, ResourceHelper.getTrainResource(new TrainCard(TrainCard.Type.LOCOMOTIVE)));
    }

    @Test
    void testGetMissionView() {
        assertNull(ResourceHelper.getMissionView(mockedContext, 1));
    }

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<ResourceHelper> constructor = ResourceHelper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
