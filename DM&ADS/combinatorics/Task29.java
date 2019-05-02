package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task29 {

    ArrayList<Integer> nextPart(ArrayList<Integer> arr) {
        arr.set(arr.size() - 1, arr.get(arr.size() - 1) - 1);
        arr.set(arr.size() - 2, arr.get(arr.size() - 2) + 1);
        if (arr.get(arr.size() - 2) > arr.get(arr.size() - 1)) {
            arr.set(arr.size() - 2, arr.get(arr.size() - 2) + arr.get(arr.size() - 1));
            arr.remove(arr.size() - 1);
            return arr;
        }
        while (arr.get(arr.size() - 2) * 2 <= arr.get(arr.size() - 1)) {
            arr.add(arr.get(arr.size() - 1) - arr.get(arr.size() - 2));
            arr.set(arr.size() - 2, arr.get(arr.size() - 3));
        }
        return arr;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("nextpartition.in");
        String p=in.next();
        String value="";
        ArrayList<Integer> arr=new ArrayList<>();
        for (int i = 0; i <p.length() ; i++) {
            if (p.charAt(i)=='='){
                p=p.substring(i+1);
                break;
            }
            value=value+p.charAt(i);
        }
        StringBuilder a=new StringBuilder();
        for (int i = 0; i <p.length() ; i++) {
            if (i==p.length()-1){
                a.append(p.charAt(i));
                arr.add(Integer.parseInt(a.toString()));
            }
            if (p.charAt(i)=='+'){
                arr.add(Integer.parseInt(a.toString()));
                a=new StringBuilder();
            }else {
                a.append(p.charAt(i));
            }
        }
        if (arr.size()==1){
            out.print("No solution");
        }
        else {
            arr=nextPart(arr);
            out.print(value+"=");
            for (int i = 0; i <arr.size()-1; i++) {
                out.print(arr.get(i)+"+");
            }
            out.print(arr.get(arr.size()-1));
        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nextpartition.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task29().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null || line.equals("")) {
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
