package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1251_하나로 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		String a[];

		for (int k = 1; k <= tc; k++) {
			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][2];
			boolean[] chk = new boolean[N];
			double[] minEdge = new double[N];
			double E;

			for (int i = 0; i < 2; i++) {
				a = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[j][i] = Integer.parseInt(a[j]);
				}
			}

			E = Double.parseDouble(br.readLine());

			for (int j = 0; j < N; j++) {
				minEdge[j] = Double.MAX_VALUE;
			}

			Double result = 0.0; // 최소신장트리 비용
			minEdge[0] = 0; // 임의의 시작점 0의 간선비용을 0으로 세팅

			for (int i = 0; i < N; i++) {
				// 1. 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 찾기
				double min = Double.MAX_VALUE;
				int minVertex = -1; // 최소간선비용의 정점번호
				for (int j = 0; j < N; j++) {
					if (!chk[j] && min > minEdge[j]) {
						min = minEdge[j];
						minVertex = j;
					}
				}

				chk[minVertex] = true; // 신장트리에 포함시킴
				result += Math.pow(min, 2) * E; // 간선비용 누적

				// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 비용 최소로 업데이트
				for (int j = 0; j < N; j++) {
					double distance = Math.sqrt(
							Math.pow(map[j][0] - map[minVertex][0], 2) + Math.pow(map[j][1] - map[minVertex][1], 2));
					if (!chk[j] && minEdge[j] > distance) {
						minEdge[j] = distance;
					}
				}
			}
			sb.append("#").append(k).append(" ").append(Math.round(result)).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

}
