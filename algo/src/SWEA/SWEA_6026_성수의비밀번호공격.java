package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6026_성수의비밀번호공격 {

	static final long P = 1000000007;
	static long fac[];
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 메모이제이션
		fac = new long[101];
		fac[0] = 1L;
		for (int i = 1; i <= 100; i++) {
			fac[i] = i * fac[i - 1] % P;
		}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // M개의 지문 자국
			N = Integer.parseInt(st.nextToken()); // N 자리 비밀번호

			long result = find();

			sb.append("#").append(k).append(" ").append(result >= 0 ? result : result + P).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	// 전사 함수 M^N -() +() - () + () ....
	private static long find() {
		long result = 0L;
		for (int i = 0; i < M; i++) {
			result = (result + ((i % 2 == 0 ? 1 : -1) * ((pow(M - i, N) % P) * (nCr(M, i) % P)) % P) % P) % P;
		}
		return result % P;
	}

	// 지수 만들기
	private static long pow(long n, long m) {
		long res = 1L;

		n = n % P;
		while (m > 0) {
			if (m % 2 == 1)
				res = (res * n) % P;
			m = m >> 1;
			n = (n * n) % P;
		}

		return res % P;
	}

	// 역함수 구하기
	static long modInverse(long n, long p) {
		return pow(n, p - 2);
	}

	// 조합 만들기(페르마의 소정리 이용)
	private static long nCr(int n, int r) {
		if (r == 0)
			return 1L;

		return (fac[n] * modInverse(fac[r], P) % P * modInverse(fac[n - r], P) % P) % P;
	}
}
