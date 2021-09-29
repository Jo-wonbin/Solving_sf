package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 한 정점마다 자신보다 키가 큰 정점 갯수와 작은 정점 갯수의 합이 N-1 인 경우 위치를 알 수 있다.
public class BOJ_2458_키순서 {

	static List<Integer>[] parent;
	static List<Integer>[] child;
	static int N, M;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new ArrayList[N + 1];
		child = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int small = Integer.parseInt(st.nextToken());
			int tall = Integer.parseInt(st.nextToken());

			parent[small].add(tall);
			child[tall].add(small);
		}

		int result = 0;
		for (int i = 1; i <= N; i++) {
			int temp = bfs(i);
			if (temp == N - 1)
				result++;
		}

		q.clear();

		System.out.println(result);
		br.close();
	}

	static int bfs(int x) {
		int temp = 0;

		boolean chk[] = new boolean[N + 1];
		chk[x] = true;

		q.add(x);
		while (!q.isEmpty()) {
			int now = q.poll();

			for (int i : parent[now]) {
				if (chk[i])
					continue;
				temp++;
				chk[i] = true;
				q.add(i);
			}
		}

		q.add(x);
		while (!q.isEmpty()) {
			int now = q.poll();

			for (int i : child[now]) {
				if (chk[i])
					continue;
				temp++;
				chk[i] = true;
				q.add(i);
			}
		}

		return temp;
	}
}
