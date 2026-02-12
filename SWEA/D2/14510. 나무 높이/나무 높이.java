
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			int even_cnt = 0;
			int odd_cnt = 0;
			int ans = 0;
			
			int max = 0;
			
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
				
			}
			
			for(int i = 0; i < N; i++) {
				int tmp = max - arr[i];
				odd_cnt += tmp % 2;
				even_cnt += tmp / 2;
			}
			
			if(odd_cnt == 0 && even_cnt == 0) ans = 0;
			else if(even_cnt > odd_cnt){
				
				while (even_cnt > odd_cnt + 1) {
					even_cnt--;
					odd_cnt += 2;
					
					//처음에 이렇게 하고 왜 50개 중 46개만 맞나 했는데 차이가 1이거나 0일때 결과가 다르게 나옵니다
//					odd_cnt += 2;
//					even_cnt -= 1;
//					
//					if(even_cnt - odd_cnt < 0) {
//						even_cnt += 1;
//						ans = even_cnt * 2;
//
//						break;
//					}
				}
				
				if (odd_cnt > even_cnt) {
					ans = odd_cnt * 2 - 1;
				} else if(even_cnt > odd_cnt) {
					ans = even_cnt * 2;
				} else {
					ans = even_cnt * 2;
				}
				

			} else if(odd_cnt > even_cnt){
				ans = odd_cnt * 2 - 1;
			} else {
				ans = even_cnt * 2;
			}
			

			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}