package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8458_원점으로집합 {

	// 맨해튼 거리의 최댓값 저장
	static long max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());

			boolean flag = true; // 짝, 모든 좌표가 원점과의 맨해튼 거리가 모두 짝 아니면 홀이어야 한다.
			long result = 0L;
			max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				long x = Long.parseLong(st.nextToken());
				long y = Long.parseLong(st.nextToken());

				long temp = Math.abs(x - 0L) + Math.abs(y - 0L); // 맨해튼 거리 계산
				max = Math.max(max, temp); // 최댓값 비교
				if (i == 0) { // 첫 번째 들어온 좌표의 맨해튼 거리가 홀인지 짝인지 판별
					if (temp % 2 == 1) { // 
						flag = false;
					}
				} else { // 첫 번쨰 이 후 들어온 좌표들은 홀 짝이 다르면 원점에 도달할 수 없으므로 -1
					if (flag) {
						if (temp % 2 == 1) {
							result = -1L;
						}
					} else {
						if (temp % 2 == 0) {
							result = -1L;
						}
					}
				}
			}
			if (result != -1L) {// 원점에 들어올 수 있을 때
				if (max != 0) // 최대 맨해튼 거리가 0이 아니면
					result = find(); // 메소드 실행
				else
					result = 0;
			}

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static long find() {

		long cnt = 1L; // 이동 횟수
		long sum = 0L; // 누적 이동 거리
		while (true) {
			sum += cnt;

			if (max <= sum) { // 최댓값보다 누적 이동 거리가 크거나 같을 때 이동거리-최댓값이 짝수여야만 원점 도달 가능
				if ((sum - max) % 2 == 0) // 짝수면 종료
					break;
			}

			cnt++;
		}
		return cnt; // 최소 시간 반환
	}
}
