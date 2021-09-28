package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {

	static class Point {
		int length, price;

		public Point(int start, int end) {
			super();
			this.length = start;
			this.price = end;
		}

	}

	static int N, result;
	static Point[] p;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		boolean chk[] = new boolean[N + 1];
		p = new Point[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			p[i] = new Point(x, y);
		}

		result = 0;
		find(1, 0, chk);

		System.out.println(result);

		br.close();
	}

	private static void find(int cnt, int sum, boolean[] chk) {
		if (cnt == N + 1) {
			result = Math.max(sum, result);
			return;
		}

		find(cnt + 1, sum, copy(chk));

		boolean flag = true;
		for (int i = cnt; i < cnt + p[cnt].length; i++) {
			if (cnt + p[cnt].length - 1 > N) {
				flag = false;
				break;
			}
			if (chk[i]) {
				flag = false;
				break;
			}
			chk[i] = true;
		}
		if (flag)
			find(cnt + 1, sum + p[cnt].price, copy(chk));
	}

	private static boolean[] copy(boolean[] chk) {
		boolean copy[] = new boolean[N + 1];
		copy = Arrays.copyOfRange(chk, 0, N+1);
		return copy;
	}
}
