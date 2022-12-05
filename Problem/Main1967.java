import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1967 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, maxTo, maxW;
    public static ArrayList<Node>[] adjList;
    public static boolean[] visited;
    public static class Node {
        int next;  // 누구랑 연결되어있는지
        int w;      // 가중치

        public Node (int next, int w) {
            this.next = next;
            this.w = w;
        }
    }
    
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }

        maxTo = 0;
        
        maxW = Integer.MIN_VALUE;
        dfs(1, 0);  // 어디까지 제일 먼지 체크해서 값 기억
        visited = new boolean[N + 1];

        maxW = Integer.MIN_VALUE;
        dfs(maxTo, 0);
        bw.write(maxW + "");
        
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int from, int dist) {
        if(visited[from]) {
            return;
        }
        if(maxW < dist) {
            maxW = dist;
            maxTo = from;
        }
        visited[from] = true;
        for(int i = 0; i < adjList[from].size(); i++) {
            Node nextNode = adjList[from].get(i);
            int next = nextNode.next;
            int w = nextNode.w;
            dfs(next, dist + w);
        }
    }
}