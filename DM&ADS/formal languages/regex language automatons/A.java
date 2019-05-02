package DiskrLab5;

import java.io.*;
import java.util.*;

public class A {



    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("problem1.in");
        String input=in.next();
        int n=in.nextInt();
        int m =in.nextInt();
        int k=in.nextInt();
        HashSet<Integer> terminals=new HashSet<>();
        for (int i = 0; i <k ; i++) {
            terminals.add(in.nextInt());
        }
        HashMap<Integer,HashMap<Character,Integer>> map=new HashMap<>();
        for (int i = 0; i <m ; i++) {
            int from=in.nextInt();
            int to=in.nextInt();
            String a=in.next();
            if (!map.containsKey(from)){
                map.put(from,new HashMap<>());
                map.get(from).put(a.charAt(0),to);

            }
            else {
                map.get(from).put(a.charAt(0),to);
            }
        }
        int cur=1;
        for (int i = 0; i <input.length() ; i++) {
            try {

                cur = map.get(cur).get(input.charAt(i));
            }
            catch (Exception e){
                out.println("Rejects");
                return;
            }
        }
        if (terminals.contains(cur)){
            out.println("Accepts");
        }
        else {
            out.println("Rejects");
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("problem1.out");
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
                if (line == null ) {
                    return null;
                }
                if(line.equals("")){
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