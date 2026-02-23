import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		HashMap<String, String> map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			String key = st.nextToken();
			String value = st.nextToken();
			
			map.put(key, value);
		}
		
		PriorityQueue<String> pq = new PriorityQueue<String>((a, b) -> b.compareTo(a));
		
		for (HashMap.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue().equals("enter")) {
				pq.offer(entry.getKey());
			}
		}
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		System.out.println(sb);
	}
}
