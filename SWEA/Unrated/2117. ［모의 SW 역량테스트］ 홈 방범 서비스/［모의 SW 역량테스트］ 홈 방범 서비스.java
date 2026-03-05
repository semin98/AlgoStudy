import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, ans;
	static List<int[]> homes;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			homes = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						homes.add(new int[] {i, j});
					}
				}
			}
			
			ans = 0;
			solve();
			System.out.println("#" + t + " " + ans);
			
		}
		
	}
	
	static void solve() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				for(int k = 1; k <= N + 1; k++) {
					int cnt = 0;
					
					for(int[] home : homes) {
						int dist = Math.abs(i - home[0]) + Math.abs(j - home[1]);
						if(dist < k) {
							cnt++;
						}
					}
					
					int cost = k * k + (k - 1) * (k - 1);
					int income = cnt * M;
					
					if(income >= cost) {
						ans = Math.max(ans,  cnt);
					}
				}
			}
		}
	}
}
