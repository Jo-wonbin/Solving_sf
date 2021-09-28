package BOJ.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780_종이의개수 {

	public static void cut(int n, int temp[][]) {
		int tp = temp[0][0];
		if (n == 1) {
			if (tp == -1) {
				minus++;
			} else if (tp == 0) {
				zero++;
			} else {
				one++;
			}
			return;
		}
		boolean flag = true;
		// 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (temp[i][j] != tp) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			if (tp == -1) {
				minus++;
			} else if (tp == 0) {
				zero++;
			} else {
				one++;
			}
			return;
		} else {
			int cnt = 0;
			int x = 0;
			int y = 0;
			while (cnt < 9) {
				int num[][] = new int[n / 3][n / 3];
				for (int i = 0; i < n / 3; i++) {
					for (int j = 0; j < n / 3; j++) {
						num[i][j] = temp[i + x][j + y];
					}
				}
				y += n / 3;
				if (y >= n) {
					y = 0;
					x += n / 3;
				}
				cut(n / 3, num);
				cnt++;
			}
		}

	}

	public

	static int minus, zero, one;

	public static void main(String[] args) throws IOException {
		minus = zero = one = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cut(n, map);

		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);

		br.close();
	}

}
