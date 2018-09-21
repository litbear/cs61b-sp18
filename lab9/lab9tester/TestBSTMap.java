package lab9tester;

import static org.junit.Assert.*;

import org.junit.Test;
import lab9.BSTMap;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    private static Random random = new Random(147258L);

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    @Test
    public void keySetTest() {
        BSTMap<String, String> b = new BSTMap<>();
        Set<String> stringSet = new HashSet<>();
        List<Integer> integerList = IntStream.range(0, 30).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList, random);
        integerList.forEach(e -> {
            b.put(String.format("hi%02d", e), String.format("hello%02d", e));
            stringSet.add(String.format("hi%02d", e));
        });
        assertTrue(stringSet.containsAll(b.keySet()));
        assertTrue(b.keySet().containsAll(stringSet));
    }

    @Test
    public void removeTest() {
        BSTMap<String, String> b = new BSTMap<>();
        List<Integer> integerList = IntStream.range(0, 30).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList, random);
        integerList.forEach(e -> b.put(String.format("hi%02d", e), String.format("hello%02d", e)));
        assertEquals(30, b.size());
        b.remove("hi11");
        assertEquals(29, b.size());
        b.remove("hi15", "hello15");
        assertEquals(28, b.size());
        String removed = b.remove("hi9", "Hello1118");
        assertNull(removed);
        assertEquals(28, b.size());
    }

    @Test
    public void printTest(){
        BSTMap<String, String> b = new BSTMap<>();
        List<Integer> integerList = IntStream.range(0, 30).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList, random);
        integerList.forEach(e -> b.put(String.format("hi%02d", e), String.format("hello%02d", e)));
        b.print();
    }

    @Test
    public void iteratorTest(){
        BSTMap<String, String> b = new BSTMap<>();
        List<Integer> integerList = IntStream.range(0, 30).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList, random);
        integerList.forEach(e -> b.put(String.format("hi%02d", e), String.format("hello%02d", e)));
        for (String s: b) {
            System.out.println(s);
        }
    }

    @Test
    public void streamTest() {
        Stream<String> stringStream = Stream.of("a,b", "cd,", "e,f");
        stringStream
                .map(e -> Stream.of(e.split(",")))
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .forEach(System.out::println);
    }

    @Test
    public void emptyStreamTest() {
        Stream<String> stringStream = Stream.of();
        assertNull(stringStream.findFirst().orElse(null));
        Stream<String> stringStream2 = Stream.of("a");
        assertNotNull(stringStream2.findFirst().orElse(null));
    }

    @Test
    public void byteSignTest() {
        // sign -2 unsign 254
        byte a = (byte) 0xfe;  // -2
        int e = a;
        System.out.println("---->" + e); // -2
        int b = a & 0xff; // 254
        System.out.println(b);

        int c = 0xfffffffe; // int -2
        System.out.println(c); // -2
        System.out.println(c % 13); // -2
        int d = c & 0x7fffffff; // int 2147483646
        System.out.println(d); // 2147483646
        System.out.println(d % 13); // 9
        System.out.println(Math.floorMod(d, 13)); // 9

    }

    @Test
    public void shiftTest() {
        int a = -8;
        System.out.println(a >> 2);
        System.out.println(a >>> 2);
        System.out.println(((-8) >> 2) + (2 << ~2));
    }

    @Test
    public void BitwiseComplementOperatorTest() {
//        int a = -2;
//        int b = (~a) + 1;
//        System.out.println(b);
        System.out.println(String.format("%32s", Integer.toBinaryString(16)).replace(' ', '0'));
        System.out.println(String.format("%32s", Integer.toBinaryString(16 >> 2)).replace(' ', '0'));
        System.out.println(String.format("%32s", Integer.toBinaryString(16 >>> 2)).replace(' ', '0'));
        System.out.println("--------------");
        System.out.println(String.format("%32s", Integer.toBinaryString(-16)).replace(' ', '0'));
        System.out.println(-16 >> 2);
        System.out.println(String.format("%32s", Integer.toBinaryString(-16 >> 2)).replace(' ', '0'));
        System.out.println(-16 >>> 2);
        System.out.println(String.format("%32s", Integer.toBinaryString(-16 >>> 2)).replace(' ', '0'));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
