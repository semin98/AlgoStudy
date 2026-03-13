import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static boolean[][] visited;
	static char[][] map;
	static Queue<int[]> syQ, dQ;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			visited = new boolean[N][M];
			syQ = new ArrayDeque<>();
			dQ = new ArrayDeque<>();
			
			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 'S') {
						syQ.offer(new int[] {i, j});
						visited[i][j] = true;
					}
					if(map[i][j] == '*') dQ.offer(new int[] {i, j});
				}
			}
			
			int ans = bfs();
			String fans = (ans == -1) ? "GAME OVER" : String.valueOf(ans);
			System.out.println("#" + t + " " + fans);
			
		}
		
	}
	
	static int bfs() {
		int time = 0;
		
		while(!syQ.isEmpty()) {
			time++;
			
			int dSize = dQ.size();
			for(int i = 0; i < dSize; i++) {
				int[] cur = dQ.poll();
			
				for(int d = 0; d < 4; d++) {
					int ni = cur[0] + di[d];
					int nj = cur[1] + dj[d];
					
					if(isIn(ni, nj) && (map[ni][nj] == '.' || map[ni][nj] == 'S')) {
						map[ni][nj] = '*';
						dQ.offer(new int[] {ni, nj});
					}
				}
			}
			
			int sSize = syQ.size();
			for(int i = 0; i < sSize; i++) {
				int[] cur = syQ.poll();
				
				for(int d = 0; d < 4; d++) {
					int ni = cur[0] + di[d];
					int nj = cur[1] + dj[d];
					
					if(isIn(ni, nj)) {
						if(map[ni][nj] == 'D') return time;
						
						if(!visited[ni][nj] && map[ni][nj] == '.') {
							visited[ni][nj] = true;
							syQ.offer(new int[] {ni, nj});
						}
					}
				}
			}
		}
		
		return -1;
	}
	
	
	static boolean isIn(int ci, int cj) {
		return 0 <= ci && ci < N && 0 <= cj && cj < M;
	}
}
