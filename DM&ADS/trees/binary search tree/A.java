package AlgoLab5;

import java.io.*;
import java.util.*;

public class A {

    class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        public Node(int data){
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
    }

    class Tree{
        Node root;
        public Tree(){
            root = null;
        }


        public boolean exists(int val){
            Node current = root;
            while(current!=null){
                if(current.data==val){
                    return true;
                }else if(current.data>val){
                    current = current.left;
                }else{
                    current = current.right;
                }
            }
            return false;
        }

        public Node find(int val){
            Node current = root;
            while(current!=null){
                if(current.data==val){
                    return current;
                }else if(current.data>val){
                    current = current.left;
                }else{
                    current = current.right;
                }
            }
            return null;
        }

        public void insert(Node newNode){
            if(root==null){
                root = newNode;
                return;
            }
            Node current = root;
            Node parent;
            while(true){
                parent = current;
                if(newNode.data<current.data){
                    current = current.left;
                    if(current==null){
                        newNode.parent=parent;
                        parent.left = newNode;
                        return;
                    }
                }else{
                    current = current.right;
                    if(current==null){
                        newNode.parent=parent;
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }


        void delete(int val) {
            Node node=find(val);
            Node p = node.parent ;

            if(node.left == null && node.right == null) {
                if (node==root){
                    root=null;
                    return;
                }
                if (p.left == node) {
                    p.left = null;
                }
                if (p.right == node) {
                    p.right = null;
                }
            }
            else if (node.left == null | node.right == null ) {

                if (node.left == null) {
                    if (node==root){
                        root=root.right;
                        return;
                    }
                    if (p.left == node) {
                        p.left = node.right;
                    } else {
                        p.right = node.right;
                    }
                    node.right.parent = p;
                } else {
                    if (node==root){
                        root=root.left;
                        return;
                    }
                    if (p.left == node) {
                        p.left = node.left;
                    } else {
                        p.right = node.left;
                    }
                    node.left.parent = p;
                }
            }
            else {
                Node s = nextNode(node);
                node.data = s.data;
                if (s.parent.left == s) {
                    s.parent.left = s.right;
                    if (s.right != null) {
                        s.right.parent = s.parent;
                    }
                } else {
                    s.parent.right = s.right;
                    if (s.right != null) {
                        s.right.parent = s.parent;
                    }

                }
            }
        }

        private Node getMin(Node node){
            Node a =node;
            Node current = node.left;
            while(current!=null){
                a = current;
                current = current.left;
            }

            return a;
        }

        private Node getMax(Node node){
            Node a =node;
            Node current = node.right;
            while(current!=null){
                a = current;
                current = current.right;
            }

            return a;
        }

        boolean hasNext(int val){
            return root!=null && getMax(root).data>val;
        }

        boolean hasPrev(int val){
            return root!=null && getMin(root).data<val;
        }


        Node nextNode(Node x) {
            if (x.right != null) {
                return getMin(x.right);
            }
            Node y = x.parent;
            while (y != null && x ==y.right) {
                x = y;
                y = y.parent;
            }
            return y;
        }


        Node next(int val){
            Node node=new Node(val);
            Node res;
            boolean insert=false;
            if(!exists(val)){
                insert(node);
                insert=true;
                res=nextNode(node);
            }
            else {
                res=nextNode(find(val));
            }
            if (insert){
                delete(val);
            }
            return res;

        }

        Node prevNode(Node x) {
            if (x.left != null) {
                return getMax(x.left);
            }
            Node y = x.parent;
            while (y != null && x ==y.left) {
                x = y;
                y = y.parent;
            }
            return y;
        }


        Node prev(int val){
            Node node=new Node(val);
            boolean insert=false;
            Node res;
            if(!exists(val)){
                insert(node);
                insert=true;
                res=prevNode(node);
            }
            else{
                res=prevNode(find(val));
            }

            if (insert){
                delete(val);
            }
            return res;

        }
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in2 = new FastScanner();
        Scanner in=new Scanner(System.in);
        Tree tree=new Tree();
        String st;
        while (in.hasNext()){
            st=in.next();
            switch (st){
                case "insert":{
                    int value=in.nextInt();
                    if (!tree.exists(value)) tree.insert(new Node(value));
                    break;
                }
                case "delete":{
                    int value=in.nextInt();
                    if (tree.exists(value)) tree.delete(value);
                    break;
                }
                case "exists":{
                    int value=in.nextInt();
                    if (tree.exists(value)) System.out.println("true");
                    else System.out.println("false");
                    break;
                }
                case "next":{
                    int value=in.nextInt();
                    if (!tree.hasNext(value)){
                        System.out.println("none");
                        break;
                    }
                    Node a = tree.next(value);
                    if (a==null){
                        System.out.println("none");
                    }
                    else System.out.println(a.data);
                    break;
                }
                case "prev":{
                    int value=in.nextInt();
                    if (!tree.hasPrev(value)){
                        System.out.println("none");
                        break;
                    }
                    Node a = tree.prev(value);
                    if (a==null){
                        System.out.println("none");
                    }
                    else System.out.println(a.data);
                    break;
                }
            }



        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("output.txt");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new A().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null ) {
                    return null;
                }
                if(line.equals("")){
                    return "";
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }

}