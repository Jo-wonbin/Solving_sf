package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4012_요리사 {

	static int result, N;
	static int map[][];
	static int temp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			temp = new int[N];
			find(0, 0);

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	static void find(int cnt, int num) {
		if (cnt == N / 2) {
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (temp[i] == 1 && temp[j] == 1)
						sumA += map[i][j];
					else if (temp[i] == 0 && temp[j] == 0)
						sumB += map[i][j];
				}
			}
			result = Math.min(Math.abs(sumA - sumB), result);
			return;
		}

		for (int i = num; i < N; i++) {
			temp[i] = 1;
			find(cnt + 1, i + 1);
			temp[i] = 0;
		}
	}
}
