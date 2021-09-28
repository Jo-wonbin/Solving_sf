package BOJ.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074_Z {

	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);

		count = 0;

		find(size, r, c);

		System.out.println(count);
		br.close();
	}

	static void find(int size, int r, int c) {
		if (size == 1) {
			return;
		}

		if (r < size / 2 && c < size / 2) {
			find(size / 2, r, c);
		} else if (r < size / 2 && c >= size / 2) {
			count += ((size / 2) * (size / 2));
			find(size / 2, r, c - size / 2);
		} else if (r >= size / 2 && c < size / 2) {
			count += ((size / 2) * (size / 2)) * 2;
			find(size / 2, r - size / 2, c);
		} else if (r >= size / 2 && c >= size / 2) {
			count += ((size / 2) * (size / 2)) * 3;
			find(size / 2, r - size / 2, c - size / 2);
		}
	}
}
