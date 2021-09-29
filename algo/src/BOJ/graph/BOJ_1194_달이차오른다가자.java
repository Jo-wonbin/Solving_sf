package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {

	static class Point {
		int x, y, count, keyLevel;

		// 2차원 배열 좌표, 누적 시간, 열쇠 레벨
		public Point(int x, int y, int count, int keyLevel) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.keyLevel = keyLevel;
		}
	}

	static int N, M, result;
	static char[][] map;
	static boolean chk[][][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		chk = new boolean[64][N][M];

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			for (int j = 0; j < M; j++) {

				map[i][j] = a.charAt(j); // 지도 저장

				if (map[i][j] == '0') { // 시작점 좌표일 때 q에 저장
					q.add(new Point(i, j, 0, 0)); //
					chk[0][i][j] = true;
				}
			}
		}

		result = bfs();
		System.out.println(result);
		br.close();
	}

	static int bfs() {
		while (!q.isEmpty()) {
			Point now = q.poll();

			if (map[now.x][now.y] == '1') {
				return now.count;
			}

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];
				int kl = now.keyLevel;

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
					continue;
				if (chk[kl][nx][ny])
					continue;
				if (map[nx][ny] == '#')
					continue;

				if (map[nx][ny] - 65 >= 0 && map[nx][ny] - 65 <= 5) {// 문을 만나면 (A = 0, F = 5)
					int temp = map[nx][ny] - 65;
					if ((kl & (1 << temp)) == (1 << temp)) // 해당 문에 키가 없다면 패스
						continue;
				}

				if (map[nx][ny] - 97 >= 0 && map[nx][ny] - 97 <= 5) { // 열쇠 만나면 열쇠 가진 후 이동(a = 0, f=5)
					int idx = map[nx][ny] - 97;
					kl = (kl | (1 << idx));
					chk[kl][nx][ny] = true;
					q.add(new Point(nx, ny, now.count + 1, kl));
					continue;
				}

				chk[kl][nx][ny] = true;
				q.add(new Point(nx, ny, now.count + 1, kl));
			}
		}
		return -1;
	}
}
