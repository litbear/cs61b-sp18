package byog.Core;

import org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class RoomTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRoomCreate() {
        new Room(new Position(0, 2), 2, 2);
    }

    @Test
    public void textIsRoomOverlap() {
        Room r1 = new Room(new Position(0, 0), 4, 4);
        Room r2 = new Room(new Position(4, 4), 4, 4);
        assertFalse(Room.isRoomOverlap(r1, r2));
    }

}
