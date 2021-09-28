package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int profits[] = new int[N + 1];
		int weights[] = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			weights[i] = a;
			profits[i] = b;
		}

		int D[][] = new int[N + 1][W + 1];

		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= W; w++) {
				if (weights[i] > w) {
					D[i][w] = D[i - 1][w];
				} else {
					D[i][w] = Math.max(profits[i] + D[i - 1][w - weights[i]], D[i - 1][w]);
				}
			}
		}

		System.out.println(D[N][W]);
		br.close();
	}

}
