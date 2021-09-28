package BOJ.justdoit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BOJ_17406_배열돌리기4 {
	//회전 내용 저장 배열
	static Point[] p;
	//순열 체크
	static boolean chk[];
	//순열 저장
	static int num[];
	static int map[][];
	static int n, m, k;
	static int min;

	static class Point {
		int r, c, s;
		public Point(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		p = new Point[k];
		chk = new boolean[k];
		num = new int[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken()) - 1;
			int S = Integer.parseInt(st.nextToken());
			p[i] = new Point(R, C, S);
		}
		min = Integer.MAX_VALUE;
		find(0);
		System.out.println(min);
		br.close();
	}

	static void find(int cnt) {
		if (cnt == k) {
			int arr[][] = copy(map);
			for (int i = 0; i < k; i++) {
				int tp = num[i];
				int r = p[tp].r;
				int c = p[tp].c;
				int s = p[tp].s;
				int count = 0;
				while (count < s) {
					rightRotate(count + r - s, r + s - count, count + c - s, c + s - count, arr);
					count++;
				}
			}

			for (int i = 0; i < n; i++) {
				int sum = 0;
				sum = IntStream.of(arr[i]).sum();
				min = Math.min(sum, min);
			}
			return;
		}
		for (int i = 0; i < k; i++) {
			if (chk[i])
				continue;
			chk[i] = true;
			num[cnt] = i;
			find(cnt + 1);
			chk[i] = false;
		}
	}

	static int[][] copy(int arr[][]) {
		int temp[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			temp[i] = Arrays.copyOfRange(arr[i], 0, m);
		}
		return temp;
	}

	static void rightRotate(int rs, int re, int cs, int ce, int arr[][]) {
		int temp = arr[rs][cs];
		for (int i = rs; i < re; i++)
			arr[i][cs] = arr[i + 1][cs];
		for (int i = cs; i < ce; i++)
			arr[re][i] = arr[re][i + 1];
		for (int i = re; i > rs; i--)
			arr[i][ce] = arr[i - 1][ce];
		for (int i = ce; i > cs; i--)
			arr[rs][i] = arr[rs][i - 1];
		arr[rs][cs + 1] = temp;
	}
}
