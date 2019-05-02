package DiskrLab1;

import java.io.*;
import java.util.*;

public class F {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n=Integer.parseInt(reader.readLine());
        String[] mas=new String[(int)Math.pow(2,n)];
        int[][] res=new int[(int)Math.pow(2,n)][(int)Math.pow(2,n)];
        for (int i = 0; i < (int)Math.pow(2,n); i++) {
            String line=reader.readLine();
            mas[i]=line.split(" ")[0];
            res[i][0]=Integer.parseInt(line.split(" ")[1]);

        }
        int k=(int)Math.pow(2,n)-1;
        for (int i = 1; i < (int)Math.pow(2,n); i++) {
            for (int j = 0; j <k ; j++) {
                if (res[j][i-1]==res[j+1][i-1]){
                    res[j][i]=0;
                }
                else {
                    res[j][i]=1;
                }

            }
            k--;
            
        }
        for (int i = 0; i <(int)Math.pow(2,n) ; i++) {
            System.out.print(mas[i]+" ");
            System.out.println(res[0][i]);

        }



    }
}



