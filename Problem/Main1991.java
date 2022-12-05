import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1991 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Node[] arr;
    public static class Node {
        char left;
        char right;
        public Node(char left , char right) {
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        arr = new Node[26];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = st.nextToken().charAt(0) - 'A';
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            arr[from] = new Node(left, right);
        }
        preOrder('A');
        bw.newLine();
        inOrder('A');
        bw.newLine();
        postOrder('A');
        bw.newLine();
        br.close();
        bw.flush();
        bw.close();
    }

    public static void preOrder(char idx) throws Exception {
        if(idx == '.') {
            return;
        }
        bw.write(Character.toString(idx));
        preOrder(arr[idx - 'A'].left);
        preOrder(arr[idx - 'A'].right);
    }
    public static void inOrder(char idx) throws Exception {
        if(idx == '.') {
            return;
        }
        inOrder(arr[idx - 'A'].left);
        bw.write(Character.toString(idx));
        inOrder(arr[idx - 'A'].right);
    }
    public static void postOrder(char idx) throws Exception {
        if(idx == '.') {
            return;
        }
        postOrder(arr[idx - 'A'].left);
        postOrder(arr[idx - 'A'].right);
        bw.write(Character.toString(idx));
    }
}