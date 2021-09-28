package 정올.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정올_1681_해밀턴순환회로 {

	static int N, result;
	static int map[][];
	static boolean chk[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		chk = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = Integer.MAX_VALUE;
		chk[0] = true;
		dfs(0, 0, 0);

		System.out.println(result);

		br.close();
	}

	private static void dfs(int cnt, int now, int cost) {
		if (cnt == N - 1) {
			if (map[now][0] == 0)
				return;
			cost += map[now][0];
			result = Math.min(cost, result);
			return;
		}
		if (cost > result)
			return;

		for (int i = 0; i < N; i++) {

			if (chk[i])
				continue;
			if (map[now][i] == 0)
				continue;

			chk[i] = true;
			dfs(cnt + 1, i, cost + map[now][i]);
			chk[i] = false;
		}
	}
}
