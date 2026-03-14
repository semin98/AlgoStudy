import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, K;
    static int[] d = new int[]{1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));

    }

    static int bfs(int s, int e) {
        Queue<Integer> q = new LinkedList<>();
        int[] v = new int[200001];
        q.offer(s);
        v[s] = 1;

        while(!q.isEmpty()) {
            int c = q.poll();
            int n = 0;

            if(c == e) {
                return v[e] - 1;
            }

            for(int i = 0; i < 3; i++){
                if(i == 2) {
                    n =  c * 2;
                } else {
                    n= c + d[i];
                }

                if(0 <= n && n <= 200000 && v[n] == 0){
                    q.offer(n);
                    v[n] = v[c] + 1;
                }
            }

        }

        return -1;
    }
}