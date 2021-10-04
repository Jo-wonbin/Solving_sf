package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {
	static int[][] copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
//		int[][] copy = new int[m][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String[] tc = br.readLine().split(" ");
		boolean flag = true;
		for (int z = 0; z < r; z++) {
			int num = Integer.parseInt(tc[z]);
			switch (num) {
			case 1:
				if (flag) {
					cs1(n, m, map);
					flag = true;
				} else {
					cs1(m, n, copy);
					flag = false;
				}
				break;
			case 2:
				if (flag) {
					cs2(n, m, map);
					flag = true;
				} else {
					cs2(m, n, copy);
					flag = false;
				}
				break;
			case 3:
				if (flag) {
					copy = cs3(n, m, map);
					flag = false;
				} else {
					map = cs3(m, n, copy);
					flag = true;
				}
				break;
			case 4:
				if (flag) {
					copy = cs4(n, m, map);
					flag = false;
				} else {
					map = cs4(m, n, copy);
					flag = true;
				}
				break;
			case 5:
				if (flag) {
					cs5(n, m, map);
					flag = true;
				} else {
					cs5(m, n, copy);
					flag = false;
				}
				break;
			case 6:
				if (flag) {
					cs6(n, m, map);
					flag = true;
				} else {
					cs6(m, n, copy);
					flag = false;
				}
				break;
			}
		}
		if (!flag) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(copy[i][j]).append(" ");
				}
				sb.setLength(sb.length() - 1);
				sb.append("\n");
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.setLength(sb.length() - 1);
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
		br.close();
	}

	static void cs1(int n, int m, int map[][]) {
		int temp = n / 2;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < temp; j++) {
				int tp = map[j][i];
				map[j][i] = map[n - 1 - j][i];
				map[n - 1 - j][i] = tp;
			}
		}
	}

	static void cs2(int n, int m, int map[][]) {
		int temp2 = m / 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < temp2; j++) {
				int tp2 = map[i][j];
				map[i][j] = map[i][m - 1 - j];
				map[i][m - 1 - j] = tp2;
			}
		}
	}

	static int[][] cs3(int n, int m, int map[][]) {
		int copy[][] = new int[m][n];
		int temp3 = 0;
		while (temp3 < m) {
			int cnt = 0;
			for (int i = n - 1; i >= 0; i--) {
				copy[temp3][cnt++] = map[i][temp3];
			}
			temp3++;
		}
		return copy;
	}

	static int[][] cs4(int n, int m, int map[][]) {
		int copy[][] = new int[m][n];
		int temp4 = m - 1;
		int cnt = 0;
		while (temp4 >= 0) {
			for (int i = 0; i < n; i++) {
				copy[cnt][i] = map[i][temp4];
			}
			cnt++;
			temp4--;
		}
		return copy;
	}

	static void cs5(int n, int m, int map[][]) {
		int arr[][] = new int[n / 2][m / 2];
		// 1복사
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				arr[i][j] = map[i][j];
			}
		}
		// 4->1
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i][j] = map[i + n / 2][j];
			}
		}
		// 3->4
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i + n / 2][j] = map[i + n / 2][j + m / 2];
			}
		}
		// 2->3
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i + n / 2][j + m / 2] = map[i][j + m / 2];
			}
		}
		// 2에 값 넣음
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i][j + m / 2] = arr[i][j];
			}
		}
	}

	static void cs6(int n, int m, int map[][]) {
		int ar[][] = new int[n / 2][m / 2];
		// 1복사
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				ar[i][j] = map[i][j];
			}
		}
		// 2->1
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i][j] = map[i][j + m / 2];
			}
		}
		// 3->2
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i][j + m / 2] = map[i + n / 2][j + m / 2];
			}
		}
		// 4->3
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i + n / 2][j + m / 2] = map[i + n / 2][j];
			}
		}
		// 4에 값 넣음
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				map[i + n / 2][j] = ar[i][j];
			}
		}
	}
}
