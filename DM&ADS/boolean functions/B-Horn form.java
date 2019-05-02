package DiskrLab1;

import java.io.*;
import java.util.*;

public class B {
    static boolean isAllNotSingle(int[] mas){
        for (int i = 0; i <mas.length ; i++) {
            if (mas[i]!=-1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        int k = reader.nextInt();
        ArrayList<int[]> mas = new ArrayList<>();
        int[] literals = new int[n];
        Arrays.fill(literals,-1);
        for (int i = 0; i < k; i++) {
            int[] s = new int[n];
            for (int j = 0; j < n; j++) {
                s[j]=reader.nextInt();
            }
            mas.add(s);
        }



        while (true) {
            int[] arr=new int[k];
            Arrays.fill(arr,-1);
            //ищем одиночные литералы
            for (int i = 0; i < mas.size(); i++) {
                int flag=1;
                for (int j = 0; j <n ; j++) {
                    if (mas.get(i)[j]!=-1){
                        flag=0;
                    }
                }
                if (flag==1){
                    System.out.print("YES");
                    return;
                }
            }
            if (mas.size()==0){
                System.out.print("NO");
                return;
            }
            for (int i = 0 ; i < mas.size(); i++) {
                int plus = 0;
                int neg=0;
                boolean flag=false;
                for (int j = 0; j < mas.get(i).length; j++) {
                    if (mas.get(i)[j]==1){
                        plus += 1;
                    }
                    else if( mas.get(i)[j]==0){
                        neg += 1;
                    }
                }
                if(plus==1 && neg==0) {
                    if (arr[i]==0){
                        System.out.print("YES");
                        return;
                    }
                    else {
                        arr[i] = 1;
                    }

                }
                else if (plus==0 && neg==1) {
                    if (arr[i]==1){
                        System.out.print("YES");
                        return;
                    }
                    else {
                        arr[i] = 0;
                    }

                }
            }
            if (isAllNotSingle(arr)){
                System.out.print("NO");
                return;
            }
            for (int i = 0; i <mas.size() ; i++) {
                if (arr[i]==1){
                    for (int j = 0; j <n ; j++) {
                        if (mas.get(i)[j]==1){
                            literals[j]=1;
                        }
                    }
                }
                else if(arr[i]==0){
                    for (int j = 0; j <n ; j++) {
                        if (mas.get(i)[j]==0){
                            literals[j]=0;
                        }
                    }
                }
            }
            int[] del=new int[mas.size()];
            for (int i = 0; i <mas.size() ; i++) {
                for (int j = 0; j <n ; j++) {
                    if (literals[j]==1 && mas.get(i)[j]==1){
                        del[i]=1;
                    }
                    if (literals[j]==1 && mas.get(i)[j]==0){
                        mas.get(i)[j]=-1;
                    }
                    if (literals[j]==0 && mas.get(i)[j]==1){
                        mas.get(i)[j]=-1;
                    }
                    if (literals[j]==0 && mas.get(i)[j]==0){
                        del[i]=1;
                    }

                }
            }

            ArrayList<int[]> masCop=new ArrayList<>();
            for (int i = 0; i <del.length ; i++) {
                if (del[i]!=1){
                    masCop.add(mas.get(i));
                }
            }
            mas=new ArrayList<>();
            for (int i = 0; i <masCop.size() ; i++) {
                mas.add(masCop.get(i));
            }
        }
    }

}

