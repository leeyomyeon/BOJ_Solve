import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
// Union Find
public class Main4803 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static StringBuffer sb;
    public static void main(String[] args) throws Exception {
        int tc = 1;
        sb = new StringBuffer();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N + 1];
            if(N == 0 && M == 0) {
                break;
            }
            for(int i = 1; i <= N; i++) {
                // 자기 자신만을 집합의 원소로 가지고 있게끔 초기화
                arr[i] = i;
            }
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                union(start, end);
            }
            HashSet<Integer> set = new HashSet<>();
            for(int i = 1; i <= N; i++) { // 사이클 판단하기
                int target = find(i);
                if(target > 0) {
                    set.add(target);
                }
            }
            sb.append("Case ").append(tc).append(": ");
            if(set.size() == 0) {
                sb.append("No trees.\n");
            } else if(set.size() == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(set.size()).append(" trees.\n");
            }
            tc++;
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x == y) {    // 사이클이 형성될경우 만들어진 그래프를 0으로 변경
            arr[x] = 0;
        } else if(x < y) {
            arr[y] = x;
        } else {
            arr[x] = y;
        }
    }

    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
    }
}