package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {

	static int N, X, result;
	static int map[][];
	static int temp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			temp = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;

			for (int i = 0; i < N; i++) {
				if (find(i, true))
					result++;
				if (find(i, false))
					result++;
			}

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	private static boolean find(int x, boolean dir) {
		if (dir) {
			for (int i = 0; i < N; i++) {
				temp[i] = map[x][i];
			}
		} else {
			for (int i = 0; i < N; i++) {
				temp[i] = map[i][x];
			}
		}

		boolean check[] = new boolean[N];

		for (int cnt = 0; cnt < N - 1; cnt++) {
			if (temp[cnt] == temp[cnt + 1]) {
				continue;
			}

			if (Math.abs(temp[cnt] - temp[cnt + 1]) > 1)
				return false;

			if (temp[cnt] - 1 == temp[cnt + 1]) {
				for (int j = cnt + 1; j <= cnt + X; j++) {
					if (j >= N || check[j] || temp[j] != temp[cnt + 1]) {
						return false;
					}
					check[j] = true;
				}
			} else if (temp[cnt] + 1 == temp[cnt + 1]) {
				for (int j = cnt; j > cnt - X; j--) {
					if (j < 0 || check[j] || temp[j] != temp[cnt]) {
						return false;
					}
					check[j] = true;
				}
			}
		}
		return true;
	}
}
