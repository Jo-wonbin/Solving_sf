package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001_파리퇴치 {

	static int answer;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int cnt = 1;
		while (cnt <= N) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			answer = Integer.MIN_VALUE;

			for (int i = 0; i < n - m + 1; i++) {
				for (int j = 0; j < n - m + 1; j++) {
					find(i, j, m);
				}
			}

			System.out.printf("#%d %d\n", cnt, answer);
			cnt++;
		}

		br.close();
	}

	public static void find(int x, int y, int size) {
		int temp = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				temp += map[i][j];
			}
		}
		answer = Math.max(temp, answer);
	}
}
