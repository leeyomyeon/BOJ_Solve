import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17114 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int M, N, O, P, Q, R, S, T, U, V, W;
    public static short[][][][][][][][][][][] arr;
    public static Queue<Tomato> queue;
    public static class Tomato {
        int w, v, u, t, s, r, q, p, o, n, m, cnt;
        public Tomato(int w, int v, int u, int t, int s, int r, int q, int p, int o, int n, int m, int cnt) {
            this.m = m;
            this.n = n;
            this.o = o;
            this.p = p;
            this.q = q;
            this.r = r;
            this.s = s;
            this.t = t;
            this.u = u;
            this.v = v;
            this.w = w;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        O = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new short[W][V][U][T][S][R][Q][P][O][N][M];
        queue = new LinkedList<>();
        int gT = 0; // 익은 토마토
        int ngT = 0;    // 안익은 토마토
        int cT = 0; // 토마토 개수
        for(int w = 0; w < W; w++) {
            for(int v = 0; v < V; v++) {
                for(int u = 0; u < U; u++) {
                    for(int t = 0; t < T; t++) {
                        for(int s = 0; s < S; s++) {
                            for(int r = 0; r < R; r++) {
                                for(int q = 0; q < Q; q++) {
                                    for(int p = 0; p < P; p++) {
                                        for(int o = 0; o < O; o++) {
                                            for(int n = 0; n < N; n++) {
                                                st = new StringTokenizer(br.readLine());
                                                for(int m = 0; m < M; m++) {
                                                    arr[w][v][u][t][s][r][q][p][o][n][m] = Short.parseShort(st.nextToken());
                                                    if(arr[w][v][u][t][s][r][q][p][o][n][m] == 1) {
                                                        queue.add(new Tomato(w, v, u, t, s, r, q, p, o, n, m,0));
                                                        gT++;
                                                        cT++;
                                                    } else if (arr[w][v][u][t][s][r][q][p][o][n][m] == 0) {
                                                        cT++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(gT == cT) {
            bw.write("0");
        } else {
            int res = -1;
            while(!queue.isEmpty()) {
                Tomato current = queue.poll();
                res = current.cnt;

                for(int d = 0; d < 22; d++) {
                    int nm = current.m + dM[d];
                    int nn = current.n + dN[d];
                    int no = current.o + dO[d];
                    int np = current.p + dP[d];
                    int nq = current.q + dQ[d];
                    int nr = current.r + dR[d];
                    int ns = current.s + dS[d];
                    int nt = current.t + dT[d];
                    int nu = current.u + dU[d];
                    int nv = current.v + dV[d];
                    int nw = current.w + dW[d];
                    if( nm >= 0 && nm < M &&
                        nn >= 0 && nn < N &&
                        no >= 0 && no < O &&
                        np >= 0 && np < P &&
                        nq >= 0 && nq < Q &&
                        nr >= 0 && nr < R &&
                        ns >= 0 && ns < S &&
                        nt >= 0 && nt < T &&
                        nu >= 0 && nu < U &&
                        nv >= 0 && nv < V &&
                        nw >= 0 && nw < W &&
                        arr[nw][nv][nu][nt][ns][nr][nq][np][no][nn][nm] == 0
                    ) {
                        queue.add(new Tomato(nw, nv, nu ,nt, ns, nr, nq, np, no, nn, nm, current.cnt + 1));
                        arr[nw][nv][nu][nt][ns][nr][nq][np][no][nn][nm] = 1;
                        ngT++;
                    }
                }
            }
            if(ngT + gT != cT) {
                res = -1;
            }
            bw.write(Integer.toString(res));
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dW = {-1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dV = {0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dU = {0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dT = {0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dS = {0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dR = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dQ = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] dP = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0};
    public static int[] dO = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0};
    public static int[] dN = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0};
    public static int[] dM = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1};
}