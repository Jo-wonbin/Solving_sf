package 정올.pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정올_2577_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int list[] = new int[N];
		int dlist[] = new int[d + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list[i] = Integer.parseInt(st.nextToken());
		}

		int temp = 0;

		int res = 0;
		for (int i = 0; i < k; i++) {
			if (dlist[list[i]] == 0)
				temp++;
			dlist[list[i]]++;
		}

		res = temp;

		for (int i = 1; i < N; i++) {
			if (res <= temp) {
				if (dlist[c] == 0) {
					res = temp + 1;
				} else {
					res = temp;
				}
			}
			dlist[list[i - 1]]--;
			if (dlist[list[i - 1]] == 0)
				temp--;
			if (dlist[list[(i + k - 1) % N]] == 0)
				temp++;
			dlist[list[(i + k - 1) % N]]++;
		}

		System.out.println(res);
		br.close();
	}

}
