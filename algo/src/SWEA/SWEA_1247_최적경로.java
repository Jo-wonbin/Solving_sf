package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static Point com, home;
	static Point[] p;
	static Point[] temp;
	static int N, min;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 회사 좌표
			com = new Point(x, y);

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			// 집 좌표
			home = new Point(x, y);

			p = new Point[N];
			temp = new Point[N];
			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				p[i] = new Point(x, y);
			}
			min = Integer.MAX_VALUE;
			find(0, 0, 0);

			sb.append("#").append(k).append(" ").append(min).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	private static void find(int cnt, int flag, int sumA) {
		if (sumA > min && min != Integer.MAX_VALUE)
			return;

		if (cnt == N) {
			Point now = temp[N - 1];
			sumA += Math.abs(now.x - home.x) + Math.abs(now.y - home.y);
			min = Math.min(sumA, min);
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			temp[cnt] = p[i];
			if (cnt == 0)
				find(cnt + 1, flag | 1 << i, sumA + Math.abs(temp[cnt].x - com.x) + Math.abs(temp[cnt].y - com.y));
			else
				find(cnt + 1, flag | 1 << i,
						sumA + Math.abs(temp[cnt-1].x - temp[cnt].x) + Math.abs(temp[cnt-1].y - temp[cnt].y));

		}
	}

//	private static void find(int cnt, int flag) {
//		if (cnt == N) {
//			int sum = 0;
//			Point now = temp[0];
//			sum += Math.abs(now.x - com.x) + Math.abs(now.y - com.y);
//			for (int i = 1; i < N; i++) {
//				sum += Math.abs(now.x - temp[i].x) + Math.abs(now.y - temp[i].y);
//				now = temp[i];
//			}
//			sum += Math.abs(now.x - home.x) + Math.abs(now.y - home.y);
//			min = Math.min(sum, min);
//			return;
//		}
//
//		for (int i = 0; i < N; i++) {
//			if ((flag & 1 << i) != 0)
//				continue;
//			temp[cnt] = p[i];
//			find(cnt + 1, flag | 1 << i);
//		}
//	}
}
