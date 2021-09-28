package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1123_사칙연산유효성검사_트리 {

	static class Node {
		String data;
		int L, R;

		public Node(String data, int l, int r) {
			this.data = data;
			this.L = l;
			this.R = r;
		}

	}
	// 중위순회
//	static String inorder(Node n) {
//		if(n!=null) {
//			inorder(tree[n.L]);
//			sb.append(n,data);
//			inorder(tree[n.R]);
//		}
//		return sb.toString();
//	}

	static Node[] tree;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int k = 1; k <= 10; k++) {
			int N = Integer.parseInt(br.readLine());

			result = 1;
			tree = new Node[N + 1];
			for (int i = 1; i <= N; i++) {
				String[] arr = br.readLine().split(" ");
				if (N % 2 == 1) {
					if (i < N / 2)
						tree[i] = new Node(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
					else
						tree[i] = new Node(arr[1], 0, 0);
				}
			}
			if (N % 2 == 0) {
				result = 0;
			} else
				check(1);
			sb.append("#").append(k).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void check(int n) {
		if (tree[n].L == 0 && tree[n].R == 0) {
			if (tree[n].data.equals("*") || tree[n].data.equals("-") || tree[n].data.equals("+")
					|| tree[n].data.equals("/")) {
				result = 0;
			}
			return;
		} else {
			if (tree[n].data.equals("*") || tree[n].data.equals("-") || tree[n].data.equals("+")
					|| tree[n].data.equals("/")) {
				check(tree[n].L);
				check(tree[n].R);
			} else {
				result = 0;
				return;
			}

		}

	}
}
