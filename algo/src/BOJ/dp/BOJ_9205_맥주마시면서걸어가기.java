package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int INF = (32767 * 2) + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 편의점 갯수
			int N = Integer.parseInt(st.nextToken());

			Point[] list = new Point[N + 2];

			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list[i] = new Point(x, y);
			}

			int D[][] = new int[N + 2][N + 2];

			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i == j)
						continue;
					D[i][j] = Math.abs(list[i].x - list[j].x) + Math.abs(list[i].y - list[j].y);
					if (D[i][j] <= 1000)
						D[i][j] = 1;
					else
						D[i][j] = INF;
				}
			}

			for (int z = 0; z < N + 2; z++) {
				for (int i = 0; i < N + 2; i++) {
					if (i == z)
						continue;
					for (int j = 0; j < N + 2; j++) {
						if (i == z || j == i)
							continue;
						D[i][j] = Math.min(D[i][z] + D[z][j], D[i][j]);
					}

				}
			}
			String feeling = "sad";
			if (0 < D[0][N + 1] && D[0][N + 1] < INF)
				feeling = "happy";

			sb.append(feeling).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

}
