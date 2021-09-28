package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int n;
	static int map[][];
	static Queue<Point> q = new LinkedList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			n = Integer.parseInt(br.readLine());

			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					q.add(new Point(i, j));
					int temp = bfs(i, j);
					if (max < temp) {
						max = temp;
						answer = map[i][j];
					} else if (max == temp) {
						answer = Math.min(map[i][j], answer);
					}
				}
			}
			sb.append("#").append(k).append(" ").append(answer).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	public static int bfs(int x, int y) {
		int count = 1;

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1)
					continue;
				if (map[nx][ny] != map[now.x][now.y] + 1)
					continue;

				q.add(new Point(nx, ny));
				count++;

			}
		}

		return count;
	}

}