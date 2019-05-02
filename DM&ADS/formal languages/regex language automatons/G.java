package DiskrLab5;

import javafx.util.Pair;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.*;

public class G {

    static boolean result=true;
    static boolean[] visited;
    static DFA A;
    static DFA B;

    static class DFA {
        FastScanner in;
        char randomAlph;
        int n;
        int m;
        int k;
        HashMap<Integer, HashMap<Character, Integer>> map = new HashMap<>();
        HashMap<Integer, HashMap<Character, HashSet<Integer>>> mapReverse = new HashMap<>();
        HashSet<Character> alph = new HashSet<>();
        HashSet<Integer> terminals = new HashSet<>();
        boolean[] useful;
        boolean[] reachable;
        boolean[] visited;
        int[] dfsnumber;

        public void makeReverseMap() {
            HashMap<Integer, HashMap<Character, HashSet<Integer>>> newMap = new HashMap<>();
            for (int v : map.keySet()) {
                HashMap<Character, Integer> cur = map.get(v);
                for (char ch : cur.keySet()) {
                    int u = map.get(v).get(ch);
                    if (!newMap.containsKey(u)) {
                        newMap.put(u, new HashMap<>());
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
            useful = new boolean[n + 1];
            reachable = new boolean[n + 1];
            visited=new boolean[n+1];
            dfsnumber=new int[n+1];

        }

        void appendHellVertex() {
            HashMap<Character, Integer> tmp = new HashMap<>();
            for (char ch : alph) {
                tmp.put(ch, n + 1);
            }
            map.put(n + 1, tmp);
            for (int v : map.keySet()) {
                HashMap<Character, Integer> cur = map.get(v);
                if (cur == null) {
                    map.put(v, tmp);
                } else {
                    for (char ch : alph) {
                        if (!cur.containsKey(ch)) {
                            map.get(v).put(ch, n + 1);
                        }
                    }
                }
            }
            n++;
        }

        void minimizeDFA() {
            clearFromUnreachable();
            //System.out.println("2:  "+(System.nanoTime()-a)/1000000000.0);
            k=terminals.size();
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
            for (char ch:alph) {
                queue.add(new Pair<>(1,ch));
                queue.add(new Pair<>(0,ch));
            }
            int safas=0;
            HashMap<Integer,HashSet<Integer>> involved;
            while (!queue.isEmpty()) {
                safas++;
                Pair cur = queue.peek();
                char ch = (char) cur.getValue();
                int curClassInd=(int) cur.getKey();
                queue.remove();
                involved=new HashMap<>();
                //System.out.println("5:  "+(System.nanoTime()-a)/1000000000.0);
                for(int x:classes.get(curClassInd)){
                    if (mapReverse.get(x)==null || mapReverse.get(x).get(ch)==null){
                        continue;
                    }
                    HashSet<Integer> reverse = mapReverse.get(x).get(ch);

                    for (int u:reverse) {
                        int uClass=classBelong[u];
                        if(involved.get(uClass)==null){
                            involved.put(uClass,new HashSet<>());
                        }
                        involved.get(uClass).add(u);
                    }
                }
                //System.out.println("6:  "+(System.nanoTime()-a)/1000000000.0);
                for (int i:involved.keySet()) {
                    if(involved.get(i).size()<classes.get(i).size()){
                        classes.add(new HashSet<>());
                        int indNewClass=classes.size()-1;
                        for (int u:involved.get(i)){
                            classes.get(i).remove(u);
                            classes.get(indNewClass).add(u);
                        }
                        if(classes.get(indNewClass).size()>classes.get(i).size()){
                            HashSet<Integer> tmp=classes.get(indNewClass);
                            classes.set(indNewClass,classes.get(i));
                            classes.set(i,tmp);
                        }
                        for (int u:classes.get(indNewClass)) {
                            classBelong[u]=indNewClass;
                        }
                        for (char ch1: alph) {
                            queue.add(new Pair<>(indNewClass,ch1));
                        }
                    }
                }
                //System.out.println("7:  "+(System.nanoTime()-a)/1000000000.0);

            }
            //System.out.println(safas);
            //System.out.println("end-1:  "+(System.nanoTime()-a)/1000000000.0);

            int newN = classes.size();
            HashMap<Integer, HashMap<Character, Integer>> newMap = new HashMap<>();
            HashSet<Integer> newTerms = new HashSet<>();
            int startInd = 0;
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
                    startInd = i;
                    indexToVertex.put(i, 1);
                    continue;
                }
                indexToVertex.put(i, counter);
                counter++;
            }
            int newM = 0;
            for(int v:map.keySet()){
                int VclassInd=classBelong[v];
                if(VclassInd==hellInd){
                    continue;
                }
                int newV=indexToVertex.get(VclassInd);
                if(newMap.get(newV)==null) {
                    newMap.put(newV, new HashMap<>());
                }
                for (char ch:map.get(v).keySet()){
                    int u=map.get(v).get(ch);
                    int UclassInd=classBelong[u];
                    if(UclassInd==hellInd){
                        continue;
                    }
                    int newU=indexToVertex.get(UclassInd);
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
            //System.out.println("end:  "+(System.nanoTime()-a)/1000000000.0);

//            for (int i = 0; i < newN; i++) {
//                if (i == hellInd) {
//                    continue;
//                }
//                int v = indexToVertex.get(i);
//                newMap.put(v, new HashMap<>());
//                for (int x : classes.get(i)) {
//                    if(map.get(x)!=null) {
//                        for (char ch : map.get(x).keySet()) {
//                            int u = map.get(x).get(ch);
//                            int uClass = classBelong[u];
//                            if(uClass==hellInd){
//                                continue;
//                            }
//                            int uNum = indexToVertex.get(uClass);
//                            if (!newMap.get(v).containsKey(ch)) {
//                                newMap.get(v).put(ch, uNum);
//                                newM++;
//                            }
//                        }
//                    }
//                    if (terminals.contains(x) && !newTerms.contains(v)) {
//                        newTerms.add(v);
//                    }
//                }
//            }
            map = newMap;
            n = newN-1;
            k = newTerms.size();
            m = newM;
            terminals = newTerms;
        }


        void clearFromUnreachable() {
            visited[1]=true;
            reachable[1]=true;
            findReachables(1);
            HashSet<Integer> delList=new HashSet<>();
            for (int x=1; x<=n;x++){
                if(map.get(x)==null){
                    map.put(x,new HashMap<>());
                }
            }
            for (int v : map.keySet()) {
                if (!reachable[v]) {
                    map.put(v,new HashMap<>());
                    map.get(v).put(randomAlph,v);
                    if(terminals.contains(v)){
                        terminals.remove(v);
                    }
                } else {
                    HashMap<Character, Integer> cur = map.get(v);
                    if (cur != null) {
                        for (char ch : cur.keySet()) {
                            if (!reachable[cur.get(ch)]) {
                                map.get(v).remove(ch);
                            }
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
            Stack<Integer> stack=new Stack<>();
            stack.push(v);
            while (!stack.isEmpty()) {
                int u = stack.pop();
                visited[u]=true;
                reachable[u]=true;
                if(map.get(u)!=null) {
                    for (char ch:map.get(u).keySet()) {
                        int to=map.get(u).get(ch);
                        if(!visited[to]) {
                            stack.push(to);
                        }
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
                randomAlph=a.charAt(0);
                if (!alph.contains(a.charAt(0)))
                    alph.add(a.charAt(0));
                if (!map.containsKey(from)) {
                    map.put(from, new HashMap<>());
                    map.get(from).put(a.charAt(0), to);

                } else {
                    map.get(from).put(a.charAt(0), to);
                }

            }
        }
    }


    void dfs(int v1, int v2) {
        visited[v1] = true;
        if (A.terminals.contains(v1) != B.terminals.contains(v2)) {
            result = false;
            return;
        }

        HashMap<Character, Integer> curMap = A.map.get(v1);
        if (curMap == null && B.map.get(v2) != null) {
            result = false;
            return;
        }
        if (curMap != null) {
            for (Character ch : curMap.keySet()) {
                int u1 = A.map.get(v1).get(ch);
                if (!B.map.get(v2).containsKey(ch)) {
                    result = false;
                    return;
                }
                int u2 = B.map.get(v2).get(ch);
                if((u1==v1 && u2!=v2)||(v1!=u1 && u2==v2)){
                    result=false;
                    return;
                }
                if (!visited[u1]) {
                    dfs(u1, u2);
                }
            }
        }
        return;
    }




    void solve(PrintWriter out) throws IOException {

        FastScanner in = new FastScanner("equivalence.in");
        int n1 = in.nextInt();
        int m1 = in.nextInt();
        int k1 = in.nextInt();
        A = new DFA(n1, m1, k1, in);
        A.readDFA();
        A.minimizeDFA();
        int n2 = in.nextInt();
        int m2 = in.nextInt();
        int k2 = in.nextInt();
        B = new DFA(n2, m2, k2, in);
        B.readDFA();
        B.minimizeDFA();

        if (A.n != B.n) {
            result = false;
        }
        if (A.m != B.m)
            result = false;
        if (A.k != B.k)
            result = false;
        if(A.map.size()!=B.map.size()){
            result=false;
        }
        if (!result) {
            out.println("NO");
            return;
        }
        visited = new boolean[n1 + 1];
        dfs(1, 1);
        if (result) out.println("YES");
        else out.println("NO");





    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("equivalence.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        //a=System.nanoTime();
        new G().run();
        //double b=System.nanoTime();
        //System.out.println((b-a)/ 1000000000.0);


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
