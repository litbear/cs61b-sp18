package byog.lab5;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HexWorldTest {

    @Test
    public void offsetTest() {
        int side = 3;
        // 0, -1, -2, -2, -1, 0
        int[] xOffsets = IntStream
                .range(0, side << 1)
                .map(e -> e < side? e: (2 * side - 1 - e))
                .map(e -> -e)
                .toArray();
        assertArrayEquals(new int[]{0, -1, -2, -2, -1, 0}, xOffsets);
        int[] rowLengths = IntStream
                .range(0, (side << 1))
                .map(e -> side + 2 * (xOffsets[e] < 0? -xOffsets[e]: xOffsets[e]))
                .toArray();
        int[] exceptArray = new int[]{3, 5 ,7, 7, 5, 3};
        assertArrayEquals(exceptArray, rowLengths);

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
