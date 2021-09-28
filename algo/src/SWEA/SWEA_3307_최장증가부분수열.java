package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3307_최장증가부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());

			int[] arr = new int[N];
			int[] LIS = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int max = 0;
			for (int i = 0; i < N; i++) {
				LIS[i] = 1;

				for (int j = 0; j < i; j++) { // j < i의 앞쪽 원소
					if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
						LIS[i] = LIS[j] + 1;
					}
					// i를 끝으로 하는 최장길이 값 계산 완료
					if (max < LIS[i])
						max = LIS[i];
				}
			}
			System.out.println(Arrays.toString(LIS));
			sb.append("#").append(k).append(" ").append(max).append("\n");
		}

		System.out.println(sb);

		br.close();
	}

}
