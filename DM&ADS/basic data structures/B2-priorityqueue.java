import java.io.*;
import java.util.*;

public class Priorityqueue {
    public static void siftDown(ArrayList<Integer> a, int i) {
        while (2 * i + 1 < a.size()) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < a.size() && a.get(right) < a.get(left)) {
                j = right;
            }
            if (a.get(i) <= a.get(j)) {
                break;
            }
            int t = a.get(i);
            a.set(i, a.get(j));
            a.set(j, t);
            i = j;
        }
    }

    public static void siftUp(ArrayList<Integer> a, int i) {
        while (a.get(i) < a.get((i - 1) / 2)) {
            int t = a.get(i);
            a.set(i, a.get((i - 1) / 2));
            a.set(((i - 1) / 2), t);
            i = (i - 1) / 2;
        }
    }

    public static int extractMin(ArrayList<Integer> a) {
        int min = a.get(0);
        a.set(0, a.get(a.size() - 1));
        a.remove(a.size() - 1);
        siftDown(a, 0);
        return min;
    }

    public static void insert(ArrayList<Integer> a, int key) {
        a.add(key);
        siftUp(a, a.size() - 1);
    }

    private static int binarySearchLeft(ArrayList<Integer> a, int key) {
        int l = 0;
        int r = a.size() - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int midEl = a.get(mid);

            if (midEl >= key)
                r = mid;
            else
                l = mid + 1;

        }
        if (a.get(l) == key) {
            return l;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("priorityqueue.in")));
        PrintWriter out = new PrintWriter("priorityqueue.out");
        ArrayList<Integer> mas = new ArrayList<>();
        String line;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        int num = 1;
        while ((line = reader.readLine()) != null) {
            String[] command = line.split(" ");
            if (command[0].equals("push")) {
                int a=Integer.parseInt(command[1]);
                insert(mas,a );
                map.put(num, a);
                count.put(a,num);
            } else if (command[0].equals("extract-min")) {
                if (mas.size() != 0) {
                    int min = extractMin(mas);
                    out.println(min);
                    map.remove(count.get(min));
                } else {
                    out.println("*");
                }
            } else if (command[0].equals("decrease-key")) {
                if (map.containsKey(Integer.parseInt(command[1]))) {
                    int val = map.get(Integer.parseInt(command[1]));
                    int ind = mas.indexOf(val);
                    if (ind >= 0) {
                        mas.set(ind, Integer.parseInt(command[2]));
                        siftUp(mas, ind);
                        map.put(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    }
                }

            }
            num++;
        }
        out.flush();
    }
}







