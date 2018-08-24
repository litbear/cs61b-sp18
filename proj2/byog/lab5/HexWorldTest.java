package byog.lab5;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HexWorldTest {

    @Test
    public void getStartPositionArrayTest() {
        Position p = new Position(25, 25);
        int side = 6;
        Position[] startPositionArray = HexWorld.getStartPositionArray(p, side);
        Arrays.stream(startPositionArray).forEach(System.out::println);
    }

    @Test
    public void testGetRowLengthArray() {
        int[] rowLengthArray = HexWorld.getRowLengthArray(6);
        int[] exceptArray = {6, 8, 10, 12, 14, 16, 16, 14, 12, 10, 8, 6};
        Assert.assertArrayEquals(exceptArray, rowLengthArray);
    }
}
