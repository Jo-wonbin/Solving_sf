package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트_knapsack {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			int[] del = new int[N + 1];
			int[] cal = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				del[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			int[][] D = new int[N + 1][W + 1];
			for (int i = 1; i <= N; i++) {
				for (int w = 1; w <= W; w++) {
					// 해당 물건을 가방에 넣을 수 있다.
					if (cal[i] <= w) {
						D[i][w] = Math.max(D[i - 1][w], del[i] + D[i - 1][w - cal[i]]);
					} else {// 해당 물건을 가방에 넣을 수 없다.
						D[i][w] = D[i - 1][w];
					}
				}
			}
			sb.append("#").append(k).append(" ").append(D[N][W]).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

}
