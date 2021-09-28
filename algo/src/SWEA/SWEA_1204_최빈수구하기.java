package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1204_최빈수구하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String a = br.readLine();
		int n = Integer.parseInt(a);

		while (n-- > 0) {

			int cnt = Integer.parseInt(br.readLine());
			int num[] = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreTokens()) {
				int N = Integer.parseInt(st.nextToken());
				num[N]++;
			}
			int max = Integer.MIN_VALUE;
			int answer = 100;
			for (int i = num.length - 1; i >= 0; i--) {
				if (max < num[i]) {
					max = num[i];
					answer = i;
				}
			}
			System.out.printf("#%d %d\n", cnt, answer);
		}

		br.close();
	}

}
