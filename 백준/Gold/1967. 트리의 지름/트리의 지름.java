import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int to, val;
        
        Edge(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }
    
    static List<Edge>[] adj;
    static int maxDia = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        if(n == 1) {
            System.out.println(0);
            return;
        }
        
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            adj[parent].add(new Edge(child, value));
        }
        
        dfs(1);

        System.out.println(maxDia);
    }
    
    static int dfs(int cur) {
        int firstMax = 0;
        int secondMax = 0;
        
        for(Edge next : adj[cur]) {
            int len = dfs(next.to) + next.val;
            
            if(len > firstMax) {
                secondMax = firstMax;
                firstMax = len;
            } else if (len > secondMax) {
                secondMax = len;
            }
        }
        
        maxDia = Math.max(maxDia, firstMax + secondMax);
        
        return firstMax;
    }
}
