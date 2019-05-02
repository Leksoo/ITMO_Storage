package DiskrLab6;

import java.io.*;
import java.util.*;

public class A {



    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("automaton.in");
        int n=in.nextInt();
        char start=in.next().charAt(0);
        HashSet<Character> terminals=new HashSet<>();
        for (int i = 0; i <1 ; i++) {
            terminals.add('%');
        }
        HashMap<Character, HashMap<Character, HashSet<Character>>> map = new HashMap<>();
        for (int i = 0; i <n ; i++) {
            String inp=in.next()+" " +in.next()+" "+in.next();
            char from;
            char ch;
            char to;
            if (inp.length()==7){
                from=inp.charAt(0);
                ch=inp.charAt(5);
                to=inp.charAt(6);

            }
            else{
                from=inp.charAt(0);
                ch=inp.charAt(5);
                to='%';
            }
            if (!map.containsKey(from)) {
                map.put(from, new HashMap<>());
            }
            if (!map.get(from).containsKey(ch)) {
                map.get(from).put(ch, new HashSet<>());
                map.get(from).get(ch).add(to);
            } else {
                map.get(from).get(ch).add(to);
            }
        }

        int m=in.nextInt();

        for (int k= 0; k <m ; k++) {
            String input=in.next();
            Set<Character> ans = new HashSet<>();
            ans.add(start);
            boolean flag=true;
            for (int i = 0; i < input.length(); i++) {
                char a = input.charAt(i);
                Set<Character> ansTmp = new HashSet<>();
                for (char el : ans
                        ) {
                    try {
                        HashSet<Character> tmp = map.get(el).get(a);
                        ansTmp.addAll(tmp);
                    } catch (Exception e) {

                    }
                    ans = ansTmp;

                }
            }
            for (char el : ans) {
                if (terminals.contains(el)) {
                    out.println("yes");
                    flag=false;
                    break;
                }
            }
            if(flag)
                out.println("no");
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("automaton.out");
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