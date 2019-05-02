package AlgoLab2;

import java.io.*;
import java.util.*;

public class F {

    public class Sold{
        int prev;
        int nex;
        public Sold(int prev,int nex){
            this.prev=prev;
            this.nex=nex;
        }
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("formation.in");
        BufferedReader r=new BufferedReader(new InputStreamReader(new FileInputStream("formation.in")));
        String[] l=r.readLine().split(" ");
        int n=Integer.parseInt(l[0]);
        int m=Integer.parseInt(l[1]);
        Sold[] str=new Sold[n+1];
        str[1]=new Sold(0,0);
        for (int i = 0; i <m ; i++) {
            String[] comm=r.readLine().split(" ");
            if (comm[0].equals("left")){
                Sold sold=str[Integer.parseInt(comm[2])];
                Sold leftSold;
                if (sold.prev==0){
                    sold.prev=Integer.parseInt(comm[1]);
                    leftSold=new Sold(0,Integer.parseInt(comm[2]));
                }
                else {
                    int prevprev=sold.prev;
                    sold.prev=Integer.parseInt(comm[1]);
                    leftSold=new Sold(prevprev,Integer.parseInt(comm[2]));
                    Sold prevprevSold=str[prevprev];
                    prevprevSold.nex=Integer.parseInt(comm[1]);
                    str[prevprev]=prevprevSold;
                }

                str[Integer.parseInt(comm[1])]=leftSold;
                str[Integer.parseInt(comm[2])]=sold;
            }
            else if (comm[0].equals("right")){
                Sold sold=str[Integer.parseInt(comm[2])];
                Sold rightSold;
                if (sold.nex==0){
                    sold.nex=Integer.parseInt(comm[1]);
                    rightSold=new Sold(Integer.parseInt(comm[2]),0);
                }
                else {
                    int nexnex=sold.nex;
                    sold.nex=Integer.parseInt(comm[1]);
                    rightSold=new Sold(Integer.parseInt(comm[2]),nexnex);
                    Sold nexnexSold=str[nexnex];
                    nexnexSold.prev=Integer.parseInt(comm[1]);
                    str[nexnex]=nexnexSold;
                }

                str[Integer.parseInt(comm[1])]=rightSold;
                str[Integer.parseInt(comm[2])]=sold;
            }
            else if (comm[0].equals("leave")) {
               Sold leaveSold=str[(Integer.parseInt(comm[1]))];
               int leftInd=leaveSold.prev;
               int rightInd=leaveSold.nex;
               if(leftInd==0) {
                   Sold rightSold = str[rightInd];
                   rightSold.prev=0;
                   str[rightInd]=rightSold;
               }
             else  if (rightInd==0){
                   Sold leftSold = str[leftInd];
                   leftSold.nex=0;
                   str[leftInd]=leftSold;

               }
               else {
                   Sold rightSold = str[rightInd];
                   Sold leftSold = str[leftInd];
                   rightSold.prev=leftInd;
                   leftSold.nex=rightInd;
                   str[rightInd]=rightSold;
                   str[leftInd]=leftSold;
               }
               str[Integer.parseInt(comm[1])]=null;


            }
            else if (comm[0].equals("name")){
                Sold sold=str[Integer.parseInt(comm[1])];
                out.println(sold.prev+" "+sold.nex);
            }
        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("formation.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new F().run();

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
