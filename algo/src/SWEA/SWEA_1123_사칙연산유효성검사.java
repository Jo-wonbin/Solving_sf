package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1123_사칙연산유효성검사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int k = 1; k <= 10; k++) {
			int N = Integer.parseInt(br.readLine());

			int result = 1;
			for (int i = 0; i < N; i++) {
				String[] arr = br.readLine().split(" ");

				// 맨 아랫줄 정점을 제외한 정점은 연산자가 있어야함.
				if (i < N / 2) {
					if (arr[1].equals("+") || arr[1].equals("-") || arr[1].equals("*") || arr[1].equals("/"))
						continue;

					result = 0;
				}
				// 맨 아랫줄 정점에는 숫자만 와야함.
				else {
					if (arr[1].equals("+") || arr[1].equals("-") || arr[1].equals("*") || arr[1].equals("/"))
						result = 0;
				}
			}
			sb.append("#").append(k).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
