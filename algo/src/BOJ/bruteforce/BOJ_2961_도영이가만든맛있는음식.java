package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가만든맛있는음식 {

	static class Del {
		int sour, bitter;

		public Del(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}

	static Del[] d;
	static int N, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		d = new Del[N];
		min = Integer.MAX_VALUE;

		for (int k = 0; k < N; k++) {
			st = new StringTokenizer(br.readLine());
			d[k] = new Del(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		boolean chk[] = new boolean[N];

		find(0, 1, 0, chk);
		System.out.println(min);

		br.close();
	}

	static void find(int cnt, int sr, int br, boolean chk[]) {
		if (cnt == N) {
			// 재료 하나도 안 넣었을 때 제외
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				if (!chk[i]) {
					continue;
				}
				flag = true;
				break;
			}
			// 재료를 넣었을 때만 동작
			if (flag) {
				if (cnt > 1)
					min = Math.min(Math.abs(sr - br), min);
				else if (cnt == 1) {
					min = Math.abs(d[cnt - 1].sour - d[cnt - 1].bitter);
				}
			}
			return;
		}

		chk[cnt] = true;
		find(cnt + 1, d[cnt].sour * sr, d[cnt].bitter + br, chk);
		chk[cnt] = false;
		find(cnt + 1, sr, br, chk);
	}
}
