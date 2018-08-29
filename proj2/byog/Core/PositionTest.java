package byog.Core;

import org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

public class PositionTest {

    @Test
    public void testGetAllNeighbors() {
        Position p = new Position(0, 0);
        Position.getAllNeighbors(p, 40, 50).forEach(System.out::println);
        System.out.println("----------------------");
        Position p2 = new Position(39, 49);
        Position.getAllNeighbors(p2, 40, 50).forEach(System.out::println);
        System.out.println("----------------------");
        Position p3 = new Position(30, 49);
        Position.getAllNeighbors(p3, 40, 50).forEach(System.out::println);
    }

    @Test(expected = NumberFormatException.class)
    public void testLongOf(){
        Long l = Long.valueOf("adsasdf");
        System.out.println(l);
    }

    @Test
    public void testIndexOf() {
        String input = "N12345678S";
        int start = input.toLowerCase().indexOf('n');
        int end = input.toLowerCase().indexOf('s');
        System.out.println(start + "---" + end);
        System.out.println(input.substring(start + 1, end));
    }
}
