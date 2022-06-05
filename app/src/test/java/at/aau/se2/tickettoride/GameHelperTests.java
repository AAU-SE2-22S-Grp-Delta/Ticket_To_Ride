package at.aau.se2.tickettoride;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;
import at.aau.se2.tickettoride.helpers.LocalGameHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class GameHelperTests
{
    GameModel gm = GameModel.getInstance();


    @Test
    void testGenerate()
    {
        LocalGameHelper.generateTestGame(gm);
        assertEquals(101, gm.getDeskClosedTrainCards().size());
        assertEquals(27, gm.getDeskDestinationCards().size());
        assertEquals(4, gm.getPlayerTrainCards().size());
        assertEquals(5, gm.getDeskOpenTrainCards().length);
        assertEquals(3, gm.getPlayerDestinationCards().size());
    }
}
