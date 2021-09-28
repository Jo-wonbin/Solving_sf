package BOJ.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992_쿼드트리 {

	static int N;
	static int map[][];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = a.charAt(j) - '0';
			}
		}
		if (N == 1) {
			sb.append(map[0][0]);
		} else {
			find(N, 0, 0);
		}
		System.out.println(sb);
		br.close();
	}

	static void find(int size, int r, int c) {
		if (size == 2) {
			int temp = map[r][c];
			boolean flag = true;
			for (int i = r; i < r + 2; i++) {
				for (int j = c; j < c + 2; j++) {
					if (i == r && j == c)
						continue;
					if (temp != map[i][j])
						flag = false;
				}
			}
			if (flag)
				sb.append(temp);
			else {
				sb.append("(");
				for (int i = r; i < r + 2; i++) {
					for (int j = c; j < c + 2; j++) {
						sb.append(map[i][j]);
					}
				}
				sb.append(")");
			}
			return;
		}

		int temp = map[r][c];
		boolean flag = true;
		Loop1: for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (i == r && j == c)
					continue;
				if (temp != map[i][j]) {
					flag = false;
					break Loop1;
				}
			}
		}
		if (flag)
			sb.append(temp);
		else {
			sb.append("(");
			find(size / 2, r, c);
			find(size / 2, r, c + size / 2);
			find(size / 2, r + size / 2, c);
			find(size / 2, r + size / 2, c + size / 2);
			sb.append(")");
		}
	}
}
