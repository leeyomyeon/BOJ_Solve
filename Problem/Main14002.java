import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
// LIS 길이와 값 구하기
public class Main14002 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] lis, arr;
    public static Point[] track;
    public static int N;
    public static class Point {
        int idx;
        int val;
        public Point(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        lis = new int[N];
        arr = new int[N];
        track = new Point[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int maxIdx = 0;
        int idx = 0;
        for(int i = 0; i < N; i++) {
            if(idx == 0) {
                lis[idx] = arr[i];
                track[i] = new Point(idx, arr[i]);
                idx++;
            } else {
                if(lis[idx - 1] < arr[i]) {
                    lis[idx] = arr[i];
                    track[i] = new Point(idx, arr[i]);
                    idx++;
                    maxIdx = i;
                } else {
                    int chgIdx = find(0, idx, arr[i]);
                    lis[chgIdx] = arr[i];
                    track[i] = new Point(chgIdx, arr[i]);
                }
            }
        }
        int tmp = idx - 1;
        Stack<Integer> stack = new Stack<>();
        for(int i = N - 1; i >= 0; i--) {
            if(tmp == track[i].idx) {
                stack.add(track[i].val);
                tmp -= 1;
            }
        }
        bw.write(stack.size() + "\n");
        while(!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static int find(int start, int end, int target) {
        int mid;
        while(start < end) {
            mid = (start + end) / 2;
            if(lis[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }


}