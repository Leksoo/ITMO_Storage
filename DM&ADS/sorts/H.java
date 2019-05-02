import java.io.*;
import java.util.*;

public class Kth {

    public static int kth(int[] a, int k,int l,int r) {
        if (l==r){
            return a[l];
        }
        int mid=partition(a,l,r);
        if (k<=mid){
            r=mid;
        }
        else {
            l=mid+1;
        }
        return kth(a,k,l,r);
    }

    static int partition(int[] a, int l, int r) {
        Random rnd = new Random();
        int mid=a[l + rnd.nextInt(Math.abs(r - l)+1)];
        int i=l;
        int k=r;
        while (i<=k){
            while(a[i]<mid){
                i++;
            }
            while (a[k]>mid){
                k--;
            }
            if(i<=k){
                swap(a,i,k);
                k--;
                i++;
            }
        }
        if (l>k){
            k=l;
        }
        return k;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(new File("kth.in"));
        PrintWriter out = new PrintWriter("kth.out");
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] mas = new int[n];
        int A =scanner.nextInt();
        int B = scanner.nextInt();
        int C =scanner.nextInt();
        mas[0] = scanner.nextInt();
        mas[1] = scanner.nextInt();
        for (int i = 2; i < n; i++) {
            mas[i] = A * mas[i - 2] + B * mas[i - 1] + C;
        }


        out.print(kth(mas, k - 1,0,mas.length-1));
        out.flush();
    }
}