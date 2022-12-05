import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Main1339 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static String[] arr;
    public static StringBuilder[] list;
    public static HashMap<Character, Integer> map;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        list = new StringBuilder[N];
        int maxLen = Integer.MIN_VALUE;
        map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            maxLen = Math.max(arr[i].length(), maxLen);
        }
        for(int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            if(arr[i].length() < maxLen) {
                for(int j = 0; j < maxLen - arr[i].length(); j++) {
                    sb.append("0");
                }
            }
            list[i] = new StringBuilder().append(sb).append(arr[i]);
            for(int j = 0; j < maxLen; j++) {
                char c = list[i].charAt(j);
                if(c != '0') {
                    int k = (int) Math.pow(10, maxLen - j - 1);
                    map.put(c, map.getOrDefault(c, 0) + k);
                }
            }
        }
        LinkedList<Entry<Character, Integer>> srtList = new LinkedList<>(map.entrySet());
        srtList.sort(Entry.comparingByValue((o1, o2) -> o2 - o1));
        int sum = 0;
        int num = 9;
        for (Entry<Character,Integer> entry : srtList) {
          sum += (num * entry.getValue());
          num--;
        };
        bw.write(Integer.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}