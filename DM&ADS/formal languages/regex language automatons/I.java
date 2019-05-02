package DiskrLab5;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class I {


    static double a;

    static class DFA {
        FastScanner in;
        char randomAlph;
        int n;
        int m;
        int k;
        ArrayList<HashMap<Character, Integer>> map = new ArrayList<>();
        ArrayList<HashMap<Character, HashSet<Integer>>> mapReverse = new ArrayList<>();
        HashSet<Character> alph = new HashSet<>();
        HashSet<Integer> terminals = new HashSet<>();
        boolean[] reachable;

        public void makeReverseMap() {
            ArrayList<HashMap<Character, HashSet<Integer>>> newMap = new ArrayList<>();
            for (int i = 1; i <=n+1 ; i++) {
                newMap.add(new HashMap<>());
            }
            for (int v = 1; v <map.size() ; v++) {

                for (char ch : map.get(v).keySet()) {
                    int u = map.get(v).get(ch);
                    if (newMap.get(u)==null) {
                        newMap.set(u,new HashMap<>());
                    }
                    if (!newMap.get(u).containsKey(ch)) {
                        newMap.get(u).put(ch, new HashSet<>());
                    }
                    newMap.get(u).get(ch).add(v);

                }
            }
            mapReverse = newMap;
        }

        public DFA(int n, int m, int k, FastScanner in) {
            this.n = n;
            this.m = m;
            this.k = k;
            this.in = in;
            reachable = new boolean[n + 1];

        }

        void appendHellVertex() {
            HashMap<Character, Integer> tmp = new HashMap<>();
            for (char ch : alph) {
                tmp.put(ch, n + 1);
            }
            map.add(tmp);
            for (int v = 1; v <map.size() ; v++) {
                for (char ch : alph) {
                    if (!map.get(v).containsKey(ch)) {
                        map.get(v).put(ch, n + 1);
                    }
                }

            }
            n++;
        }

        void minimizeDFA() {
            clearFromUnreachable();
            //System.out.println("2:  "+(System.nanoTime()-a)/1000000000.0);
            k = terminals.size();
            appendHellVertex();
            //System.out.println("3:  "+(System.nanoTime()-a)/1000000000.0);
            makeReverseMap();
            //System.out.println("4:  "+(System.nanoTime()-a)/1000000000.0);
            ArrayList<HashSet<Integer>> classes = new ArrayList<>();
            classes.add(new HashSet<>());
            classes.add(new HashSet<>());
            int[] classBelong = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int isTerm = terminals.contains(i) ? 1 : 0;
                classBelong[i] = isTerm;
                if (isTerm == 1) {
                    classes.get(1).add(i);
                } else {
                    classes.get(0).add(i);
                }
            }
            LinkedList<Pair<Integer, Character>> queue = new LinkedList<>();
            if(classes.get(0).size()<classes.get(1).size()) {
                for (char ch : alph) {
                    queue.add(new Pair<>(0, ch));
                }
            }
            else {
                for (char ch : alph) {
                    queue.add(new Pair<>(1, ch));
                }
            }
            int safas=0;
            HashMap<Integer, HashSet<Integer>> involved;
            while (!queue.isEmpty()) {
                safas++;
                Pair cur = queue.peek();
                char ch = (char) cur.getValue();
                int curClassInd = (int) cur.getKey();
                queue.remove();
                involved = new HashMap<>();
                //System.out.println("5:  "+(System.nanoTime()-a)/1000000000.0);
                for (int x : classes.get(curClassInd)) {
                    if (mapReverse.get(x) == null || mapReverse.get(x).get(ch) == null) {
                        continue;
                    }
                    HashSet<Integer> reverse = mapReverse.get(x).get(ch);

                    for (int u : reverse) {
                        int uClass = classBelong[u];
                        if (involved.get(uClass) == null) {
                            involved.put(uClass, new HashSet<>());
                        }
                        involved.get(uClass).add(u);
                    }
                }
                //System.out.println("6:  "+(System.nanoTime()-a)/1000000000.0);
                for (int i : involved.keySet()) {
                    if (involved.get(i).size() < classes.get(i).size()) {
                        classes.add(new HashSet<>());
                        int indNewClass = classes.size() - 1;
                        for (int u : involved.get(i)) {
                            classes.get(i).remove(u);
                            classes.get(indNewClass).add(u);
                        }
//                        if (classes.get(indNewClass).size() > classes.get(i).size()) {
//                            HashSet<Integer> tmp = classes.get(indNewClass);
//                            classes.set(indNewClass, classes.get(i));
//                            classes.set(i, tmp);
//                        }
                        for (int u : classes.get(indNewClass)) {
                            classBelong[u] = indNewClass;
                        }
                        for (char ch1 : alph) {
                            queue.add(new Pair<>(indNewClass, ch1));
                        }
                    }
                }
                //System.out.println("7:  "+(System.nanoTime()-a)/1000000000.0);

            }
            System.out.println(safas);
            //System.out.println("end-1:  "+(System.nanoTime()-a)/1000000000.0);

            int newN = classes.size();
            ArrayList<HashMap<Character, Integer>> newMap = new ArrayList<>();
            for (int i = 1; i <=n+1 ; i++) {
                newMap.add(new HashMap<>());
            }
            HashSet<Integer> newTerms = new HashSet<>();
            int hellInd = 0;
            HashMap<Integer, Integer> indexToVertex = new HashMap<>();
            int counter = 2;
            for (int i = 0; i < newN; i++) {
                if (classes.get(i).contains(n)) {
                    hellInd = i;
                    indexToVertex.put(i, newN);
                    continue;
                }
                if (classes.get(i).contains(1)) {
                    indexToVertex.put(i, 1);
                    continue;
                }
                indexToVertex.put(i, counter);
                counter++;
            }
            int newM = 0;
            for (int v = 1; v <map.size() ; v++) {
                int VclassInd = classBelong[v];
                if (VclassInd == hellInd) {
                    continue;
                }
                int newV = indexToVertex.get(VclassInd);
                if (newMap.get(newV) == null) {
                    newMap.set(newV, new HashMap<>());
                }
                for (char ch : map.get(v).keySet()) {
                    int u = map.get(v).get(ch);
                    int UclassInd = classBelong[u];
                    if (UclassInd == hellInd) {
                        continue;
                    }
                    int newU = indexToVertex.get(UclassInd);
                    if (!newMap.get(newV).containsKey(ch)) {
                        newMap.get(newV).put(ch, newU);
                        newM++;
                    }
                    if (terminals.contains(u) && !newTerms.contains(newU)) {
                        newTerms.add(newU);
                    }

                }
                if (terminals.contains(v) && !newTerms.contains(newV)) {
                    newTerms.add(newV);
                }
            }

            map = newMap;
            n = newN - 1;
            k = newTerms.size();
            m = newM;
            terminals = newTerms;
        }


        void clearFromUnreachable() {
            reachable[1] = true;
            findReachables(1);

            for (int v = 1; v <map.size() ; v++) {
                if (!reachable[v]) {
                    map.get(v).clear();
                    map.get(v).put(randomAlph, v);
                    if (terminals.contains(v)) {
                        terminals.remove(v);
                    }
                } else {
                    for (char ch : map.get(v).keySet()) {
                        if (!reachable[map.get(v).get(ch)]) {
                            map.get(v).remove(ch);
                        }
                    }

                }
            }


        }

        void findReachables(int v) {
//            visited[v] = true;
//            reachable[v] = true;
//
//            if (map.get(v) != null) {
//                for (Character ch :map.get(v).keySet()) {
//                    int u = map.get(v).get(ch);
//                    if (!visited[u]) {
//                        findReachables(u);
//                    }
//                }
//            }
            Stack<Integer> stack = new Stack<>();
            stack.push(v);
            while (!stack.isEmpty()) {
                int u = stack.pop();
                reachable[u] = true;
                for (char ch : map.get(u).keySet()) {
                    int to = map.get(u).get(ch);
                    if (!reachable[to]) {
                        stack.push(to);
                    }
                }
            }
        }

        void readDFA() throws IOException {
            for (int i = 0; i < k; i++) {
                terminals.add(in.nextInt());
            }
            for (int i = 0; i < m; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                String a = in.next();
                randomAlph = a.charAt(0);
                if (!alph.contains(a.charAt(0)))
                    alph.add(a.charAt(0));
                map.get(from).put(a.charAt(0), to);


            }
        }
    }


    void solve(PrintWriter out) throws IOException {

        FastScanner in = new FastScanner("fastminimization.in");
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        DFA A = new DFA(n, m, k, in);
        for (int x = 1; x <= n+1; x++) {
            A.map.add(new HashMap<>());
        }
        A.readDFA();
        //System.out.println("1:  "+System.nanoTime()/1000000000.0);
        A.minimizeDFA();

        out.println(A.n + " " + A.m + " " + A.k);
        for (int v : A.terminals) {
            out.print(v + " ");
        }
        out.println();
        for (int v = 1; v <A.map.size() ; v++) {
            for (char ch : A.map.get(v).keySet()) {
                int u = A.map.get(v).get(ch);
                out.println(v + " " + u + " " + ch);
            }
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("fastminimization.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        a = System.nanoTime();
        new I().run();
        double b = System.nanoTime();
        System.out.println((b - a) / 1000000000.0);


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
