import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int W, H;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static Queue<int[]> fq, sq;
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			visited = new boolean[H][W];
			
			fq = new ArrayDeque<>();
			sq = new ArrayDeque<>();
			
			for(int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < W; j++) {
					if(map[i][j] == '*') fq.offer(new int[] {i, j});
					if(map[i][j] == '@') sq.offer(new int[] {i, j, 0});
				}
			}
			
			int ans = solve();
			System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
		
		}
	}
	
	static int solve() {
	    while (!sq.isEmpty()) {
	        int fSize = fq.size();
	        for (int i = 0; i < fSize; i++) {
	            int[] cur = fq.poll();
	            for (int d = 0; d < 4; d++) {
	                int ni = cur[0] + di[d];
	                int nj = cur[1] + dj[d];
	                if (isIn(ni, nj) && (map[ni][nj] == '.' || map[ni][nj] == '@')) {
	                    map[ni][nj] = '*'; 
	                    fq.offer(new int[]{ni, nj});
	                }
	            }
	        }

	        int sSize = sq.size();
	        for (int i = 0; i < sSize; i++) {
	            int[] cur = sq.poll();
	            for (int d = 0; d < 4; d++) {
	                int ni = cur[0] + di[d];
	                int nj = cur[1] + dj[d];

	                if (!isIn(ni, nj)) return cur[2] + 1;

	                if (map[ni][nj] == '.' && !visited[ni][nj]) {
	                    visited[ni][nj] = true;
	                    sq.offer(new int[]{ni, nj, cur[2] + 1});
	                }
	            }
	        }
	    }
	    return -1;
	}
	
	static boolean isIn(int ci, int cj) {
		return 0 <= ci && ci < H && 0 <= cj && cj < W;
	}
	
}
