package DiskrLab1;

import javafx.scene.chart.AxisBuilder;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class A {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graphRev = new ArrayList<>();
    static ArrayList<Boolean> used = new ArrayList<>();
    static ArrayList<Integer> mas = new ArrayList<>();
    static ArrayList<Integer> comp = new ArrayList<>();

    static void dfs1(int v) {
        used.set(v, true);
        for (int i = 0; i < graph.get(v).size(); i++) {
            int next = graph.get(v).get(i);
            if (!used.get(next))
                dfs1(next);
        }
        mas.add(v);
    }

    static void dfs2(int v, int col) {
        comp.set(v, col);
        for (int i = 0; i < graphRev.get(v).size(); i++) {
            int next = graphRev.get(v).get(i);
            if (comp.get(next) == -1) {
                dfs2(next, col);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);

        int n = reader.nextInt();
        int m = reader.nextInt();
        for (int i = 0; i < 2 * n; i++) {
            graph.add(new ArrayList<Integer>());
            graphRev.add(new ArrayList<Integer>());
        }

        int a;
        int b;
        for (int i = 0; i < m; i++) {
            int a0 = reader.nextInt();
            int b0 = reader.nextInt();
            if (a0 >= 0) {
                a = 2 * a0 - 2;
            } else {
                a = 2 * (-a0) - 1;
            }
            if (b0 >= 0) {
                b = 2 * b0 - 2;
            } else {
                b = 2 * (-b0) - 1;
            }
           // if (graph.get(a ^ 1).indexOf(b) == -1) {
                graph.get(a ^ 1).add(b);
            //}
            //if (graph.get(b ^ 1).indexOf(a) == -1) {
                graph.get(b ^ 1).add(a);
            //}
            //if (graph.get(b).indexOf(a ^ 1) == -1) {
                graphRev.get(b).add(a ^ 1);
            //}
            //if (graph.get(a).indexOf(b ^ 1) == -1) {
                graphRev.get(a).add(b ^ 1);
            //}

        }


        for (int i = 0; i < 2 * n; i++) {
            used.add(false);
        }
        for (int i = 0; i < 2 * n; i++)
            if (!used.get(i))
                dfs1(i);

        for (int i = 0; i < 2 * n; i++) {
            comp.add(-1);
        }
        for (int i = 0, j = 0; i < 2 * n; i++) {
            int point = mas.get(2 * n - i - 1);
            if (comp.get(point) == -1)
                dfs2(point, j++);
        }

        for (int i = 0; i < 2 * n; i+=2)
            if (comp.get(i).compareTo(comp.get(i ^ 1)) == 0) {
                System.out.print("YES");
                return;
            }
        System.out.print("NO");

    }

}
