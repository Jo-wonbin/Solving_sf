package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS {

	static ArrayList<Integer>[] al;
	static boolean chk[];
	static int N, M, start;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		al = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			al[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			al[a].add(b);
			al[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(al[i]);
		}

		chk = new boolean[N + 1];
		sb.append(start).append(" ");
		chk[start] = true;
		dfs(0, start);

		sb.setLength(sb.length() - 1);
		sb.append("\n").append(start).append(" ");

		chk = new boolean[N + 1];
		chk[start] = true;
		bfs();
		sb.setLength(sb.length() - 1);

		System.out.println(sb);

		br.close();
	}

	static void dfs(int cnt, int temp) {
		if (cnt == N) {
			return;
		}
		for (int i = 0; i < al[temp].size(); i++) {
			if (chk[al[temp].get(i)])
				continue;
			chk[al[temp].get(i)] = true;
			sb.append(al[temp].get(i)).append(" ");
			dfs(cnt + 1, al[temp].get(i));
		}
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {

			int temp = q.poll();
			for (int i = 0; i < al[temp].size(); i++) {
				if (chk[al[temp].get(i)])
					continue;
				chk[al[temp].get(i)] = true;
				sb.append(al[temp].get(i)).append(" ");
				q.add(al[temp].get(i));
			}
		}
	}

}
