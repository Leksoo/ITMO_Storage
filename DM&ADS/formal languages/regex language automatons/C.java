package DiskrLab5;

import java.io.*;
import java.util.*;

public class C {


    HashMap<Integer,HashMap<Character,Integer>> map=new HashMap<>();
    HashMap<Integer,HashMap<Character,Integer>> mapReverse=new HashMap<>();
    HashSet<Integer> terminals=new HashSet<>();
    boolean[] visited;
    boolean[] useful;
    int[] colors;
    int[] res;
    boolean[] reachable;
    ArrayList<Integer> sorted=new ArrayList<>();

    boolean hasCycle=false;

    final long A=1000000007;

    void reachDfs(int v){
        visited[v] = true;
        reachable[v] = true;
        HashMap<Character, Integer> curMap = map.get(v);

        if (curMap != null) {
            for (Character ch : curMap.keySet()) {
                int u = map.get(v).get(ch);
                if (!visited[u]) {
                    reachDfs(u);
                }
            }
        }

    }
    void dfs(int v) {
        visited[v] = true;
        HashMap<Character, Integer> curMap = mapReverse.get(v);

        if (curMap != null) {
            for (Character ch : curMap.keySet()) {
                int u = mapReverse.get(v).get(ch);
                useful[u]=true;
                if (!visited[u]) {
                    dfs(u);
                }
            }
        }
    }

    void cycleDfs(int v){
        colors[v]=1;
        HashMap<Character, Integer> curMap = map.get(v);
        if (curMap != null && useful[v] && reachable[v] ) {
            for (Character ch : curMap.keySet()) {
                int u = map.get(v).get(ch);
                if(colors[u]==0){
                    cycleDfs(u);
                }
                if(colors[u]==1){
                    hasCycle=true;
                    return;
                }
            }
        }
        colors[v]=2;
    }

    void sortDfs(int v){
        visited[v]=true;
        HashMap<Character, Integer> curMap = map.get(v);
        if (curMap != null) {
            for (Character ch : curMap.keySet()) {
                int u = map.get(v).get(ch);
                if(!visited[u]){
                    sortDfs(u);
                }
            }
        }
        sorted.add(v);

    }



    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("problem3.in");
        HashSet<Character> alph = new HashSet<>();
        int n=in.nextInt();
        int m =in.nextInt();
        int k=in.nextInt();

        for (int i = 0; i <k ; i++) {
            terminals.add(in.nextInt());
        }
        for (int i = 0; i <m ; i++) {
            int from=in.nextInt();
            int to=in.nextInt();
            String a=in.next();
            if (!alph.contains(a.charAt(0)))
                alph.add(a.charAt(0));
            if (!map.containsKey(from)){
                map.put(from,new HashMap<>());
                map.get(from).put(a.charAt(0),to);

            }
            else {
                map.get(from).put(a.charAt(0),to);
            }

            if(!mapReverse.containsKey(to)){
                mapReverse.put(to,new HashMap<>());
                mapReverse.get(to).put(a.charAt(0),from);
            }
            else{
                mapReverse.get(to).put(a.charAt(0),from);
            }
        }
        useful=new boolean[n+1];
        visited=new boolean[n+1];
        for (int term:terminals) {
            useful[term]=true;
            dfs(term);
        }
        visited=new boolean[n+1];
        reachable=new boolean[n+1];
        reachDfs(1);
        colors=new int[n+1];
        for (int i = 1; i <=n ; i++) {
            if(colors[i]==0 && reachable[i])
                cycleDfs(i);
        }
        if(hasCycle){
            out.println(-1);
            return;
        }
        visited=new boolean[n+1];
        for (int i = 1; i <=n ; i++) {
            if(!visited[i] && reachable[i]){
                sortDfs(i);
            }
        }
        res=new int[n+1];
        res[1]=1;
        for (int i = sorted.size()-1; i >=0 ; i--) {
            int v=sorted.get(i);
            HashMap<Character, Integer> curMap = map.get(v);
            if (curMap != null && reachable[v]) {
                for (Character ch : curMap.keySet()) {
                    int u = map.get(v).get(ch);
                    res[u] += res[v];
                    res[u] %= A;
                }
            }
        }
        int result=0;
        for (int v:terminals) {
            if(reachable[v]) {
                result += res[v];
                result %= A;
            }
        }
//        if (terminals.contains(1)){
//            result-=1;
//        }

        out.println(result);





    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("problem3.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new C().run();

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