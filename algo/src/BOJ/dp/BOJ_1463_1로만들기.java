package BOJ.dp;

import java.util.Scanner;

public class BOJ_1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int X = sc.nextInt();
		int arr[] = new int[X + 1];
		for (int i = 2; i <= X; i++) {
			int minus;
			int two = Integer.MAX_VALUE;
			int three = Integer.MAX_VALUE;

			if (i % 3 == 0)
				three = arr[i / 3] + 1;
			if (i % 2 == 0)
				two = arr[i / 2] + 1;
			minus = arr[i - 1] + 1;

			int result = Math.min(Math.min(two, three), minus);

			arr[i] = result;
		}
		System.out.println(arr[X]);
	}
}
