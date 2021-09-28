package BOJ.recursive;

import java.math.BigInteger;
import java.util.Scanner;

public class BOJ_11729_하노이탑이동순서 {

	static StringBuffer bf = new StringBuffer();

	public static void hanoi(int from, int temp, int dest, int num) {
		// 맨 위의 블럭을 옮길 경우 출력 후 스택을 빠져나감
		if (num == 1) {
			bf.append(from + " " + dest + "\n");
			return;
		} else {
			// 임시 기둥에 저장
			hanoi(from, dest, temp, num - 1);
			// 맨 윗 블럭 움직임 저장
			bf.append(from + " " + dest + "\n");
			//  임시기둥의 블럭을 목적기둥에 옮김
			hanoi(temp, from, dest, num - 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.close();

		BigInteger N = new BigInteger("2").pow(n).subtract(BigInteger.ONE);
		bf.append(N + "\n");

		hanoi(1, 2, 3, n);

		System.out.println(bf.toString());
	}

}
