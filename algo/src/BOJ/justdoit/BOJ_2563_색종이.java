package BOJ.justdoit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2563_색종이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		boolean paper[][] = new boolean[101][101];

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			for (int i = x; i < x + 10; i++) {
				for (int j = y; j < y + 10; j++) {
					paper[i][j] = true;
				}
			}

		}
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (paper[i][j])
					cnt++;
			}
		}
		System.out.println(cnt);

		br.close();
	}

}
