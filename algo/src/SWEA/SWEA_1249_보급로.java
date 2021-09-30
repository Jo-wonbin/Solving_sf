package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA_1249_보급로 {

	static class Point implements Comparable<Point> {
		int x, y, count;

		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.count, o.count);
		}
	}

	static int N, result;
	static int map[][];
	static int dis[][];
	static PriorityQueue<Point> pq = new PriorityQueue<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			dis = new int[N][N];

			for (int i = 0; i < N; i++) {
				String a = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = a.charAt(j) - '0';
					dis[i][j] = Integer.MAX_VALUE;
				}
			}

			pq.add(new Point(0, 0, map[0][0]));
			dis[0][0] = map[0][0];
			bfs();
			result = dis[N - 1][N - 1];

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	private static void bfs() {

		while (!pq.isEmpty()) {
			Point now = pq.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					continue;
				if (dis[nx][ny] > dis[now.x][now.y] + map[nx][ny]) {
					dis[nx][ny] = dis[now.x][now.y] + map[nx][ny];
					pq.add(new Point(nx, ny, dis[nx][ny]));
				}
			}
		}
	}
}
