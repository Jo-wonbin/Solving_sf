package BOJ.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {

	static int[] parents;

	private static void make() {
		parents = new int[V + 1];
		for (int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b, int flag) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (flag == 0) {
			parents[bRoot] = aRoot;
		} else {
			if (aRoot == bRoot) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
	}

	static int V;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		make();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int flag = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b, flag);
		}

		System.out.println(sb);
		br.close();
	}

}
