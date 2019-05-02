package DiskrLab1;

import java.io.*;
import java.util.*;

public class E {

    public static void main(String[] args) throws IOException {
        Scanner reader=new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n=reader.nextInt();
        ArrayList<ArrayList<Integer>> elements=new ArrayList<>();
        ArrayList<ArrayList<Integer>> codes=new ArrayList<>();
        ArrayList<ArrayList<Integer>> scheme=new ArrayList<>();
        ArrayList<Integer> currLevel=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            int a=reader.nextInt();
            if (a==0){
                ArrayList<Integer> b=new ArrayList<>();
                b.add(0);
                elements.add(b);
            }
            else {
                ArrayList<Integer> b=new ArrayList<>();
                for (int j = 0; j <a; j++) {
                    b.add(reader.nextInt());
                }
                elements.add(b);
                b=new ArrayList<>();
                for (int j = 0; j <(int)Math.pow(2,a) ; j++) {
                    b.add(reader.nextInt());
                }
                codes.add(b);
            }

        }


        ArrayList<ArrayList<Integer>> el =new ArrayList<>();
        for (int i = 0; i <elements.size() ; i++) {
            ArrayList<Integer> c=new ArrayList<>();
            for (int j = 0; j <elements.get(i).size() ; j++) {
                c.add(elements.get(i).get(j));
            }
            el.add(c);
        }
        for (int i = 0; i <el.size(); i++) {
            if (elements.get(i).get(0)==0){
                currLevel.add(i);
            }
        }
        scheme.add(currLevel);
        currLevel=new ArrayList<>();

        for (int i = 1; i <27 ; i++) {
            scheme.add(new ArrayList<>());
            for (int j = 0; j < elements.size(); j++) {
                if (scheme.get(i-1).contains(j)){
                    continue;
                }
                for (int k = 0; k < scheme.get(i-1).size(); k++) {

                    if (elements.get(j).contains(scheme.get(i-1).get(k))){
                        scheme.get(i).add(j);
                        break;
                    }
                }
            }
            if (scheme.get(i).size()==0){
                scheme.remove(i);
                break;
            }

        }
        for (ArrayList<Integer> x:scheme
             ) {
            for (int y:x
                 ) {
                System.out.print(y+" ");

            }
            System.out.println();

        }


        }

}
