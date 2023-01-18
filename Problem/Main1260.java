import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1260 {
    static int n, m, v;
    static int a, b;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/Algorithm/P1260/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   // 정점 수
        m = Integer.parseInt(st.nextToken());   // 간선 수
        v = Integer.parseInt(st.nextToken());   // 탐색을 시작할 정점의 번호

        // 방문 여부 검사
        isVisited = new boolean[n + 1];

        LinkedList<Integer>[] adjList = new LinkedList[n + 1];

        // n만큼
        for (int i = 0; i <= n; i++) {
            adjList[i] = new LinkedList<>();
        }

        // 간선의 개수만큼 초기화한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // 주어지는 간선이 양방향이다.
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 방문할 수 있는 정점이 여러 개인 경우 정점 번호가 작은 것부터 존재하므로 미리 정렬한다.
        for (int i = 0; i <= n; i++) {
            Collections.sort(adjList[i]);
        }
        isVisited[v] = true;
        DFS(v, adjList);

        // 초기화
        Arrays.fill(isVisited, false);
        System.out.println();

        isVisited[v] = true;
        BFS(v, adjList);
    }

    // DFS - 인접 리스트(재귀로 표시)
    public static void DFS(int v, LinkedList<Integer>[] adjList) {
        System.out.print(v + " ");

        Iterator<Integer> iter = adjList[v].listIterator(); // 정점 인접리스트 순회

        while (iter.hasNext()) {
            int w = iter.next();
            // 방문하지 않은 정점이라면
            if (!isVisited[w]) {
                isVisited[w] = true;
                DFS(w, adjList);
            }
        }
    }

    // BFS
    public static void BFS(int v, LinkedList<Integer>[] adjList) {
        Queue<Integer> queue = new LinkedList<>();
        // 방문했으니까 true로 바꾼다.
        // 정점을 넣어준다.
        queue.add(v);
        // queue 사이즈가 0이 되기 전까지
        while (queue.size() != 0) {
            v = queue.poll();
            System.out.print(v + " ");

            Iterator<Integer> iter = adjList[v].listIterator();
            while (iter.hasNext()) {
                int w = iter.next();
                if (!isVisited[w]) {
                    isVisited[w] = true;
                    queue.add(w);
                }
            }
        }
    }
}