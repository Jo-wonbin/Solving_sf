package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		int cost[][] = new int[1001][3];
		int D[][] = new int[1001][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		D[1][0] = cost[1][0];
		D[1][1] = cost[1][1];
		D[1][2] = cost[1][2];
		for (int i = 2; i <= N; i++) {
			D[i][0] = Math.min(D[i - 1][1], D[i - 1][2]) + cost[i][0];
			D[i][1] = Math.min(D[i - 1][0], D[i - 1][2]) + cost[i][1];
			D[i][2] = Math.min(D[i - 1][0], D[i - 1][1]) + cost[i][2];
		}

		System.out.println(Math.min(Math.min(D[N][0], D[N][1]), D[N][2]));
		
		br.close();
	}

}
