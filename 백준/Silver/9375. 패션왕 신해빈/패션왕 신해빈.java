import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			
			HashMap<String, Integer> map = new HashMap<>();
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				st.nextToken();
				String key = st.nextToken();
				
				map.put(key, map.getOrDefault(key, 0) + 1);
			}
			
			int answer = 1;
			
			for (int x : map.values()) answer *= (x + 1);
			
			sb.append(answer - 1).append("\n");
		}
		
		System.out.println(sb);
	}
}