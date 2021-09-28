package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2578_빙고 {
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[5][5];
		int call[][] = new int[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				call[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 1;
		Loop1: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				remove(call[i][j]);
				if (bingo())
					break Loop1;
				cnt++;
			}
		}

		System.out.println(cnt);
		br.close();
	}

	static void remove(int n) {
		Loop1: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] != n)
					continue;

				map[i][j] = 0;
				break Loop1;
			}
		}
	}

	static boolean bingo() {
		int sum = 0;

		int garoCnt = 0;
		int cnt = 0;
		while (cnt < 5) {
			for (int i = 0; i < 5; i++) {
				if (map[cnt][i] != 0)
					break;
				if (i == 4)
					garoCnt++;
			}
			cnt++;
		}

		int seroCnt = 0;
		cnt = 0;
		while (cnt < 5) {
			for (int i = 0; i < 5; i++) {
				if (map[i][cnt] != 0)
					break;
				if (i == 4)
					seroCnt++;
			}
			cnt++;
		}
		boolean flagA = true;
		boolean flagB = true;
		cnt = 0;
		while (cnt < 5) {
			if (map[cnt][cnt] != 0)
				flagA = false;

			if (map[cnt][4 - cnt] != 0)
				flagB = false;

			cnt++;
		}
		if (flagA)
			sum += 1;
		if (flagB)
			sum += 1;

		sum += garoCnt + seroCnt;
		if (sum >= 3)
			return true;

		return false;
	}
}
