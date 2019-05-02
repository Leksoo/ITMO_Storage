package AlgoLab7;

import java.io.*;
import java.util.*;

public class I {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distance(Point b) {
            return Math.sqrt((x - b.x) * (x - b.x) + (y - b.y) * (y - b.y));
        }
    }

    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] revGraph;
    ArrayList<Point> points = new ArrayList<>();
    int n;
    int m;
    boolean[] visited;
    int[] colors;
    double[] dist;
    double res = 0;
    ArrayList<Integer> sorted = new ArrayList<>();

    boolean hasCycle = false;


    void input() throws IOException {
        FastScanner scanner = new FastScanner();
        n = scanner.nextInt();
        graph = new ArrayList[n];
        revGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));

        }
        visited = new boolean[n];
        colors = new int[n];
        dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
    }


    void solve(PrintWriter out) throws IOException {
        input();
        while (true) {
            int minNum = -1;
            double minDist = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    minNum = i;
                }
            }
            if (minNum == -1) {
                break;
            }
            visited[minNum] = true;
            res += minDist;
            for (int i = 0; i < n; i++) {
                if (points.get(minNum).distance(points.get(i)) < dist[i]) {
                    dist[i] = points.get(minNum).distance(points.get(i));
                }
            }
        }
        System.out.print(res);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("topsort.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new I().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                if (line.equals("")) {
                    return "";
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}