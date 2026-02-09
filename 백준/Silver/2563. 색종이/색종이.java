
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int size = 100;
	static final int b_size = 10;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] arr = new int[size][size];
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(st.nextToken());
			int sj = Integer.parseInt(st.nextToken());
			
			for(int i = si; i < si + b_size; i++) {
				for(int j = sj; j < sj + b_size; j++) {
					arr[i][j] = 1;
				}
			}
		}
		
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(arr[i][j] == 1) {
					ans += 1;
				}
			}
		}
		
		System.out.println(ans);
	}

}
