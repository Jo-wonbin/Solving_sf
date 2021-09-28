package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와spotmart {

	static int n, m, answer;
	static int snack[];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			snack = new int[n];

			flag = false;
			answer = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}

			find(0, 0, 0);

			sb.append("#").append(k).append(" ");
			if (flag)
				sb.append(answer);
			else
				sb.append("-1");
			sb.append("\n");

		}
		System.out.println(sb);
		br.close();
	}

	static void find(int idx, int cnt, int sum) {
		// 제한 무게보다 무거우면 끝
		if (sum > m) {
			return;
		}
		// 두 봉지일 때 가장 무거운 것
		if (cnt == 2) {
			flag = true;
			if (sum > answer) {
				answer = sum;
			}
			return;
		}

		// 다 찾았으면 끝
		if (idx == n) {
			return;
		}
		// 현재 포함
		find(idx + 1, cnt + 1, sum + snack[idx]);

		// 현재 미포함
		find(idx + 1, cnt, sum);
	}

}
