package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1263_사람네트워크2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());

			int D[][] = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (temp == 0 && i != j)
						temp = Integer.MAX_VALUE / 2;
					D[i][j] = temp;
				}
			}

			for (int z = 1; z <= N; z++) {
				for (int i = 1; i <= N; i++) {
					if (i == z)
						continue;
					for (int j = 1; j <= N; j++) {
						if (i == z || j == i)
							continue;
						D[i][j] = Math.min(D[i][z] + D[z][j], D[i][j]);
					}

				}
			}

			int result = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				int cnt = 0;
				for (int j = 1; j <= N; j++) {
					cnt += D[i][j];
				}
				if (result > cnt) {
					result = cnt;
				}
			}

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

}
