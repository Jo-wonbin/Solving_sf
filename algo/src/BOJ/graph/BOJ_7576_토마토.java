package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static Queue<Point> q = new LinkedList<>();
	static int N, M;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.add(new Point(i, j));
				}
			}
		}
		bfs();
		int max = Integer.MIN_VALUE;
		boolean flag = false;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					flag = true;
					break;
				}
				max = Math.max(max, map[i][j]);
			}
		}
		if (flag) {
			System.out.println(-1);
		} else {
			System.out.println(max - 1);
		}
		br.close();
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > M - 1 || ny > N - 1)
					continue;
				if (map[nx][ny] > 0)
					continue;
				if (map[nx][ny] == -1)
					continue;

				map[nx][ny] = map[now.x][now.y] + 1;
				q.add(new Point(nx, ny));
			}
		}
	}
}
