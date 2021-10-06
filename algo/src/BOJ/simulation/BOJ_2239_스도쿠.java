package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2239_스도쿠 {

	static int map[][];
	static ArrayList<int[]> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[9][9];

		for (int i = 0; i < 9; i++) {
			String a = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = a.charAt(j) - '0';
				if (map[i][j] == 0)
					list.add(new int[] { i, j });
			}
		}
		check(0);

		br.close();
	}

	private static void check(int cnt) {

		if (list.size() == cnt) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}

		boolean chk[] = new boolean[10];
		int x = list.get(cnt)[0];
		int y = list.get(cnt)[1];
		// 가로줄 세로줄 검사
		for (int i = 0; i < 9; i++) {
			if (map[x][i] > 0)
				chk[map[x][i]] = true;
			if (map[i][y] > 0)
				chk[map[i][y]] = true;
		}

		// 3x3
		int sx = (x / 3) * 3;
		int sy = (y / 3) * 3;
		for (int i = sx; i < sx + 3; i++) {
			for (int j = sy; j < sy + 3; j++) {
				if (map[i][j] > 0)
					chk[map[i][j]] = true;
			}
		}
		// 계산
		for (int i = 1; i <= 9; i++) {
			if (!chk[i]) {
				map[x][y] = i;
				check(cnt + 1);
				map[x][y] = 0;
			}
		}
	}
}
