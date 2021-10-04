package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2999_비밀이메일 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String a = br.readLine();

		int size = a.length();
		int max = 0;
		int min = 101;
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (i * j == size && i <= j) {
					max = Math.max(i, max);
					min = Math.min(j, min);
				}
			}
		}

		char map[][] = new char[max][min];

		int cnt = 0;
		for (int i = 0; i < min; i++) {
			for (int j = 0; j < max; j++) {
				map[j][i] = a.charAt(cnt++);
			}
		}
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < min; j++) {
				sb.append(map[i][j]);
			}
		}

		System.out.println(sb);
		br.close();
	}

}
