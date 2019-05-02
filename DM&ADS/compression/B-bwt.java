package DiskrLab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("bwt.in"));
        PrintWriter out = new PrintWriter("bwt.out");
        String line=scanner.next();
        int n=line.length();
        ArrayList<String> mas= new ArrayList<>();
        mas.add(line);
        for (int i = 0; i <n-1 ; i++) {
            String a=line.substring(n-i-1);
            mas.add(a+line.substring(0,n-i-1));

        }
        Collections.sort(mas);
        for (int i = 0; i <mas.size() ; i++) {
            out.print(mas.get(i).charAt(n-1));
        }
        out.flush();
        scanner.close();


    }
}
