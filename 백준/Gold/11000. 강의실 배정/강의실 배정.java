import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Lecture implements Comparable<Lecture> {
    int start;
    int end;

    Lecture(int start, int end) {
        this.start = start;
        this.end  = end;
    }

    @Override
    public int compareTo(Lecture o) {
        if(this.start == o.start) {
            return this.end - o.end;
        }

        return this.start - o.start;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0].end);
        
        for(int i = 1; i < N; i++) {
            if(pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.add(lectures[i].end);
        }

        System.out.println(pq.size());
    }

}
