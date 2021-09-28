package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_3040_백설공주와일곱난쟁이 {

	static int small[];
	static int seven[];
	static boolean chk[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		small = new int[9];
		chk = new boolean[9];
		seven = new int[7];

		for (int k = 0; k < 9; k++) {
			small[k] = Integer.parseInt(br.readLine());
		}
		f2(small, 9);
//		find(0, 0);
//		System.out.println(sb.toString());
		br.close();
	}

//	static void find(int cnt, int num) {
//		if (cnt == 7) {
//			int sum = 0;
//			for (int i = 0; i < 7; i++) {
//				sum += seven[i];
//			}
//			if (sum == 100) {
//				for (int i = 0; i < 7; i++) {
//					sb.append(seven[i]).append("\n");
//				}
//			}
//			return;
//		}
//
//		for (int i = num; i < 9; i++) {
//			if (chk[i])
//				continue;
//			chk[i] = true;
//			seven[cnt] = small[i];
//			find(cnt + 1, i + 1);
//			chk[i] = false;
//		}
//	}

	private static void f2(int[] c, int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < (1 << n); i++) {
			if (count(i) == 7) {
				int sum = 0;
				for (int j = 0; j < n; j++) {
					if ((i & (1 << j)) != 0) {
						sum += c[j];
						list.add(c[j]);
					}
				}
				if (sum == 100) {
					list.forEach(l -> System.out.println(l));
				}
				list.clear();
			}
		}
	}

	private static int count(int value) {
		int count = 0;
		while (value > 0) {
			if ((value & 1) == 1)
				count++;
			value = value >> 1;
		}
		return count;
	}
}
