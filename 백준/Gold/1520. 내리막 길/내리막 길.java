import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map, dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		
		System.out.println(dfs(0, 0));
		  
	}
	
	static int dfs(int si, int sj) {
		
		if(si == N - 1 && sj == M - 1) {
			return 1;
		}
		
		if(dp[si][sj] != -1) {
			return dp[si][sj];
		}
		
		dp[si][sj] = 0;
		
		for(int d = 0; d < 4; d++) {
			int ni = si + di[d];
			int nj = sj + dj[d];
			
			if(isIn(ni, nj)) {
				if(map[ni][nj] < map[si][sj]) {
					dp[si][sj] += dfs(ni, nj);
				}
			}
		}
		
		return dp[si][sj];
	}
	
	static boolean isIn(int ci, int cj) {
		return 0 <= ci && ci < N && 0 <= cj && cj < M;
	}
}
