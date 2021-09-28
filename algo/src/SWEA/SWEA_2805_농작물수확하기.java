package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2805_농작물수확하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int cnt = 1;
		while (cnt <= N) {
			int n = Integer.parseInt(br.readLine());
			int answer = 0;
			int map[][] = new int[n][n];

			for (int i = 0; i < n; i++) {
				String a = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = a.charAt(j)-'0';
				}
			}
			
			//마름모 기준선
			int line = (n - 1) / 2;
			int start, end;
			start = end = line;
			
			for (int i = 0; i < n; i++) {
				if(i != 0) {					
					if (i <= line) {
						start--;
						end++;
					} else {
						start++;
						end--;
					}
				}
				for (int j = 0; j < n; j++) {
					if (start <= j && end >= j) {
						answer += map[i][j];
					}
				}
			}

			System.out.printf("#%d %d\n", cnt, answer);
			cnt++;

		}

		br.close();
	}

}
