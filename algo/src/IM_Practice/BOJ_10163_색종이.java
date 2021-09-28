package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10163_색종이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int map[][] = new int[1001][1001];
		int N = Integer.parseInt(br.readLine());
		for (int k = 1; k <= N; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int rsize = Integer.parseInt(st.nextToken());
			int csize = Integer.parseInt(st.nextToken());

			for (int i = r; i < r + rsize; i++) {
				for (int j = c; j < c + csize; j++) {
					map[i][j] = k;
				}
			}
		}
		int num[] = new int[101];
		for (int i = 0; i < 1001; i++) {
			for (int j = 0; j < 1001; j++) {
				num[map[i][j]]++;
			}
		}
		for (int i = 1; i <= N; i++) {
			sb.append(num[i]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
