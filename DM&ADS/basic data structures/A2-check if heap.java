import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class A {
    public static void main(String[] args) throws IOException {
        Scanner scanner =new Scanner(new File("isheap.in"));
        PrintWriter out=new PrintWriter("isheap.out");
        int n=scanner.nextInt();
        int[] heap=new int[n];
        for (int i = 0; i <n ; i++) {
            heap[i]=scanner.nextInt();
            
        }
        for (int i = 0; 2*i+2 <n ; i++) {
            if (heap[i]>heap[2*i+1] || heap[i]>heap[2*i+2] ){
                out.print("NO");
                out.flush();
                return;
            }
            
        }
        out.print("YES");
        out.flush();
    }
}
