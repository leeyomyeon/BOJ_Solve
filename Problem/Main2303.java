import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2303 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] bitMask = {7, 11, 13, 14, 19, 21, 22, 25, 26, 28};
    public static class Game implements Comparable<Game> {
        int seq;
        int num;

        public Game(int seq, int num) {
            this.seq = seq;
            this.num = num;
        }

        @Override
        public int compareTo(Game o) {
            if(o.num == this.num) {
                return o.seq - this.seq;
            } else {
                return o.num - this.num;
            }
            
        }
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        LinkedList<Game> list = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            };

            int sumMax = Integer.MIN_VALUE;

            for(int j = 0; j < bitMask.length; j++) {
                int k = bitMask[j];
                int tmpSum = 0;
                for(int l = 0; l < 5; l++) {
                    if((k & 1 << l) > 0) {
                        tmpSum += arr[l];
                    }
                }
                sumMax = sumMax < (tmpSum % 10) ? (tmpSum % 10) : sumMax;
            }

            list.add(new Game(i, sumMax));
        }
        Collections.sort(list);

        bw.write(Integer.toString(list.get(0).seq));
        br.close();
        bw.flush();
        bw.close();
    }
}