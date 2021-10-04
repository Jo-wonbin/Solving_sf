package BOJ.dp;

import java.util.Scanner;

public class BOJ_2133_타일채우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int arr[] = new int[31];

		arr[0] = 1;
		arr[2] = 3;

		for (int i = 4; i <= N; i += 2) {
			arr[i] = arr[i - 2] * 3;
			for (int j = i - 4; j >= 0; j -= 2) {
				arr[i] += arr[j] * 2;
			}
		}

		System.out.println(arr[N]);
		sc.close();
	}

}
