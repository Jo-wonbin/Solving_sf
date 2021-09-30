package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs로 다닐 수 있는 파이프 찾고 chk 배열에 해당 파이프에 도달하기까지 걸린 시간 기록
public class SWEA_1953_탈주범검거 {

	static class Point {
		int x, y, type;

		public Point(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	static int N, M;
	static int map[][];
	static int chk[][];
	static Queue<Point> q = new LinkedList<>();
	static int dx[] = { 0, -1, 0, 1 }; // 좌상우하
	static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			map = new int[N][M]; // 파이프 값 받기 위한 배열
			chk = new int[N][M]; // 탈주범이 시간마다 이동 가능한 거리 체크

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			q.add(new Point(x, y, map[x][y])); // 시작 좌표 넣음
			chk[x][y] = 1; // 시작 좌표는 1로 기록
			int result = 0; // 시간 안에 다닐 수 있는 파이프 수
			bfs();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (chk[i][j] > 0 && chk[i][j] <= time) // 시간 안에 갈 수 있는 파이프 찾으면 +1
						result++;
				}
			}

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static boolean linkable(int nowh, int nexttype) {

		switch (nowh) { // 다음 갈 곳에 파이프가 이어지지 않다면 false
		case 0:
			if (nexttype == 2 || nexttype == 6 || nexttype == 7)
				return false;
			break;
		case 1:
			if (nexttype == 3 || nexttype == 4 || nexttype == 7)
				return false;
			break;
		case 2:
			if (nexttype == 2 || nexttype == 4 || nexttype == 5)
				return false;
			break;
		case 3:
			if (nexttype == 3 || nexttype == 5 || nexttype == 6)
				return false;
			break;
		}
		return true; // 갈 수 있으면 true
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Point now = q.poll();

			// 현재 좌표의 파이프 타입별 분기
			if (now.type == 1) {
				for (int h = 0; h < 4; h++) {
					int nx = now.x + dx[h];
					int ny = now.y + dy[h];

					if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
						continue;
					if (map[nx][ny] == 0)
						continue;
					if (chk[nx][ny] > 0)
						continue;
					if (!linkable(h, map[nx][ny]))
						continue;

					chk[nx][ny] = chk[now.x][now.y] + 1;
					q.add(new Point(nx, ny, map[nx][ny]));
				}
			} else if (now.type == 2 || now.type == 3) {
				for (int h = 0; h < 4; h++) {
					if (now.type == 2 && h % 2 == 0)
						continue;
					if (now.type == 3 && h % 2 == 1)
						continue;
					int nx = now.x + dx[h];
					int ny = now.y + dy[h];

					if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
						continue;
					if (map[nx][ny] == 0)
						continue;
					if (chk[nx][ny] > 0)
						continue;
					if (!linkable(h, map[nx][ny]))
						continue;

					chk[nx][ny] = chk[now.x][now.y] + 1;
					q.add(new Point(nx, ny, map[nx][ny]));
				}
			}

			else if (now.type >= 4) {
				for (int h = now.type; h < now.type + 2; h++) {
					int nx = now.x + dx[(h + 1) % 4];
					int ny = now.y + dy[(h + 1) % 4];

					if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
						continue;
					if (map[nx][ny] == 0)
						continue;
					if (chk[nx][ny] > 0)
						continue;
					if (!linkable((h + 1) % 4, map[nx][ny]))
						continue;

					chk[nx][ny] = chk[now.x][now.y] + 1;
					q.add(new Point(nx, ny, map[nx][ny]));
				}
			}
		}
	}
}
