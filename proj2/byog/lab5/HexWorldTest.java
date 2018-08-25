package byog.lab5;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

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

    @Test
    public void testTesselationVerticalArray(){
        int n = 6;
        // -5, -4, -3, -2, -1,  0,  1,  2, 3, 4, 5
        // 6,   7,  8,  9,  10, 11, 10, 9, 8, 7, 6
        int[] ints = IntStream
                .range(1 - n, n)
                .map(e -> (e < 0) ? (((2 * n) - 1) + e) : ((2 * n) - 1 - e))
                .toArray();
        assertArrayEquals(new int[]{6, 7, 8, 9, 10, 11, 10, 9, 8, 7, 6}, ints);
    }

    @Test
    public void testTesselationStartPointArray(){
        int n = 6;
        int[] ints = IntStream
                .range(1 - n, n)
                .toArray();
        assertArrayEquals(new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}, ints);
    }
}
