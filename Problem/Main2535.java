import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2535 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static class Medal implements Comparable<Medal> {
        int country;
        int seq;
        int score;

        public Medal(int country, int seq, int score) {
            this.country = country;
            this.seq = seq;
            this.score = score;
        }

        @Override
        public int compareTo(Medal o) {
            return o.score - this.score;
        }
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        LinkedList<Medal> list = new LinkedList<>();
        int countryMax = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int countSeq = Integer.parseInt(st.nextToken());
            countryMax = countryMax < countSeq ? countSeq : countryMax;
            list.add(new Medal(
                countSeq,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            ));
        }
        int[] medalCnt = new int[countryMax + 1];
        Collections.sort(list);
        int idx = 0;
        for(Medal m : list) {
            if(medalCnt[m.country] >= 2) {
                continue;
            } else {
                idx++;
                medalCnt[m.country]++;
                bw.write(m.country + " " + m.seq);
                bw.newLine();
            }
            if(idx == 3) {
                break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}