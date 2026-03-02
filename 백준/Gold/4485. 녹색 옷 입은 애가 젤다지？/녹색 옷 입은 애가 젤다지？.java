import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, dist;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    // 거리가 큰 값을 나타내기 위한 상수 (무한대)
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            
            N = Integer.parseInt(line);
            if (N == 0) break; // N이 0이면 종료

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF; // 거리 배열을 무한대로 초기화
                }
            }

            int result = solve();
            System.out.println("Problem " + testCase + ": " + result);
            testCase++;
        }
    }

    static int solve() {
        // PriorityQueue: {행, 열, 현재까지의 누적 도둑루피(비용)}
        // 비용(o[2])을 기준으로 오름차순 정렬하여 가장 최소 비용인 노드를 먼저 꺼냄
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        // 시작점 설정
        dist[0][0] = map[0][0];
        pq.offer(new int[]{0, 0, dist[0][0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];

            // 1. 현재 꺼낸 비용이 이미 기록된 최소 비용보다 크면 무시 (가지치기)
            if (cost > dist[r][c]) continue;

            // 2. 목적지에 도달하면 즉시 반환 (PQ 특성상 가장 먼저 도달한 것이 최솟값임)
            if (r == N - 1 && c == N - 1) return cost;

            // 3. 인접한 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + di[d];
                int nc = c + dj[d];

                if (isIn(nr, nc)) {
                    // 4. 새로운 경로를 통한 비용이 기존 기록된 최소 비용보다 작을 때만 갱신 후 PQ 삽입
                    if (dist[nr][nc] > cost + map[nr][nc]) {
                        dist[nr][nc] = cost + map[nr][nc];
                        pq.offer(new int[]{nr, nc, dist[nr][nc]});
                    }
                }
            }
        }
        return -1;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}