package BOJ.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {

		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
//			return this.weight - o.weight; // 간선의 부호가 모두 같을때
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int[] parents; // 부모원소를 관리(트리처럼 사용)

	private static void make() {
		parents = new int[V + 1];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	// a가 속한 집합의 대표자 찾기
	private static int find(int a) {
		if (a == parents[a])
			return a; // 자신이 대표자.
		return parents[a] = find(parents[a]); // 자신이 속합 집합의 대표자를 자신의 부모로 : path compression
	}

	// 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false; // 이미 같은 집합으로 합치지 않음

		parents[bRoot] = aRoot;
		return true;
	}

	static int V;
	static ArrayList<Edge> edgeList = new ArrayList<>();;
	static int N, M, result;
	static int map[][];
	static int island[][];
	static int D[][];
	static Queue<Point> q = new LinkedList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		island = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && island[i][j] == 0) {
					island[i][j] = cnt;
					findIsland(i, j, cnt);
					cnt++;
				}
			}
		}
		D = new int[cnt][cnt];
		V = cnt - 1;

		System.out.println("--섬--");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(island[i][j] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (island[i][j] > 0)
					findEdge(i, j);
			}
		}

		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if (i < j && D[i][j] != 0) {
					edgeList.add(new Edge(i, j, D[i][j]));
				}
			}
		}

		System.out.println("---간선---");
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				System.out.print(D[i][j] + " ");
			}
			System.out.println();
		}

		Collections.sort(edgeList);

		make(); // 모든 정점을 각각으로 집합으로 만들고 출발

		// 간선 하나씩 시도하며 트리 만들어 감.
		int count = 0;
		result = 0;
		boolean flag = false;
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) {
				result += edge.weight;
				if (++count == V - 1) {
					flag = true;
					break; // 신장트리 완성
				}
			}
		}

		System.out.println(Arrays.toString(parents));
		
		int ct = 0;
		for (int i = 1; i <= V; i++) {
			if (parents[i] == i) {
				ct++;
			}
		}
		if (ct > 1)
			flag = false;

		if (flag)
			System.out.println(result);
		else
			System.out.println(-1);
		br.close();
	}

	static void findEdge(int x, int y) {

		for (int h = 0; h < 4; h++) {
			int nx = x + dx[h];
			int ny = y + dy[h];

			int cnt = 0;
			boolean flag = false;
			while (true) {

				// 범위 벗어나면 아웃
				if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
					break;
				// 자기 섬을 간 경우 아웃
				if (island[nx][ny] == island[x][y])
					break;
				// 다른 섬에 도착하면 브레이크
				if (island[nx][ny] != island[x][y] && island[nx][ny] != 0) {
					flag = true;
					break;
				}

				nx += dx[h];
				ny += dy[h];

				cnt++;
			}
			if (flag && cnt >= 2) {
				if (D[island[x][y]][island[nx][ny]] == 0)
					D[island[x][y]][island[nx][ny]] = cnt;
				else
					D[island[x][y]][island[nx][ny]] = Math.min(D[island[x][y]][island[nx][ny]], cnt);
			}
		}

	}

	// 같은 섬이면 같은 값을 가지게 함.
	static void findIsland(int x, int y, int cnt) {
		q.add(new Point(x, y));

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
					continue;
				if (map[nx][ny] == 0)
					continue;
				if (island[nx][ny] > 0)
					continue;

				island[nx][ny] = cnt;
				q.add(new Point(nx, ny));
			}
		}
	}
}
