package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task26 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("nextsetpartition.in");
        int n=1;
        int k=1;
        while (n!=0 || k!=0){
            ArrayList<ArrayList<Integer>> mas =new ArrayList<>();
            n=in.nextInt();
            k=in.nextInt();
            if (n==0 && k==0){
                return;
            }
            for (int i = 0; i < k; i++) {
                String[] f=in.reader.readLine().split(" ");
                ArrayList<Integer> f1=new ArrayList<>();
                for (int j = 0; j <f.length ; j++) {
                    f1.add(Integer.parseInt(f[j]));
                }
                mas.add(f1);
            }
            ArrayList<Integer> deleted=new ArrayList();
            int marker=0;
            int j;
            for (int i = mas.size()-1; i >=0 ; i--) {
                for (int l = 0; l <deleted.size() ; l++) {
                    if (mas.get(i).get(mas.get(i).size()-1)<deleted.get(l) ){
                        mas.get(i).add(deleted.remove(l));
                        marker=1;
                        break;
                    }
                }
                if (marker==1){
                    break;
                }

                    for (j = mas.get(i).size() - 1; j >= 0; j--) {
                        for (int l = 0; l <deleted.size() ; l++) {
                            if (j!=0 && deleted.size()>0 && deleted.get(l) > mas.get(i).get(j)) {
                                int a=mas.get(i).get(j);
                                mas.get(i).set(j, deleted.get(l));
                                deleted.remove(l);
                                deleted.add(a);
                                marker = 1;
                                break;
                            }
                        }
                        if (marker==1){
                            break;
                        }

                        deleted.add(mas.get(i).remove(mas.get(i).size()-1));
                        Collections.sort(deleted);
                    }

                if (marker==1){
                    break;
                }
                mas.remove(i);

            }




            Collections.sort(deleted);
            for (int i = 0; i <deleted.size() ; i++) {
                ArrayList<Integer> a=new ArrayList<>();
                a.add(deleted.get(i));
                mas.add(a);
            }
            out.println(n+" "+(mas.size()));
            for (ArrayList<Integer> x:mas
                 ) {
                for (int y:x
                     ) {
                    out.print(y+" ");

                }
                out.println();

            }
            out.println();






            in.reader.readLine();
        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nextsetpartition.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task26().run();

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
