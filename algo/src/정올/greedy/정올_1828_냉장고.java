package 정올.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정올_1828_냉장고 {
	static class Ref implements Comparable<Ref> {
		int start, end;

		public Ref(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Ref o) {
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		Ref arr[] = new Ref[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Ref(x, y);
		}

		Arrays.sort(arr);
		int temp = 1;
		int cnt = 1;
		Ref now = arr[0];
		while (temp < N) {
			if (now.end < arr[temp].start) {
				now = arr[temp];
				cnt++;
			}
			temp++;
		}

		System.out.println(cnt);
		br.close();
	}

}
