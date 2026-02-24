
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] tmp = br.readLine().toCharArray();
        int ans = 0;

        String value = "";

        Deque<Integer> q = new ArrayDeque<>();
        Deque<Character> op_q = new ArrayDeque<>();

        for(int i = 0; i < tmp.length; i++) {
            char c = tmp[i];
            if(c == '-' || c== '+') {
                q.offer(Integer.parseInt(value));
                value = "";
                op_q.offer(c);
            } else if(i == tmp.length - 1) {
                value += c;
                q.offer(Integer.parseInt(value));
            } else {
                value += c;
            }
        }

        ans = q.poll();
        char op;
        while(true) {
            if(q.isEmpty()) break;
            op = op_q.poll();
            if(op == '-') {
                ans -= q.poll();
                break;
            } else {
                ans += q.poll();
            }
        }

        while(!q.isEmpty()) {
            ans -= q.poll();
        }

        System.out.println(ans);
    }
}
