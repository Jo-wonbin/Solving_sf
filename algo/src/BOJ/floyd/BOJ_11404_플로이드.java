package BOJ.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());

		final int INF = 10000001;

		int map[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j && map[i][j] == 0) {
					map[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			map[start][end] = Math.min(weight, map[start][end]);
		}
		// 경유지
		for (int k = 1; k <= N; ++k) {
			// 출발지
			for (int i = 1; i <= N; ++i) {
				if (i == k)
					continue;
				// 도착지
				for (int j = 1; j <= N; ++j) {
					if (i == j || k == j)
						continue;

					// [i][j]의 비용이 [i][k] +[k][j] k를 거쳐온 비용보다 크다면
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == INF) {
					map[i][j] = 0;
				}
				sb.append(map[i][j]).append(" ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}

		System.out.println(sb);

		br.close();
	}

}
