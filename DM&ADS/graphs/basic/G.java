package AlgoLab7;


import java.io.*;
import java.util.*;

public class G {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graphRev = new ArrayList<>();
    static ArrayList<Boolean> used = new ArrayList<>();
    static ArrayList<Integer> mas = new ArrayList<>();
    static ArrayList<Integer> comp = new ArrayList<>();
    static Map<String, Integer> nameToNum = new HashMap<>();
    static Map<Integer,String> numToNames = new HashMap<>();


    static void dfs1(int v) {
        used.set(v, true);
        for (int i = 0; i < graph.get(v).size(); i++) {
            int next = graph.get(v).get(i);
            if (!used.get(next))
                dfs1(next);
        }
        mas.add(v);
    }

    static void dfs2(int v, int col) {
        comp.set(v, col);
        for (int i = 0; i < graphRev.get(v).size(); i++) {
            int next = graphRev.get(v).get(i);
            if (comp.get(next) == -1) {
                dfs2(next, col);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);

        int n = reader.nextInt();
        int m = reader.nextInt();
        for (int i = 0; i < 2 * n; i++) {
            graph.add(new ArrayList<>());
            graphRev.add(new ArrayList<>());
        }
        for (int i = 0; i < 2*n; i++) {
            String name = reader.next();
            nameToNum.put("+"+name, i);
            numToNames.put(i,name);
            i++;
            nameToNum.put("-"+name,i);
            numToNames.put(i,name);
        }

        for (int i = 0; i < m; i++) {
            String str1;
            String str2;
            str1 = reader.next();
            reader.next();
            str2 = reader.next();
            graph.get(nameToNum.get(str1)).add(nameToNum.get(str2));
            graphRev.get(nameToNum.get(str2)).add(nameToNum.get(str1));
            if(str1.charAt(0)=='+' && str2.charAt(0)=='+'){
                String b = "-"+str1.substring(1);
                String a = "-"+str2.substring(1);
                graph.get(nameToNum.get(a)).add(nameToNum.get(b));
                graphRev.get(nameToNum.get(b)).add(nameToNum.get(a));
            }
            else if(str1.charAt(0)=='-' && str2.charAt(0)=='+'){
                String b = "+"+str1.substring(1);
                String a = "-"+str2.substring(1);
                graph.get(nameToNum.get(a)).add(nameToNum.get(b));
                graphRev.get(nameToNum.get(b)).add(nameToNum.get(a));
            }
            else if(str1.charAt(0)=='+' && str2.charAt(0)=='-'){
                String b = "-"+str1.substring(1);
                String a = "+"+str2.substring(1);
                graph.get(nameToNum.get(a)).add(nameToNum.get(b));
                graphRev.get(nameToNum.get(b)).add(nameToNum.get(a));
            }
            else {
                String b = "+"+str1.substring(1);
                String a = "+"+str2.substring(1);
                graph.get(nameToNum.get(a)).add(nameToNum.get(b));
                graphRev.get(nameToNum.get(b)).add(nameToNum.get(a));
            }
        }


        for (int i = 0; i < 2 * n; i++) {
            used.add(false);
        }
        for (int i = 0; i < 2 * n; i++)
            if (!used.get(i))
                dfs1(i);

        for (int i = 0; i < 2 * n; i++) {
            comp.add(-1);
        }
        for (int i = 0, j = 0; i < 2 * n; i++) {
            int point = mas.get(2 * n - i - 1);
            if (comp.get(point) == -1)
                dfs2(point, j++);
        }

        for (int i = 0; i < 2 * n; i++) {
            if (comp.get(i).equals(comp.get(++i))) {
                System.out.print(-1);
                return;
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 2 * n; ++i) {
            if (comp.get(i) > comp.get(++i)) {
                res.add(numToNames.get(i));
            }

        }
        System.out.println(res.size());
        for (String i : res) {
            System.out.print(i + " ");

        }


    }

}