/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b);
        // 自动装箱 1字节的那个坑
        // return a == b;
    }
}
