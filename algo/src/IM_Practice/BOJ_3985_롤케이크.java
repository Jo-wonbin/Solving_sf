package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3985_롤케이크 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int tc = Integer.parseInt(br.readLine());

		boolean cake[] = new boolean[N + 1];
		int maxA = Integer.MIN_VALUE;
		int maxB = Integer.MIN_VALUE;
		int resultA = 0;
		int resultB = 0;

		for (int i = 1; i <= tc; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (maxA < end - start) {
				maxA = end - start;
				resultA = i;
			}
			int cnt = 0;
			for (int j = start; j <= end; j++) {
				if(cake[j])
					continue;
				cake[j] = true;
				cnt++;
			}
			if(maxB < cnt) {
				maxB = cnt;
				resultB = i;
			}

		}
		System.out.println(resultA);
		System.out.println(resultB);

		br.close();
	}
}