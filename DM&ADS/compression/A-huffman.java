package DiskrLab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("huffman.in"));
        PrintWriter out = new PrintWriter("huffman.out");
        int n = scanner.nextInt();
        long[] arr = new long[n];
        long[] sums = new long[n];
        Arrays.fill(sums, Long.MAX_VALUE);
        int i = 0;
        int j = 0;
        for (int k = 0; k < n; k++) {
            arr[k] = scanner.nextLong();
        }
        Arrays.sort(arr);
        long ans = 0;
        int k=0;
        while (i<n || j<n-2){
            if (i<n-1){
                long[] mas=new long[4];
                mas[0]=arr[i];
                mas[1]=arr[i+1];
                mas[2]=sums[j];
                mas[3]=sums[j+1];
                Arrays.sort(mas);
                sums[k]=mas[0]+mas[1];
                ans+=sums[k];
                if ((arr[i]==mas[0] && arr[i+1]==mas[1])||((arr[i]==mas[1] && arr[i+1]==mas[0]))){
                    i+=2;
                }
                else if((sums[j]==mas[0] && sums[j+1]==mas[1])||((sums[j]==mas[1] && sums[j+1]==mas[0]))){
                    j+=2;
                }
                else {
                    j++;
                    i++;
                }

            }
            else if (i==n-1){
                long[] mas=new long[3];
                mas[0]=arr[i];
                mas[1]=sums[j];
                mas[2]=sums[j+1];
                Arrays.sort(mas);
                sums[k]=mas[0]+mas[1];
                ans+=sums[k];
                if ((arr[i]==mas[0])||((arr[i]==mas[1]))){
                    i+=1;
                    j+=1;
                }
                else {
                    j+=2;
                }
            }
            else if (i>=n){
                long[] mas=new long[2];
                mas[0]=sums[j];
                mas[1]=sums[j+1];
                Arrays.sort(mas);
                sums[k]=mas[0]+mas[1];
                ans+=sums[k];
                j+=2;
            }
            k++;
        }
        out.print(ans);
        out.flush();
        scanner.close();


    }
}
