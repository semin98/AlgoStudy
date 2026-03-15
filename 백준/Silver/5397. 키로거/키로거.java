import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            char[] arr = br.readLine().toCharArray();
            Deque<Character> lq = new ArrayDeque<>();
            Deque<Character> rq = new ArrayDeque<>();

            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == '<') {
                    if(lq.isEmpty()){
                        continue;
                    } else {
                        rq.addFirst(lq.pollLast());
                    }
                } else if(arr[i] == '>') {
                    if(rq.isEmpty()) {
                        continue;
                    } else {
                        lq.addLast(rq.pollFirst());
                    }
                } else if(arr[i] == '-') {
                    if(!lq.isEmpty()) {
                        lq.pollLast();
                    }
                } else {
                    lq.add(arr[i]);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Character c : lq) {
                sb.append(c);
            }
            for (Character c : rq) {
                sb.append(c);
            }

            System.out.println(sb);

        }
    }
}