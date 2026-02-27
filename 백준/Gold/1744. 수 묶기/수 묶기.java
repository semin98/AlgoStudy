
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        int one = 0;
        int zero = 0;
        long sum = 0; 
        
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num < 0) minus.add(num);
            else if (num == 0) zero++;
            else if (num == 1) one++;
            else plus.add(num);
        }

        Collections.sort(plus);
        Collections.sort(minus);

        int pSize = plus.size();
        for (int i = pSize - 1; i >= 0; i -= 2) {
            if (i > 0) { 
                sum += (long) plus.get(i) * plus.get(i - 1);
            } else { 
                sum += plus.get(i);
            }
        }

        int mSize = minus.size();
        for (int i = 0; i < mSize; i += 2) {
            if (i + 1 < mSize) { 
                sum += (long) minus.get(i) * minus.get(i + 1);
            } else { 
                if (zero == 0) { 
                    sum += minus.get(i);
                }
            }
        }

        sum += one; 
        System.out.println(sum);
    }
}