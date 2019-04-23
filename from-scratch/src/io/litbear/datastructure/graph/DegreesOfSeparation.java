package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * N度人脉
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        String source = args[3];

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        BreadthFirstPaths bfp = new BreadthFirstPaths(G, sg.indexOf(source));

        while (!StdIn.isEmpty()) {
            String target = StdIn.readLine();
            if (sg.contains(target)) {

            } else {
                StdOut.println(StdOut.println();
            }
        }
    }
}
