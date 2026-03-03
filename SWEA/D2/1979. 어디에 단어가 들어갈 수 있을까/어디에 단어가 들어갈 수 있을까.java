import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	 
	static int N, K, ans;
	static int[][] map;
	
	 public static void main(String[] args) throws IOException {
		 
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int T = Integer.parseInt(br.readLine());
		 
		 for(int t = 1; t <= T; t++) {
		 
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 
			 N = Integer.parseInt(st.nextToken());
			 K = Integer.parseInt(st.nextToken());
			 ans = 0;
			 
			 map = new int[N][N];
			 
			 for(int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for(int j = 0; j < N; j++) {
					 map[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }
			 
			 for(int i = 0; i < N; i++) {
				 solve(i);
			 }
			 
			 System.out.println("#" + t + " " + ans);
		 }
		 
	 }
	 
	 static void solve(int row) {
		 int len_r = 0;
		 int len_c = 0;
		 for(int i = 0; i < N; i++) {
			 if(map[row][i] == 1) {
				 len_r += 1;
			 } else {
				 if(len_r == K) {
					 ans += 1;
				 }
				 len_r = 0;
			 }
			 
			 if(i == N - 1) {
				 if(len_r == K) ans += 1;
			 }
			
		 }
		 
		 for(int i = 0; i < N; i++) {
			 if(map[i][row] == 1) {
				 len_c += 1;
			 } else {
				 if(len_c == K) {
					 ans += 1;
				 }
				 len_c = 0;
			 }
			 
			 if(i == N - 1) {
				 if(len_c == K) ans += 1;
			 }
		 }
	 }
}