package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579_계단오르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int stair[] = new int[301];
		int D[] = new int[301];

		for (int i = 1; i <= N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}

		D[1] = stair[1];
		D[2] = stair[1] + stair[2];
		D[3] = Math.max(stair[1], stair[2]) + stair[3];

		for (int i = 4; i <= N; i++) {
			D[i] = Math.max(D[i - 3] + stair[i - 1], D[i - 2]) + stair[i];
		}

		System.out.println(D[N]);
		br.close();
	}

}
