import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main10814 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static class User implements Comparable<User> {
        int age;
        String name;
        int seq;

        public User(int age, String name, int seq) {
            this.age = age;
            this.name = name;
            this.seq = seq;
        }

        @Override
        public int compareTo(User o) {
            if(this.age == o.age) {
                return this.seq - o.seq;
            }
            return this.age - o.age;
        }
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        ArrayList<User> list = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new User(Integer.parseInt(st.nextToken()), st.nextToken(), i));
        }
        Collections.sort(list);

        for(User u : list) {
            bw.write(u.age+" "+u.name);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}