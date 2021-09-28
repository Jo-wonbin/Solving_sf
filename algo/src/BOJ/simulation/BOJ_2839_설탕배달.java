package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2839_설탕배달 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		if (n == 4 || n == 7) {
			System.out.println(-1);
		} else if (n % 5 == 0) {
			System.out.println(n / 5);
		} else if (n % 5 == 1 || n % 5 == 3) {// 나머지 1은 5하나 빼고 3 2번
			System.out.println(n / 5 + 1);// == 5로 나누고 1더한 값과 같음
		} else if (n % 5 == 2 || n % 5 == 4) {// 나머지 4는 5로 나눈 몫 하나 빼고 3 3번더함 , 나머지 2는 5로 나눈 몫 2개 빼고 3 4번더함
			System.out.println(n / 5 + 2);// == 5로 나누고 2 더한 값과 같음.
		}

		br.close();
	}

}
