package DiskrLab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class C {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("mtf.in"));
        PrintWriter out = new PrintWriter("mtf.out");
        String line=scanner.next();
        int n=line.length();
        ArrayList<String> mas= new ArrayList<>();
        for (int i = 0; i <26 ; i++) {
            mas.add(Character.toString((char)((int)'a'+i)));
        }
        for (int i = 0; i <n ; i++) {
            String a=Character.toString(line.charAt(i));
            int ind=mas.indexOf(a);
            out.print(ind+1+" ");
            mas.add(0,mas.remove(ind));
        }
        out.flush();
        scanner.close();


    }
}
