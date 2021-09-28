package BOJ.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11401_이항계수3 {

	static final long P = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());

		long num = fac(N);

		long num2 = fac(N - M) * fac(M) % P;

		System.out.println(num * pow(num2, P - 2) % P);

		br.close();
	}

	private static long fac(long n) {
		long fac = 1L;
		while (n > 1) {
			fac = (fac * n) % P;
			n--;
		}
		return fac;
	}

	public static long pow(long base, long expo) {
		// 지수가 1일 경우 base^1 이므로 base % P를 리턴
		if (expo == 1) {
			return base % P;
		}

		// 지수의 절반에 해당하는 base^(expo / 2) 을 구한다.
		long temp = pow(base, expo / 2);

		/*
		 * 현재 지수가 홀 수 였다면 base^(expo / 2) * base^(expo / 2) * base 이므로 base를 한 번 더 곱해주어야
		 * 한다.
		 * 
		 * ex) A^9 = A^4 * A^4 * A
		 */
		if (expo % 2 == 1) {
			return (temp * temp % P) * base % P;
		}
		return temp * temp % P;

	}
}
