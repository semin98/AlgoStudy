import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, String> map1 = new HashMap<>();
		HashMap<String, Integer> map2 = new HashMap<>();
		
		for (int i = 1; i <= n; i++) {
			String pocketmon = br.readLine();
			
			map1.put(i, pocketmon);
			map2.put(pocketmon, i);
		}
		
		for (int i = 0; i < m; i++) {
			String input = br.readLine();
			
			if (Character.isDigit(input.charAt(0))) {
			    sb.append(map1.get(Integer.parseInt(input))).append("\n");
			} else {
			    sb.append(map2.get(input)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}