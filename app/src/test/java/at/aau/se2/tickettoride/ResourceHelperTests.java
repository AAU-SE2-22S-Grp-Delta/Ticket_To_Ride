package at.aau.se2.tickettoride;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import at.aau.se2.tickettoride.helpers.ResourceHelper;

public class ResourceHelperTests
{
    
    @Test
    public void testGetResource()
    {
        assertEquals(R.drawable.ic_train_purpur, ResourceHelper.getTrainResource(1));
        assertEquals(R.drawable.ic_train_white, ResourceHelper.getTrainResource(2));
        assertEquals(R.drawable.ic_train_blue, ResourceHelper.getTrainResource(3));
        assertEquals(R.drawable.ic_train_yellow, ResourceHelper.getTrainResource(4));
        assertEquals(R.drawable.ic_train_orange, ResourceHelper.getTrainResource(5));
        assertEquals(R.drawable.ic_train_black, ResourceHelper.getTrainResource(6));
        assertEquals(R.drawable.ic_train_red, ResourceHelper.getTrainResource(7));
        assertEquals(R.drawable.ic_train_green, ResourceHelper.getTrainResource(8));
        assertEquals(R.drawable.ic_train, ResourceHelper.getTrainResource(9));
        assertThrows(IllegalStateException.class, ()-> ResourceHelper.getTrainResource(10));
    }
}
