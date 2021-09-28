package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {

	static int N, M;
	static char[] arr;
	static char[] temp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		arr = new char[M];

		for (int i = 0; i < M; i++) {
			arr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(arr);

		temp = new char[N];
		dfs(0, 0);

		System.out.println(sb);

		br.close();
	}

	static void dfs(int cnt, int num) {
		if (cnt == N) {
			int Gcount = 0;
			int Mcount = 0;
			for (int i = 0; i < N; i++) {
				if (temp[i] == 'a' || temp[i] == 'e' || temp[i] == 'i' || temp[i] == 'o' || temp[i] == 'u') {
					Mcount++;
				} else {
					Gcount++;
				}

			}
			if (Gcount >= 2 && Mcount >= 1) {
				for (int i = 0; i < N; i++) {
					sb.append(temp[i]);
				}
				sb.append("\n");
			}
			return;
		}

		for (int i = num; i < M; i++) {
			temp[cnt] = arr[i];
			dfs(cnt + 1, i + 1);
		}
	}
}
