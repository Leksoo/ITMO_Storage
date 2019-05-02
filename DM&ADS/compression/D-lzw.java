package DiskrLab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class D {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("lzw.in"));
        PrintWriter out = new PrintWriter("lzw.out");
        String line = scanner.next();
        int n = line.length();
        StringBuilder t = new StringBuilder(Character.toString(line.charAt(0)));
        Map<String, Integer> words = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            words.put(Character.toString((char) ((int) 'a' + i)), i);
        }
        int k = 26;
        for (int i = 1; i <= n; i++) {
            if (i == n) {
                out.print(words.get(t.toString()));
                break;
            }
            char y = line.charAt(i);
            StringBuilder x = new StringBuilder(t);

            if (words.containsKey(x.append(y).toString())) {
                t.append(y);
            } else {
                out.print(words.get(t.toString()) + " ");
                words.put(t.append(y).toString(), k);
                k++;
                t = new StringBuilder(Character.toString(y));
            }

        }
        out.flush();
        scanner.close();


    }
}
