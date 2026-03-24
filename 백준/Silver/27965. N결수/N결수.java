import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static long tmp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		tmp = 0;
		
		for(int i = 1; i <= N; i++) {
			tmp = Long.parseLong(tmp + String.valueOf(i));
			if(tmp >= K) {
				divide();
			} 
		}
		System.out.println(tmp);
	}
	
	static void divide( ) {
		tmp = tmp % K;
	}
}
