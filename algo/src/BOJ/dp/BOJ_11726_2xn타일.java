package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11726_2xn타일 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		final long P = 10007L;
		long arr[] = new long[1001];
		arr[1] = 1L;
		arr[2] = 2L;

		if (N >= 3) {
			for (int i = 3; i <= N; i++) {
				arr[i] = (arr[i - 1] + arr[i - 2]) % P;
			}
		}

		System.out.println(arr[N]);
		br.close();
	}

}
