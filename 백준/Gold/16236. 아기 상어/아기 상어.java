
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, ans, r, c;
    static int shark, cnt;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {-1, 0, 1, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        shark = 2;
        ans = 0;
        cnt = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    r = i;
                    c = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {

            Result res = bfs(r, c);

            if(res.lst.isEmpty()) break;

            Collections.sort(res.lst, (o1, o2) -> {
                if(o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            });

            int[] target = res.lst.get(0);
            int targetR = target[0];
            int targetC = target[1];

            ans += res.dist;

            r = targetR;
            c = targetC;

            map[r][c] = 0;
            cnt++;
            if(cnt == shark) {
                shark++;
                cnt = 0;
            }
        }

        System.out.println(ans);
    }

    static class Result {
        List<int[]> lst;
        int dist;
        Result(List<int[]> lst, int dist) {
            this.lst = lst;
            this.dist = dist;
        }
    }

    static Result bfs(int si, int sj) {

        Queue<int[]> dq = new ArrayDeque<>();
        int[][] v = new int[N][N];
        List<int[]> lst = new ArrayList<>();

        dq.offer(new int[] {si, sj});
        v[si][sj] = 1;
        int minDistance = 0;

        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int ci = cur[0];
            int cj = cur[1];

            if(minDistance > 0 && v[ci][cj] == minDistance) {
                return new Result(lst, minDistance - 1);
            }

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj) && map[ni][nj] <= shark && v[ni][nj] == 0) {
                    v[ni][nj] = v[ci][cj] + 1;
                    dq.offer(new int[] {ni, nj});

                    if(map[ni][nj] > 0 && map[ni][nj] < shark) {
                        lst.add(new int[] {ni, nj});
                        minDistance = v[ni][nj];
                    }
                }
            }
        }
        return new Result(lst, minDistance - 1);
    }

    static boolean detect() {

        for(int i = 0; i < N; i ++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] < shark) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isIn(int si, int sj) {
        if(0 <= si && si < N && 0 <= sj && sj < N) return true;
        return false;
    }
}
