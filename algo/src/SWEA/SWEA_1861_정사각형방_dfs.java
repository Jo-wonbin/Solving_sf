package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방_dfs {

	static int n;
	static int map[][];
	static int max;
	static int answer;
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

			max = Integer.MIN_VALUE;
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dfs(map[i][j], 1, i, j);
				}
			}

			sb.append("#").append(k).append(" ").append(answer).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	public static void dfs(int num, int cnt, int x, int y) {

		if (max < cnt) {
			max = cnt;
			answer = map[x][y];
		} else if (max == cnt) {
			answer = Math.min(map[x][y], answer);
		}

		for (int h = 0; h < 4; h++) {
			int nx = x + dx[h];
			int ny = y + dy[h];

			if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1)
				continue;
			if (num != map[nx][ny] + 1) {
				continue;
			}

			dfs(map[nx][ny], cnt + 1, nx, ny);
		}
	}

}
