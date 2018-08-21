import synthesizer.GuitarString;

import java.util.Arrays;
import java.util.function.DoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] guitarStrings;
    static {
        guitarStrings = IntStream
                .range(0, keyboard.length())
                .mapToDouble(Double::valueOf)
                .map(e -> 440 * Math.pow(2, (e - 24) / 12))
                .mapToObj(GuitarString::new)
                .toArray(GuitarString[]::new);
    }


    public static void main(String[] args) {


        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if(index != -1) {
                    GuitarString string = guitarStrings[index];
                    string.pluck();
                }
            }
            double sample;
            sample = Arrays.stream(guitarStrings).map(GuitarString::sample).reduce(0.0, (a, b) -> a + b);
            StdAudio.play(sample);
            Arrays.stream(guitarStrings).forEach(GuitarString::tic);
        }
    }
}
