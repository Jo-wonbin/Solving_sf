package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {

	static int N, M;
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			makeSet();

			sb.append("#").append(k).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String temp = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (temp.equals("0")) {
					union(a, b);
				} else {
					if (findSet(b) == findSet(a)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if (x == parent[x])
			return x;
		else {
			parent[x] = findSet(parent[x]);
			return parent[x];
		}
	}

	static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		if (pa != pb)
			parent[pb] = pa;

	}
}
