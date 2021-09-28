package SWEA;

import java.util.Scanner;

public class SWEA_1954_달팽이숫자 {

	static int max;
	static int[][] dp;
	static int n;

	public static void draw(int cnt, int dir, int x, int y) {
		int temp = dir;
		if (x < 0 || y < 0 || x > n - 1 || y > n - 1) {
			return;
		}
		if (cnt > max) {
			return;
		}
		dp[x][y] = cnt++;
		if (dir == 0) {
			// 오른쪽이 벽이 아니고 0 이상일 때와 벽일 때
			if ((y < n - 1 && dp[x][y + 1] > 0) || y == n - 1) {
				temp = 1;
				x += 1;
			} else {// 그게 아니면 오른쪽 이동
				y += 1;
			}
		} else if (dir == 1) {
			// 아래쪽이 벽이 아니고 0 이상일 때와 벽일 때
			if ((x < n - 1 && dp[x + 1][y] > 0) || x == n - 1) {
				temp = 2;
				y -= 1;
			} else {// 그게 아니면 아래쪽 이동
				x += 1;
			}
		} else if (dir == 2) {
			// 왼쪽이 벽이 아니고 0 이상일 때와 벽일 때
			if ((y > 0 && dp[x][y - 1] > 0) || y == 0) {
				temp = 3;
				x -= 1;
			} else {// 그게 아니면 왼쪽 이동
				y -= 1;
			}
		} else {
			// 위 쪽이 벽이 아니고 0 이상일 때와 벽일 때
			if ((x > 0 && dp[x - 1][y] > 0) || x == 0) {
				temp = 0;
				y += 1;
			} else {// 그게 아니면 위쪽 이동
				x -= 1;
			}
		}
		draw(cnt, temp, x, y);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int cnt = 1;
		while (cnt <= N) {
			n = sc.nextInt();

			dp = new int[n][n];
			max = n * n;

			draw(1, 0, 0, 0);

			System.out.printf("#%d\n", cnt);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.printf("%d ", dp[i][j]);
				}
				System.out.println();
			}
			cnt++;
		}
		sc.close();
	}

}
