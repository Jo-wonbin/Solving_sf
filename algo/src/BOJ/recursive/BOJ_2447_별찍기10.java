package BOJ.recursive;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2447_별찍기10 {

	static char[][] st;

	public static void star(int n, int x, int y) {
		if (n == 1) {
			st[x][y] = '*';
			return;
		}
		n /= 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					continue;
				star(n, i * n + x, j * n + y);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		st = new char[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				st[i][j] = ' ';
			}
		}

		star(N, 0, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(st[i][j] + " ");
			}
			System.out.println();
		}
		br.close();
	}

}
