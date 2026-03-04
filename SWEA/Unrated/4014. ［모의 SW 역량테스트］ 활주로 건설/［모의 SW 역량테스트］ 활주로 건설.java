import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, X, ans;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			ans = 0;
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				if(check(i, 0, true)) ans++;
				if(check(0, i, false)) ans++;
			}
			
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	static boolean check(int r, int c, boolean isRow) {
		int[] line = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			line[i] = isRow ? map[r][i] : map[i][c];
		}
		
		for(int i = 0; i < N - 1; i++) {
			if(line[i] == line[i + 1]) continue;
			
			int diff = line[i] - line[i + 1];
			
			if(Math.abs(diff) > 1) return false;
			
			if(diff == 1) {
				for(int j = i + 1; j <= i + X; j++) {
					if(j >= N || line[j] != line[i + 1] || visited[j]) return false;
					visited[j] = true;
				}
			} else if (diff == -1) {
				for(int j = i; j > i - X; j--) {
					if(j < 0 || line[j] != line[i] || visited[j]) return false;
					visited[j] = true;
				}
			}
		}
		
		return true;
	}
}
