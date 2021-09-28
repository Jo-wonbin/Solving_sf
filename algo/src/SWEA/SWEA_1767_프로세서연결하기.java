package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, result, coreCnt, maxCoreCnt;
	static int map[][];
	static List<Point> core = new ArrayList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
							continue;
						} else {
							core.add(new Point(i, j));
						}
					}
				}
			}

			result = Integer.MAX_VALUE;
			maxCoreCnt = Integer.MIN_VALUE;

			find(0, 0, 0);

			sb.append("#").append(k).append(" ").append(result).append("\n");
			core.clear();
		}

		System.out.println(sb);
		br.close();
	}

	static void find(int cnt, int sum, int coreNum) {
		if (cnt == core.size()) {
			if (maxCoreCnt < coreNum) {
				maxCoreCnt = coreNum;
				result = sum;
			} else if (maxCoreCnt == coreNum) {
				result = Math.min(sum, result);
			}

			return;
		}

		Point now = core.get(cnt);

		for (int h = 0; h < 4; h++) {
			int nx = now.x;
			int ny = now.y;
			int sx = now.x;
			int sy = now.y;

			int size = 0;
			while (true) {
				nx += dx[h];
				ny += dy[h];
				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					break;
				if (map[nx][ny] == 1) {
					size = 0;
					break;
				}

				size++;
			}

			for (int i = 0; i < size; i++) {
				sx += dx[h];
				sy += dy[h];
				map[sx][sy] = 1;
			}

			if (size == 0)
				find(cnt + 1, sum, coreNum);
			else {
				find(cnt + 1, sum + size, coreNum + 1);
				sx = now.x;
				sy = now.y;
				for (int i = 0; i < size; i++) {
					sx += dx[h];
					sy += dy[h];
					map[sx][sy] = 0;
				}
			}
		}

	}
}
