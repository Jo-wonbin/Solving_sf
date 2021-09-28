package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, leftCheese;
	// 치즈 위치 저장
	static int map[][];
	// 공기와 닫는 치즈 저장
	static int chk[][];
	// 치즈 중복 탐색 방지
	static boolean cheeseChk[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 걸린 시간
		int cnt = 0;
		// 마지막에 남은 치즈 갯수
		leftCheese = 0;

		while (true) {
			chk = new int[N][M];
			cheeseChk = new boolean[N][M];

			// 치즈와 닫는 공기 탐색
			bfs(0, 0, 0);

			// 공기와 닿아있는 치즈 탐색
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (cheeseChk[i][j])
						continue;
					if (map[i][j] == 1)
						bfs(i, j, 1);
				}
			}

			// 공기과 닿은 치즈 삭제
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (chk[i][j] == 2)
						map[i][j] = 0;
				}
			}

			// 더 이상 치즈를 삭제할 수 없으면 종료
			if (check())
				break;

			// 걸린 시간 +1
			cnt++;
		}

		System.out.println(cnt);
		System.out.println(leftCheese);
		br.close();
	}

	static boolean check() {
		int temp = 0;
		// 삭제할 치즈 탐색
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (chk[i][j] == 2)
					temp++;
			}
		}
		
		// 삭제할 치즈가 있으면 남은 치즈 변수에 갯수 저장
		if (temp != 0) {
			leftCheese = temp;
			return false;
		} else
			return true;
	}

	static void bfs(int x, int y, int type) {
		q.add(new Point(x, y));

		// 타입이 0이면 바깥 공기 탐색, 바깥이 1이면 치즈 탐색
		if (type == 0) {
			chk[x][y] = -1;
		} else {
			cheeseChk[x][y] = true;
		}

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				// 범위 벗어나면 패스
				if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
					continue;
				// 공기 탐색
				if (type == 0) {
					// 치즈를 만나면 패스
					if (map[nx][ny] == 1)
						continue;
					// 이미 지나간 곳은 패스
					if (chk[nx][ny] == -1)
						continue;
					q.add(new Point(nx, ny));
					chk[nx][ny] = -1;
				} 
				// 치즈 탐색
				else {
					// 방문했으면 패스
					if (cheeseChk[nx][ny])
						continue;
					// 치즈 주변이 바깥 공기를 만나면 삭제할 치즈 좌표에 저장
					if (chk[nx][ny] == -1) {
						chk[now.x][now.y] = 2;
						continue;
					}
					// 공기면 패스
					if (map[nx][ny] == 0)
						continue;
					cheeseChk[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}

	}
}
