import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Main2822 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static class Prob implements Comparable<Prob> {
        int score;
        int seq;

        public Prob(int score, int seq) {
            this.score = score;
            this.seq = seq;
        }

        @Override
        public int compareTo(Prob o) {
            return this.score - o.score;
        }
    }
    public static void main(String[] args) throws Exception {
        LinkedList<Prob> list = new LinkedList<>();
        for(int i = 1; i <= 8; i++) {
            list.add(new Prob(Integer.parseInt(br.readLine()), i));
        }

        Collections.sort(list);

        int sum = 0;
        LinkedList<Integer> seqList = new LinkedList<>();
        for(int i = 3; i <= 7; i++) {
            sum += list.get(i).score;
            seqList.add(list.get(i).seq);
        }

        Collections.sort(seqList);

        bw.write(sum + "\n");
        for(int s : seqList) {
            bw.write(s + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}