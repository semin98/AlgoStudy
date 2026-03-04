import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, maxCores, minLen;
	static int[][] map;
	static List<int[]> cores;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
						cores.add(new int[] {i, j});
					}
				}
			}
			
			maxCores = 0;
			minLen = Integer.MAX_VALUE;
			
			dfs(0, 0, 0);
			
			System.out.println("#" + t + " " + minLen);
		}
		
	}
	
	static void dfs(int idx, int coreCnt, int wireLen) {
		
		if(idx == cores.size()) {
			if(coreCnt > maxCores) {
				maxCores = coreCnt;
				minLen = wireLen;
			} else if(coreCnt == maxCores) {
				minLen = Math.min(minLen, wireLen);
			}
			return;
		}
		
		int r = cores.get(idx)[0];
		int c = cores.get(idx)[1];
		
		for(int d = 0; d < 4; d++) {
			int cnt = getWireCount(r, c, d);
			if(cnt > 0) {
				fill(r, c, d, 2);
				dfs(idx + 1, coreCnt + 1, wireLen + cnt);
				fill(r, c, d, 0);
			}
		}
		
		dfs(idx + 1, coreCnt, wireLen);
	}
	
	static int getWireCount(int r, int c, int d) {
		int cnt = 0;
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			if(map[nr][nc] != 0) return -1;
			cnt++;
		}
		return cnt;
	}
	
	static void fill(int r, int c, int d, int val) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			map[nr][nc] = val;
		}
	}
}
