package DiskrLab9;


import java.io.*;
import java.util.*;

public class A {

    static class Order implements Comparable {
        int time;
        int w;

        public Order(int time, int w) {
            this.time = time;
            this.w = w;
        }


        @Override
        public int compareTo(Object o) {
            Order order = (Order) o;
            return (this.w > order.w) || (this.w == order.w && this.time < order.time) ? -1 : 1;
        }
    }

    ArrayList<Order> orders = new ArrayList<>();
    int n;
    DSU dsu;


    void input() throws IOException {
        FastScanner scanner = new FastScanner("schedule.in");
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            int w = scanner.nextInt();
            orders.add(new Order(Math.min(t, n), w));
        }
    }


    void solve(PrintWriter out) throws IOException {
        input();
        dsu = new DSU(n);
        Collections.sort(orders);
        long res = 0;
        for (Order order : orders) {
            int ind = dsu.ind[dsu.get(order.time)];
            if ( ind > 0) {
                dsu.union(ind , ind-1);
            } else {
                res += order.w;
            }
        }
        out.print(res);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("schedule.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new A().run();

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

    public static class DSU {
        int[] counts;
        int[] mins;
        int[] maxs;
        int[] p;
        int[] rank;
        int[] ind;
        int n;

        DSU(int n) {
            this.n = n;
            p = new int[n+2];
            rank = new int[n + 2];
            counts = new int[n + 2];
            mins = new int[n + 2];
            maxs = new int[n + 2];
            ind = new int[n+2];
            for (int i = 0; i <= n; i++) {
                init(i);
                counts[i] = 1;
                mins[i] = i;
                maxs[i] = i;
                ind[i] = i;
            }
        }

        void init(int el) {
            p[el] = el;
            rank[el] = 0;
        }

        int get(int el) {
            if (p[el] == el) {
                return el;
            }
            return p[el] = get(p[el]);
        }


        void union(int x, int y) {
            x = get(x);
            y = get(y);

            if (x != y) {
                ind[x] = ind[y];
                counts[x] = counts[x] + counts[y];
                counts[y] = counts[x];
                if (mins[x] < mins[y]) {
                    mins[y] = mins[x];
                } else {
                    mins[x] = mins[y];
                }
                if (maxs[x] > maxs[y]) {
                    maxs[y] = maxs[x];
                } else {
                    maxs[x] = maxs[y];
                }

                if (rank[x] < rank[y]) {
                    int t = x;
                    x = y;
                    y = t;
                }

                p[y] = x;
                rank[x] = Math.max(rank[x], rank[y] + 1);
            }
        }


    }
}