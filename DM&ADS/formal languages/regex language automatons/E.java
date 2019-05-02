package DiskrLab5;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class E {


    void solve(PrintWriter out) throws IOException {
        HashSet<Character> alph = new HashSet<>();
        FastScanner in = new FastScanner("problem5.in");
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        HashSet<Integer> terminals = new HashSet<>();
        for (int i = 0; i < k; i++) {
            terminals.add(in.nextInt()-1);
        }
        HashMap<Integer, HashMap<Character, HashSet<Integer>>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            String a = in.next();
            if (!alph.contains(a.charAt(0)))
                alph.add(a.charAt(0));
            if (!map.containsKey(from)) {
                map.put(from, new HashMap<>());
            }
            if (!map.get(from).containsKey(a.charAt(0))) {
                map.get(from).put(a.charAt(0), new HashSet<>());
                map.get(from).get(a.charAt(0)).add(to);
            } else {
                map.get(from).get(a.charAt(0)).add(to);
            }
        }

//        HashMap<String,Integer> test=new HashMap<>();
//        String a="a";
//        String b= "a";
//        test.put(a,1);
//        System.out.println(test.containsKey(b));
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());

        // НКА -> ДКА ----------------------
        HashMap<String, Integer> newMap = new HashMap<>();
        String start = "1";
        for (int i = 0; i < n-1; i++) {
            start += "0";
        }
        newMap.put(start, 0);
        int count = 1;
        LinkedList<String> queue = new LinkedList<>();
        queue.push(start);
        ArrayList<Integer> from = new ArrayList<>();
        ArrayList<Integer> to = new ArrayList<>();
        HashSet<Integer> terms = new HashSet<>();
        if (terminals.contains(0))
            terms.add(0);
        while (!queue.isEmpty()) {
            String v = queue.peek();
            queue.remove();
            for (char ch:alph) {
                String u = "";
                for (int i = 0; i < n; i++) {
                    u += "0";
                }
                boolean isTerminal = false;
                for (int i = 0; i < n; i++) {
                    if (v.charAt(i) == '0')
                        continue;
                    HashSet<Integer> cur;
                    try {
                        cur = map.get(i).get((char) ch);
                    }
                    catch (Exception e){
                        cur=null;
                    }
                    if (cur == null) {
                        continue;
                    }
                    for (int x : cur) {
                        u = u.substring(0, x) + "1" + u.substring(x + 1);
                        isTerminal |= terminals.contains(x);
                    }
                }
                boolean flag = false;
                if (!newMap.containsKey(u)) {
                    newMap.put(u, count);
                    if (isTerminal)
                        terms.add(count);
                    count++;
                    queue.push(u);
                }
                from.add(newMap.get(v));
                to.add(newMap.get(u));
            }
        }
        //-------------------------------------------------


        int[] dp = new int[count];
        for (int i = 0; i < count; i++)
            dp[i] = terms.contains(i) ? 1 : 0;
        for (int i = 0; i < l; i++) {
            int[] dpTmp = new int[count];
            for (int j = 0; j < from.size(); j++) {
                dpTmp[from.get(j)] += dp[to.get(j)];
                dpTmp[from.get(j)] %= 1000000007;
            }
            dp = dpTmp;
        }
        out.print(dp[newMap.get(start)]);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("problem5.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new E().run();

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