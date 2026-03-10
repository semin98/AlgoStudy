import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, si, sj, ei, ej, ans;
	static boolean[] chk;
	static int[] order;
	static int[][] homes;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			
			homes = new int[N][2];
			chk = new boolean[N];
			order = new int[N];
			
			int idx = 0;
			for(int i = 0; i < N + 2; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				if(i == 0) {
					si = start;
					sj = end;
				} else if(i == 1) {
					ei = start;
					ej = end;
				} else {
					homes[idx][0] = start;
					homes[idx][1] = end;
					idx++;
				}
			}
			
			permutation(0);
			
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	static void permutation(int cnt) {
		
		if(cnt == N) {
			getAns();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!chk[i]) {
				chk[i] = true;
				order[cnt] = i;
				permutation(cnt + 1);
				chk[i] = false;
			}
		}
	}
	
	static void getAns() {
		int result = 0;
		
		int start_row = si;
		int start_col = sj;
		
		for(int i = 0; i < N; i++) {
			int home = order[i];
			int ci = homes[home][0];
			int cj = homes[home][1];
			
			result += getDist(start_row, start_col, ci, cj);
			start_row = ci;
			start_col = cj;
		}
		result += getDist(start_row, start_col, ei, ej);
		
		ans = Math.min(ans, result);
	}
	
	static int getDist(int sr, int sc, int er, int ec) {
		return Math.abs(sr - er) + Math.abs(sc - ec);
	}
}
