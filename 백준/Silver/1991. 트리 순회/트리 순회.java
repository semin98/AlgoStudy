import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        char left, right;
        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    static Map<Character, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.put(root, new Node(left, right));
        }

        preOrder('A');
        System.out.println();
        inOrder('A');
        System.out.println();
        postOrder('A');
    }

    static void preOrder(char cur) {
        if(cur == '.') return;
        System.out.print(cur);
        preOrder(tree.get(cur).left);
        preOrder(tree.get(cur).right);
    }

    static void inOrder(char cur) {
        if(cur == '.') return;
        inOrder(tree.get(cur).left);
        System.out.print(cur);
        inOrder(tree.get(cur).right);
    }

    static void postOrder(char cur) {
        if(cur == '.') return;
        postOrder(tree.get(cur).left);
        postOrder(tree.get(cur).right);
        System.out.print(cur);
    }
}
