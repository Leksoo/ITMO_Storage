package DiskrLab6;

import java.io.*;
import java.util.*;

public class C {

    /*
    5 K
S -> C
S -> T
T -> S
T -> c
S -> a
     */
    void solve(PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("useless.in"));
        String[] a = in.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        char start = a[1].charAt(0);
        HashSet<String> rules = new HashSet<>();
        TreeSet<Character> nonTerm=new TreeSet<>();
        for (int i = 0; i < n; i++) {
            String input = in.readLine();
            rules.add(input);
            nonTerm.add(input.charAt(0));
            String[] arr=input.split(" ");
            if (arr.length==2){
                continue;
            }
            for (int j = 0; j <arr[2].length() ; j++) {
                if(Character.isUpperCase(arr[2].charAt(j))) {
                    nonTerm.add(arr[2].charAt(j));
                }
            }

        }
        HashSet<Character> need=new HashSet<>();
        HashMap<String ,Integer> count=new HashMap<>();
        HashMap<Character ,HashSet<String>> where=new HashMap<>();
        for (String rule:rules) {
            String[] arr=rule.split(" ");
            if(arr.length==3){
                for (int j = 0; j <arr[2].length() ; j++) {
                    if(Character.isUpperCase(arr[2].charAt(j))){
                        if (count.containsKey(rule)){
                            int tmp=count.get(rule);
                            tmp++;
                            count.put(rule,tmp);
                        }
                        else {
                            count.put(rule,1);
                        }

                        if (where.containsKey(arr[2].charAt(j))){
                            where.get(arr[2].charAt(j)).add(rule);
                        }
                        else {
                            where.put(arr[2].charAt(j),new HashSet<>());
                            where.get(arr[2].charAt(j)).add(rule);
                        }
                    }
                }

            }
            else {
                count.put(rule,0);
            }
            if (!count.containsKey(rule) || count.get(rule)==0){
                need.add(arr[0].charAt(0));
            }
        }

        while (true) {
            boolean needNext = false;
            for (String rule : rules) {
                String[] c = rule.split(" ");
                boolean flag = true;
                if(c.length==2){
                    continue;
                }
                for (int i = 0; i < c[2].length(); i++) {
                    if(Character.isLowerCase(c[2].charAt(i))) {
                        continue;
                    }

                    if (!need.contains(c[2].charAt(i))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (!need.contains(c[0].charAt(0))) {
                         need.add(c[0].charAt(0));
                        needNext = true;
                        break;
                    }
                }
            }
            if (!needNext) {
                break;
            }
        }


        HashSet<String> rules2=new HashSet<>();
        for (String rule: rules) {
            if (!need.contains(rule.charAt(0)) ){
                continue;
            }
            String[] c=rule.split(" ");
            boolean needAdd=true;
            if(c.length==3){
                for (int i = 0; i <c[2].length() ; i++) {
                    if(Character.isLowerCase(c[2].charAt(i))){
                        continue;
                    }
                    if (!need.contains(c[2].charAt(i)) ){
                        needAdd=false;
                    }
                }
            }
            if (needAdd){
                rules2.add(rule);
            }
        }
        HashSet<Character> acceptable=new HashSet<>();
        acceptable.add(start);
        while (true) {
            boolean needNext = false;
            for (String rule : rules2) {
                String[] c = rule.split(" ");
                if(acceptable.contains(c[0].charAt(0))){
                    if(c.length==3) {
                        for (int i = 0; i < c[2].length(); i++) {
                            if(Character.isLowerCase(c[2].charAt(i))){
                                continue;
                            }
                            if(!acceptable.contains(c[2].charAt(i))){
                                acceptable.add(c[2].charAt(i));
                                needNext=true;
                            }
                        }
                    }
                }
                if(needNext){
                    break;
                }
            }
            if (!needNext) {
                break;
            }
        }
        TreeSet<Character> ans=new TreeSet<>();
        for (String rule:rules) {
            if (!acceptable.contains(rule.charAt(0)) || !need.contains(rule.charAt(0)) ){
                ans.add(rule.charAt(0));
            }
            String[] c=rule.split(" ");
            if(c.length==3){
                for (int i = 0; i <c[2].length() ; i++) {
                    if(Character.isLowerCase(c[2].charAt(i))){
                        continue;
                    }
                    if (!acceptable.contains(c[2].charAt(i)) || !need.contains(c[2].charAt(i)) ){
                        ans.add(c[2].charAt(i));
                    }
                }
            }
        }
        if(!ans.contains(start)){
            if(ans.equals(nonTerm)){
                ans.add(start);
            }
        }
        for (char ch : ans) {
            out.print(ch + " ");
        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("useless.out");
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