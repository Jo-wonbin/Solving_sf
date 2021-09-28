package BOJ.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {
	static class Point {
		int x, y, count, time;
		
		public Point(int x, int y, int count, int time) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.time = time;
		}
	}

	static int K, N, M, result = -1;
	static int map[][];
	// 방문 저장 배열
	static boolean chk[][][];
	// 12방향 탐색
	static int dx[] = { 0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2 };
	static int dy[] = { -1, 1, 0, 0, 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		// K개의  2차원 배열
		chk = new boolean[K + 1][N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();

		System.out.println(result);

		br.close();
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, K, 0));
		
		// 맨 왼쪽 윗 좌표 방문
		chk[K][0][0] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();
			
			// q에서 나온 좌표가 맨 오른쪽 아래 좌표인 경우, 제일 빨리 도착한 시간이 된다.
			if (now.x == N - 1 && now.y == M - 1) {
				result = now.time;
				return;
			}
			
			// 12방향 탐색
			for (int h = 0; h < 12; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];
				int nk = now.count;

				// 점프하는 경우 점프 카운트가 0이 아니면 -1 해주고 아니면 멈춤.
				if (h >= 4) {
					if (nk == 0)
						break;
					else
						nk--;
				}
				
				// 범위 벗어나면 패스
				if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
					continue;
				// 도착 좌표가 장애물이면 패스
				if (map[nx][ny] == 1)
					continue;
				// 이미 방문했으면 패스
				if (chk[nk][nx][ny])
					continue;

				chk[nk][nx][ny] = true;
				q.add(new Point(nx, ny, nk, now.time + 1));
			}
		}
	}
}
