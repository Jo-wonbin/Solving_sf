package BOJ.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색옷을입은애가젤다지_pq {

	static class Point implements Comparable<Point> {
		int x, y, count;

		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public int compareTo(Point o) {
			return this.count - o.count;
		}

	}

	static int N, result;
	static int map[][];
	static int dis[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static PriorityQueue<Point> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int cnt = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			dis = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dis[i][j] = Integer.MAX_VALUE;
				}
			}

			pq.add(new Point(0, 0, map[0][0]));
			dis[0][0] = map[0][0];
			bfs();

			sb.append("Problem ").append(cnt).append(": ").append(result).append("\n");

			cnt++;
			pq.clear();
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

				// 다음에 갈 좌표의 값이 현재 좌표의 최소값 + 다음 좌표의 값보다 크다면 최소값 갱신하고 큐에 좌표 입력
				if (dis[nx][ny] > dis[now.x][now.y] + map[nx][ny]) {
					dis[nx][ny] = dis[now.x][now.y] + map[nx][ny];
					pq.add(new Point(nx, ny, dis[nx][ny]));

					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							if (dis[i][j] == Integer.MAX_VALUE)
								System.out.print(-1 + " ");
							else
								System.out.print(dis[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println("---");
				}
			}
		}
		result = dis[N - 1][N - 1];
	}

}
