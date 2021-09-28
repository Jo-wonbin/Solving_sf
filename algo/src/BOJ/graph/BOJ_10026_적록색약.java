package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static char map[][];
	static boolean[][] normal;
	static boolean[][] abnormal;
	static Queue<Point> q = new LinkedList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		normal = new boolean[N][N];
		abnormal = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			map[i] = a.toCharArray();
		}

		int normalCount = 0;
		int abnormalCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!normal[i][j]) {
					normal[i][j] = true;
					normalCount++;
					q.add(new Point(i, j));
					bfs(map[i][j], true);
				}
				if (!abnormal[i][j]) {
					abnormal[i][j] = true;
					abnormalCount++;
					q.add(new Point(i, j));
					bfs(map[i][j], false);
				}
			}
		}
		sb.append(normalCount).append(" ").append(abnormalCount);
		System.out.println(sb);

		br.close();
	}

	private static void bfs(char Alpa, boolean flag) {
		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					continue;

				if (flag) {
					if (normal[nx][ny])
						continue;
					if (map[nx][ny] != Alpa)
						continue;
					normal[nx][ny] = true;
					q.add(new Point(nx, ny));
				} else {
					if (abnormal[nx][ny])
						continue;
					if (Alpa == 'R' || Alpa == 'G') {
						if (map[nx][ny] == 'B') {
							continue;
						}
					} else {
						if (map[nx][ny] != Alpa)
							continue;
					}
					abnormal[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}
	}

}
