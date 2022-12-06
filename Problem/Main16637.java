import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main16637 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024*8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024*8);
    public static boolean[] isSelected;
    public static int N, max;
    public static char[] arr;
    public static void main(String[] args) throws Exception {
        max = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        if(N == 1) {
            bw.write(br.readLine());
        } else {
            isSelected = new boolean[N / 2];
            arr = br.readLine().toCharArray();
            subset(0, false);
            bw.write(Integer.toString(max));
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void subset(int cnt, boolean before) {
        if(cnt == N / 2) {
            getSum();
            return;
        }
        if (!before) {  // 연속으로 선택할 수 없음
            isSelected[cnt] = true;
            subset(cnt + 1, true);
        }
        isSelected[cnt] = false;
        subset(cnt + 1, false);
    }
    public static void getSum() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Character> operation = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            if(arr[i] >= '0' && arr[i] <= '9') {
                list.add(arr[i] - '0');
            } else {
                operation.add(arr[i]);
            }
        }
        int k = 0;
        for(int i = 0; i < operation.size(); i++) {
            if(isSelected[k]) {
                char o = operation.get(i);
                int l = list.get(i);
                int r = list.get(i + 1);
                int sum = 0;
                if(o == '+') {
                    sum = l + r;
                } else if (o == '-') {
                    sum = l - r;
                } else if (o == '*') {
                    sum = l * r;
                }
                list.set(i, sum);
                list.remove(i + 1);
                operation.remove(i);
                i--;
            }
            k++;
        }
        int sum = operation.size() == 0 ? list.get(0) : 0;
        for(int i = 0; i < operation.size(); i++) {
            char o = operation.get(i);
            int l = list.get(i);
            int r = list.get(i + 1);
            if(o == '+') {
                sum = l + r;
            } else if (o == '-') {
                sum = l - r;
            } else if (o == '*') {
                sum = l * r;
            }
            list.set(i, sum);
            list.remove(i + 1);
            operation.remove(i);
            i--;
        }
        max = Math.max(max, sum);
    }
}