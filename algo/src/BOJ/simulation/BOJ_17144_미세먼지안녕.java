package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {

	static int N, M, T, result;
	static int map[][];
	static int copy[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		int up = 0;
		int down = 0;

		boolean flag = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && flag) {
					up = i;
					down = i + 1;
					flag = false;
				}
			}
		}

		result = 0;
		while (T-- > 0) {
			copy = new int[N][M];
			// 미세먼지 퍼지기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0) {
						map[i][j] -= spread(i, j);
					}
				}
			}
			// 퍼진 미세먼지 값 합치기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] += copy[i][j];
				}
			}

			// 공기청정기 회전
			leftRotate(0, up, 0, M - 1);
			rightRotate(down, N - 1, 0, M - 1);

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0)
					result += map[i][j];
			}
		}

		System.out.println(result);
		br.close();
	}

	static int spread(int x, int y) {
		int cnt = 0;
		for (int h = 0; h < 4; h++) {
			int nx = x + dx[h];
			int ny = y + dy[h];

			if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
				continue;
			if (map[nx][ny] == -1)
				continue;

			copy[nx][ny] += map[x][y] / 5;
			cnt++;
		}

		return (map[x][y] / 5) * cnt;
	}

	static void leftRotate(int rs, int re, int cs, int ce) {
		int temp = map[rs][cs];
		for (int i = cs; i < ce; i++)
			map[rs][i] = map[rs][i + 1];
		for (int i = rs; i < re; i++)
			map[i][ce] = map[i + 1][ce];
		for (int i = ce; i > cs; i--)
			map[re][i] = map[re][i - 1];
		for (int i = re; i > rs; i--)
			map[i][cs] = map[i - 1][cs];
		map[rs + 1][cs] = temp;
		map[re][cs] = -1;
		map[re][cs + 1] = 0;
	}

	static void rightRotate(int rs, int re, int cs, int ce) {
		int temp = map[rs][cs];
		for (int i = rs; i < re; i++)
			map[i][cs] = map[i + 1][cs];
		for (int i = cs; i < ce; i++)
			map[re][i] = map[re][i + 1];
		for (int i = re; i > rs; i--)
			map[i][ce] = map[i - 1][ce];
		for (int i = ce; i > cs; i--)
			map[rs][i] = map[rs][i - 1];
		map[rs][cs] = temp;
		map[rs][cs + 1] = 0;
	}

}
