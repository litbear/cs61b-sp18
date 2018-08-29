package byog.Core;

import org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RoomTest {

    @Test
    public void testRoomCreate() {
        new Room(new Position(0, 2), 2, 2);
    }

    @Test
    public void textIsRoomOverlap() {
        Room r1 = new Room(new Position(0, 0), 4, 4);
        Room r2 = new Room(new Position(4, 4), 4, 4);
        assertFalse(Room.isRoomOverlap(r1, r2));
    }

    @Test
    public void testIsInWorld() {
        Room room = new Room(new Position(8, 27), 1, 6);
        assertFalse(Room.isInTheWorld(80, 30, room));
    }


    @Test
    public void testGetCenter() {
        Room room = new Room(new Position(8, 27), 1, 1);
        assertEquals(8, room.getCenter().getxIndex());
        assertEquals(27, room.getCenter().getyIndex());
    }

    @Test
    public void testRandomRange() {
        Random random = new Random(100);
        IntStream.range(0, 20).map(e -> RandomUtils.uniform(random, 2)).forEach(System.out::println);
    }

}
