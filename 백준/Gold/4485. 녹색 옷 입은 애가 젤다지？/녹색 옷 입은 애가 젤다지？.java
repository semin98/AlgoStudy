import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cnt = 1;
		
		while(true) {
			
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) return;
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("Problem " + cnt++ + ": " + solve());
		}
		
	}
	
	static int solve() {
		int[][] loss = new int[N][N];
		for(int i = 0; i < N; i++) Arrays.fill(loss[i], Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		pq.offer(new int[] {0, 0, map[0][0]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int ci = cur[0];
			int cj = cur[1];
			int cost = cur[2];
			
			if(ci == N - 1 && cj == N - 1) return loss[ci][cj];
			
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				if(isIn(ni, nj)) {
					if(loss[ni][nj] > cost + map[ni][nj]) {
						loss[ni][nj] = cost + map[ni][nj];
						pq.offer(new int[] {ni, nj, loss[ni][nj]});
					}
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int ci, int cj) {
		return 0 <= ci && ci < N && 0 <= cj && cj < N;
	}
	
}
