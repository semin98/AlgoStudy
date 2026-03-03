import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		String[][] dp = new String[n + 1][2];
		
		dp[1][0] = "0";
		dp[1][1] = "1";
		
		for (int i = 2; i <= n; i++) {
			BigInteger a = new BigInteger(dp[i - 1][0]);
			BigInteger b = new BigInteger(dp[i - 1][1]);
			
			dp[i][0] = a.add(b).toString();
			dp[i][1] = a.toString();
		}
		
		BigInteger a = new BigInteger(dp[n][0]);
		BigInteger b = new BigInteger(dp[n][1]);
		
		System.out.println(a.add(b));
	}
}
