package AlgoLab2;

import java.io.*;
import java.util.*;

public class G {


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("hospital.in");
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("hospital.in")));
        ArrayList<Character> a = new ArrayList<>();
        int n= Integer.parseInt(r.readLine());
        List<Integer> deck1=new ArrayList<>();
        List<Integer> deck2=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            String[] comm=r.readLine().split(" ");
            if (comm[0].charAt(0)=='+'){
                deck1.add(0,Integer.parseInt(comm[1]));
                if (deck1.size()!=deck2.size()){
                    deck2.add(0,deck1.remove(deck1.size()-1));
                }
            }
            else if (comm[0].charAt(0)=='*'){
                if ((deck1.size()+deck2.size())%2==0){
                    deck2.add(0,Integer.parseInt(comm[1]));
                }
                else {
                    deck1.add(Integer.parseInt(comm[1]));
                }
            }
            else if (comm[0].charAt(0)=='-'){
                out.println(deck2.remove(deck2.size()-1));
                if (deck1.size()!=deck2.size()){
                    deck2.add(0,deck1.remove(deck1.size()-1));
                }
            }

        }
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("hospital.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new G().run();

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
