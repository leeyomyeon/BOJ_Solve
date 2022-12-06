import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
후위 순회의 맨 오른쪽값이 트리의 루트 노드
해당 루트 노드를 중위 순회에서 O(1)로 찾기 위해 인덱스 배열인 node 사용
루트 노드의 위치를 찾으면 루트 노드 기준 좌/우로 나눠 다시 재귀함수로 들어감
*/
public class Main2263 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuffer sb = new StringBuffer();
    public static int N;
    public static int[] post, in, node;
    
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        post = new int[N + 1];
        in = new int[N + 1];
        node = new int[N + 1];
        int idx = 1;
        // inOrder 중위 순회 = 왼쪽 - 루트 - 오른쪽
        // postOrder 후위 순회 =  왼쪽 - 오른쪽 - 루트
        while(st1.hasMoreTokens()) {
            in[idx] = Integer.parseInt(st1.nextToken());
            post[idx] = Integer.parseInt(st2.nextToken());
            node[in[idx]] = idx;

            idx++;
        }
        tree(1, N, 1, N);
        // preOrder 전위 순회 = 루트 - 왼쪽 - 오른쪽
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void tree(int inStart, int inEnd, int postStart, int postEnd) throws Exception {
        if(inStart > inEnd || postStart > postEnd) {
            return;
        }

        int rootIndex = node[post[postEnd]];    // 해당 트리의 루트 노드는 포스트오더의 맨 뒤값
        int left = rootIndex - inStart; // 왼쪽길이
        sb.append(in[rootIndex]).append(" ");

        tree(inStart, rootIndex - 1, postStart, postStart + left - 1);  // 좌
        tree(rootIndex + 1, inEnd, postStart + left, postEnd - 1);  // 우

    }
}