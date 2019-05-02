package DiskrLab1;

import java.io.*;
import java.util.*;

public class D {


    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        long[] mas = new long[n];
        for (int i = 0; i < n; i++) {
            mas[i] = reader.nextLong();
        }
        long s = reader.nextLong();
        String res = Long.toBinaryString(s);
        int maxLen = 33;
        String[] numbersBinar = new String[n];
        for (int i = 0; i < n; i++) {
            if (s == mas[i]) {
                System.out.print(i + 1);
                return;
            }
            String a = Long.toBinaryString(mas[i]);
            if (a.length() > maxLen) {
                maxLen = a.length();
            }
            numbersBinar[i] = a;
        }
        if (res.length() > maxLen) {
            maxLen = res.length();
        }
        for (int i = 0; i < n; i++) {
            StringBuilder a = new StringBuilder(numbersBinar[i]);
            if (a.length() < maxLen) {
                while (a.length() < maxLen) {
                    a.insert(0, "0");
                }

            }
            numbersBinar[i] = a.toString();
        }
        StringBuilder a = new StringBuilder(res);
        if (a.length() < maxLen) {
            while (a.length() < maxLen) {
                a.insert(0, "0");
            }

        }
        res = a.toString();
        Map<String, Character> table = new HashMap<>();
        for (int i = 0; i < maxLen; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(numbersBinar[j].charAt(i));
            }
            if (table.containsKey(row.toString())) {
                if (!(table.get(row.toString()) == (res.charAt(i)))) {
                    System.out.print("Impossible");
                    return;
                }
            } else {
                table.put(row.toString(), res.charAt(i));
            }
        }
        ArrayList<String> resMas = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        if (s!=0) {
            for (Map.Entry<String, Character> entry : table.entrySet()
                    ) {
                StringBuilder key = new StringBuilder(entry.getKey());
                char value = entry.getValue();
                if (value == '1') {
                    for (int i = 0; i < key.length(); i++) {
                        if (key.charAt(i) == '0') {
                            result.append((char) 126 + "" + (i + 1));
                        } else {
                            result.append((i + 1));
                        }
                        if (i != key.length() - 1) result.append((char) 38);
                    }

                    if (key.length() != 1) {
                        result.insert(0, (char) 40).append((char) 41);
                    }
                    resMas.add(result.toString());
                    result = new StringBuilder();
                }

            }

            for (int i = 0; i < resMas.size(); i++) {
                if (resMas.size() == 1 && resMas.get(i).charAt(0) == '(') {
                    resMas.set(i, resMas.get(i).substring(1, resMas.get(i).length() - 1));
                }
                System.out.print(resMas.get(i));
                if (i != resMas.size() - 1) {
                    System.out.print((char) 124);
                }
            }
        }

        else  {
            result = new StringBuilder();
            for (Map.Entry<String, Character> entry : table.entrySet()
                    ) {
                StringBuilder key = new StringBuilder(entry.getKey());
                char value = entry.getValue();
                if (value == '0') {
                    for (int i = 0; i < key.length(); i++) {
                        if (key.charAt(i) == '1') {
                            result.append((char)126 +""+ (i + 1));
                        } else {
                            result.append((i + 1));
                        }
                        if (i != key.length() - 1) result.append((char)124);
                    }

                    if (key.length() != 1) {
                        result.insert(0, (char)40).append((char)41);
                    }
                    resMas.add(result.toString());
                    result = new StringBuilder();
                }

            }

            for (int i = 0; i < resMas.size(); i++) {
                if (resMas.size() == 1 && resMas.get(i).charAt(0) == '(') {
                    resMas.set(i, resMas.get(i).substring(1, resMas.get(i).length() - 1));
                }
                System.out.print(resMas.get(i));
                if (i != resMas.size() - 1) {
                    System.out.print((char)38);
                }

            }


        }
    }
}