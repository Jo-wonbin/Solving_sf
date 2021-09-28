package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465_창용마을무리의개수 {

	static int N, M, result;
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

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);

			}
			result = 0;
			int[] temp = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				findSet(i);
				if (temp[parent[i]] != 0)
					continue;
				temp[parent[i]] = 1;
			}
			for (int i = 1; i <= N; i++) {
				if (temp[i] > 0)
					result++;
			}
			sb.append("#").append(k).append(" ").append(result).append("\n");
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
