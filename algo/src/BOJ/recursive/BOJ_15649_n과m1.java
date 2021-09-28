package BOJ.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649_n과m1 {

	static int arr[];
	static boolean chk[];
	static int size;
	static int n;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		size = Integer.parseInt(st.nextToken());

		arr = new int[size];
		chk = new boolean[n + 1];

		find(0);

		System.out.println(sb);

		br.close();
	}

	public static void find(int cnt) {
		if (cnt == size) {
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (chk[i])
				continue;
			chk[i] = true;
			arr[cnt] = i;
			find(cnt + 1);
			chk[i] = false;
		}

	}
}
