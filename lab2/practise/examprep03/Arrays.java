package examprep03;

/**
 * examprep03 第一题 Flatten array
 */
public class Arrays {
    public static int[] flatten(int[][] x) {
        int totalLength = 0;

        for (int i = 0; i < x.length; i += 1) {
            totalLength += x[i].length;
        }

        int[] a = new int[totalLength];
        int aIndex = 0;

        for (int i = 0; i < x.length; i += 1) {
            for (int j = 0; j < x[i].length; j += 1) {
                a[aIndex] = x[i][j];
                aIndex += 1;
            }
        }

        return a;
    }
}
