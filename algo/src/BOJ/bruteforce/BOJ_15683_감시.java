package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {

	static class Point {
		int x, y, num;

		public Point(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static int N, M, min;
	static ArrayList<Point> CCTV = new ArrayList<>();
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int[][] dir = { {}, { 1 }, { 1, 3 }, { 0, 1 }, { 0, 1, 3 }, { 0, 1, 2, 3 } };// 0,1,2,3,4,5

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6)
					CCTV.add(new Point(i, j, map[i][j]));
			}
		}
		min = Integer.MAX_VALUE;

		find(0, map);

		System.out.println(min);
		br.close();
	}

	static void find(int cnt, int map[][]) {
		if (cnt == CCTV.size()) {
			min = Math.min(check(map), min);
			return;
		}

		int x = CCTV.get(cnt).x;
		int y = CCTV.get(cnt).y;
		int num = CCTV.get(cnt).num;

		// 4방향 탐색
		for (int h = 0; h < 4; h++) {
			//위아래 좌우만 탐색하므로 2번만 반복
			if (num == 2 && h > 1)
				break;
			//모든 방향 탐색하니까 1번만 작동
			if (num == 5 && h > 0)
				break;
			//탐색할 때마다 기존의 배열 복사
			int copy[][] = copy(map);

			// num에 해당하는 방향 탐색
			for (int i = 0; i < dir[num].length; i++) {
				// h를 뺀 이유는 4방향 탐색을 해야하기 때문, 3 더하는 이유는 h를 뺀 값을 4로 나눈 나머지가 음수가 안나오게 함.
				int nd = (dir[num][i] - h + 3) % 4;
				int nx = x;
				int ny = y;

				while (true) {
					//한칸 전진
					nx += dx[nd];
					ny += dy[nd];

					//벽이거나 범위 벗어나면 끝
					if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1 || copy[nx][ny] == 6)
						break;
					//CCTV면 그냥 지나감
					if (copy[nx][ny] > 0 && copy[nx][ny] < 6)
						continue;
					copy[nx][ny] = -1;
				}
			}
			// CCTV 하나 돌렸으면 다음 CCTV 확인
			find(cnt + 1, copy);
		}
	}

	static int[][] copy(int map[][]) {
		int copy[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	static int check(int map[][]) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}
