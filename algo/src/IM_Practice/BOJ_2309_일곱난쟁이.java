package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2309_일곱난쟁이 {

	static int small[] = new int[9];
	static int seven[] = new int[7];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			small[i] = Integer.parseInt(br.readLine());
		}
		find(0, 0, 0);

		br.close();
	}

	static void find(int cnt, int num, int flag) {
		if (cnt == 7) {
			int sum = Arrays.stream(seven).sum();
			if (sum == 100) {
				Arrays.sort(seven);
				for(int i=0; i<7; i++) {
					System.out.println(seven[i]);
				}
			}
			return;
		}

		for (int i = num; i < 9; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			seven[cnt] = small[i];
			find(cnt + 1, i + 1, flag | 1 << i);
		}
	}
}
