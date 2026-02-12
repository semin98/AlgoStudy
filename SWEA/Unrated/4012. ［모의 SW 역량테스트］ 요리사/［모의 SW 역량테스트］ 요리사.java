
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, ans;
	static int map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException  {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean[] gogo = new boolean[N];
			getComb(0, 0, gogo);
			System.out.println("#" + test_case + " " + ans);
		}
	}
	
	static void getComb(int idx, int cnt, boolean[] chk) {
	
		if(idx == N) return;
		
		if(cnt == (N / 2)) {
			int result = cal(chk);

			ans = Math.min(ans,  result);
			return;
		}
		
		
		chk[idx] = true;
		getComb(idx + 1, cnt + 1, chk);
		
		chk[idx] = false;
		getComb(idx + 1, cnt, chk);
	}
	
	static int cal(boolean[] chk) {
		int result1 = 0;
		int result2 = 0;
		
		for(int i = 0; i < chk.length; i++) {
			for(int j = 0; j < chk.length; j++) {
				if(i == j) continue;
				
				if(chk[i] && chk[j]) {
					result1 += map[i][j];
				} else if (!chk[i] && !chk[j]) {
					result2 += map[i][j];
				}
			}
		}
		return Math.abs(result1 - result2);
	}
}