package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기 {

	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 행과 열 둘 중 길이가 제일 작은 것에 영향
		int limitR = n / 2 - 1;
		int limitC = m / 2 - 1;
		int min = Math.min(limitR, limitC);
		for (int i = 0; i < r; i++) {
			int cnt = 0;
			while (cnt <= min) {
				leftRotate(cnt, n - 1 - cnt, cnt, m - 1 - cnt);
				cnt++;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.delete(sb.length() - 1, sb.length()).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void leftRotate(int rs, int re, int cs, int ce) {
		int temp = map[rs][cs];
		for (int i = cs; i < ce; i++)
			map[rs][i] = map[rs][i + 1];
		for (int i = rs; i < re; i++)
			map[i][ce] = map[i + 1][ce];
		for (int i = ce; i > cs; i--)
			map[re][i] = map[re][i - 1];
		for (int i = re; i > rs; i--)
			map[i][cs] = map[i - 1][cs];
		map[rs + 1][cs] = temp;
	}

	static void rightRotate(int rs, int re, int cs, int ce) {
		int temp = map[rs][cs];
		for (int i = rs; i < re; i++)
			map[i][cs] = map[i + 1][cs];
		for (int i = cs; i < ce; i++)
			map[re][i] = map[re][i + 1];
		for (int i = re; i > rs; i--)
			map[i][ce] = map[i - 1][ce];
		for (int i = ce; i > cs; i--)
			map[rs][i] = map[rs][i - 1];
		map[rs][cs + 1] = temp;
	}
}
