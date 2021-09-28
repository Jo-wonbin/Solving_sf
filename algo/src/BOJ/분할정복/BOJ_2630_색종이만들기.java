package BOJ.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기 {

	static int N;
	static int map[][];
	static int white, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		white = blue = 0;
		find(N, 0, 0);

		System.out.println(white);
		System.out.println(blue);
		
		br.close();
	}

	static void find(int size, int r, int c) {
		if (size == 2) {
			int temp = map[r][c];
			boolean flag = true;
			for (int i = r; i < r + 2; i++) {
				for (int j = c; j < c + 2; j++) {
					if (temp != map[i][j]) {
						flag = false;
					}
				}
			}
			if (flag) {
				if (temp == 1)
					blue++;
				else
					white++;
			} else {
				for (int i = r; i < r + 2; i++) {
					for (int j = c; j < c + 2; j++) {
						if (map[i][j] == 0)
							white++;
						else
							blue++;
					}
				}
			}
			return;
		}

		int temp = map[r][c];
		boolean flag = true;
		Loop1: for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (temp != map[i][j]) {
					flag = false;
					break Loop1;
				}
			}
		}
		if (flag) {
			if (temp == 1)
				blue++;
			else
				white++;
		} else {
			find(size / 2, r, c);
			find(size / 2, r, c + size / 2);
			find(size / 2, r + size / 2, c);
			find(size / 2, r + size / 2, c + size / 2);
		}

	}
}
