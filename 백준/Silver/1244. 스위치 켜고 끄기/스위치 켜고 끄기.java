import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] LED = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			LED[i] = Integer.parseInt(st.nextToken());
		}
		
		int k = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int target = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			if (target == 1) {
				int index = number;
				
				while (index <= n) {
					LED[index] = (LED[index] == 1 ? 0 : 1);
					index += number;
				}
			}
			else {
				int left = number;
				int right = number;

				while (left - 1 >= 1 && right + 1 <= n 
				       && LED[left - 1] == LED[right + 1]) {
				    left--;
				    right++;
				}

				for (int j = left; j <= right; j++) {
				    LED[j] = 1 - LED[j];
				}
			}
		}
		
		int c = 0;
		
		for (int i = 1; i <= n; i++) {
			System.out.print(LED[i] + " ");
			c++;
			
			if (c == 20) {
				System.out.println();
				c = 0;
			}
		}
	}
}
