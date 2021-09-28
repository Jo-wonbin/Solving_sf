package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3324_준환이의양팔저울 {

	static int N, result;
	static int[] num;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int k = 1; k <= tc; k++) {
			N = Integer.parseInt(br.readLine());

			num = new int[N];
			arr = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			result = 0;
			perm(0, 0);

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	private static void find(int cnt, int leftSum, int rightSum) {
		if (cnt == N) {
			result++;
			return;
		}

		if (leftSum + arr[cnt] >= rightSum) {
			find(cnt + 1, leftSum + arr[cnt], rightSum);
		}
		if (leftSum >= rightSum + arr[cnt]) {
			find(cnt + 1, leftSum, rightSum + arr[cnt]);
		}
	}

	private static void perm(int cnt, int flag) {
		if (cnt == N) {
			find(0, 0, 0);
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			arr[cnt] = num[i];
			perm(cnt + 1, flag | 1 << i);
		}
	}
}
