package DiskrLab1;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class C {


    public static void main(String[] args) throws IOException {
        int[] argsCount;
        String[] func;
        int n;
        int[][] res;

        Scanner reader = new Scanner(System.in);
        n = reader.nextInt();
        argsCount = new int[n];
        func = new String[n];
        res = new int[5][n];
        for (int i = 0; i < n; i++) {
            argsCount[i] = reader.nextInt();
            func[i] = reader.next();

        }

        //res[0]-save 0
        for (int i = 0; i < n; i++) {
            if (func[i].charAt(0) == '0') {
                res[0][i] = 1;
            } else {
                res[0][i] = 0;
            }

            //res[1]-save 1
            if (func[i].charAt(func[i].length() - 1) == '1') {
                res[1][i] = 1;
            } else {
                res[1][i] = 0;
            }

            //res[2]-dvoystv
            for (int j = 0; j < func[i].length() / 2; j++) {
                if (func[i].charAt(j) == func[i].charAt(func[i].length() - j - 1)) {
                    res[2][i] = 0;
                    break;
                } else {
                    res[2][i] = 1;
                }
            }

            //res[3]-monotonn
            for (int m = 1; m <=argsCount[i] ; m++) {

                int marker=1;
                for (int j = 0; j < (int)Math.pow(2,argsCount[i]); j += (int) Math.pow(2,m)) {
                    BigInteger a=new BigInteger(func[i].substring(j, j+((int) Math.pow(2,m))/2));
                    BigInteger b=new BigInteger(func[i].substring(j+((int) Math.pow(2,m))/2,j+(int) Math.pow(2,m)));
                    if (a.compareTo(b)>0){
                        marker=0;
                        break;
                    }
                }
                if (marker==0){
                    res[3][i] = 0;
                    break;
                }
                else {
                    res[3][i] = 1;
                }
            }
            if (func[i].equals("0") || func[i].equals("1")){
                res[3][i]=1;
            }


            //res[4]-lin
            int zhyg[][] = new int[(int) Math.pow(2, argsCount[i])][(int) Math.pow(2, argsCount[i])];
            int k = (int) Math.pow(2, argsCount[i]) - 1;
            char[] s = func[i].toCharArray();
            for (int j = 0; j < (int) Math.pow(2, argsCount[i]); j++) {
                zhyg[j][0] = s[j] - '0';

            }
            for (int t = 1; t < (int) Math.pow(2, argsCount[i]); t++) {
                for (int j = 0; j < k; j++) {
                    if (zhyg[j][t - 1] == zhyg[j + 1][t - 1]) {
                        zhyg[j][t] = 0;
                    } else {
                        zhyg[j][t] = 1;
                    }

                }
                k--;

            }
            for (int j = 0; j < (int) Math.pow(2, argsCount[i]); j++) {
                int l = Integer.parseInt(Integer.toBinaryString(j));
                int oneC = 0;
                while (l > 0) {
                    oneC += l % 10;
                    l /= 10;
                }
                if (oneC > 1 && zhyg[0][j] == 1) {
                    res[4][i] = 0;
                    break;
                } else {
                    res[4][i] = 1;
                }
            }

        }
        boolean result = true;
        for (int i = 0; i < 5; i++) {
            int marker = 0;
            for (int j = 0; j < n; j++) {
                if (res[i][j] == 0) {
                    marker = 1;
                    break;
                }


            }
            if (marker == 0) {
                result = false;
                break;
            }
        }

        if (result) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }


    }
}