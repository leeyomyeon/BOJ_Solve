import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main8979 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static class Medal implements Comparable<Medal> {
        int num;
        int gold;
        int silver;
        int bronze;
        int seq;
        public Medal(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        @Override
        public int compareTo(Medal o) {
            if(this.gold == o.gold) {
                if(this.silver == o.silver) {
                    return o.bronze - this.bronze;
                } else {
                    return o.silver - this.silver;
                }
            } else {
                return o.gold - this.gold;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Medal> list = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            list.add(new Medal(num, gold, silver, bronze));
        }

        Collections.sort(list);
        Medal cur = list.get(0);
        cur.setSeq(1);
        list.set(0, cur);
        int eq = 1;
        for(int i = 1; i < list.size(); i++) {
            Medal pre = list.get(i - 1);
            cur = list.get(i);
            if(isEquals(pre, cur)) {
                cur.setSeq(pre.seq);
                eq++;
            } else {
                cur.setSeq(pre.seq + eq);
                eq = 1;
            }
            list.set(i, cur);
        }
        for(Medal m : list) {
            if(m.num == K) {
                bw.write(Integer.toString(m.seq));
                break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isEquals(Medal m1, Medal m2) {
        if(m1.gold == m2.gold && m1.silver == m2.silver && m1.bronze == m2.bronze) {
            return true;
        } else {
            return false;
        }
    }
}