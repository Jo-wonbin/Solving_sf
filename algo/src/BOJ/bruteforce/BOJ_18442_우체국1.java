package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18442_우체국1 {

	static int N, P;
	static long L, result;
	static long temp[];
	static long res[];
	static long list[];
	static long D[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력받음
		StringBuilder sb = new StringBuilder(); // 문자열로 한번에 저장
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine(), " "); // 문자열 파싱
		N = Integer.parseInt(st.nextToken()); // 마을 갯수
		P = Integer.parseInt(st.nextToken()); // 경찰서 갯수
		L = Long.parseLong(st.nextToken()); // 순환길의 길이

		temp = new long[P]; // 임시 저장 배열
		res = new long[P]; // 경찰서 세울 마을 저장 배열
		list = new long[N]; // 마을 리스트 저장 배열
		D = new long[N][N]; // 마을 간 거리 계산 배열

		st = new StringTokenizer(br.readLine(), " "); // 문자열 파싱
		for (int i = 0; i < N; i++) {
			list[i] = Long.parseLong(st.nextToken());
		}

		for (int i = 0; i < N; i++) { // 마을 간 거리를 계산해서 2차원 배열에 담아둔다.
			for (int j = 0; j < N; j++) {
				if (i >= j) // j가 i보다 커야 작동
					continue;
				// 마을 간 최소 거리 저장
				D[i][j] = (long) Math.min((long) Math.abs(list[i] - list[j]), L - (long) Math.abs(list[i] - list[j]));
				D[j][i] = D[i][j];
			}
		}

		result = Long.MAX_VALUE; // 이동 거리 최소값 초기화
		Comb(0, 0); // 조합

		sb.append(result).append("\n"); // 출력 형식 저장
		for (int i = 0; i < P; i++) { // 경찰서가 세워질 마을 정보 순서대로 저장
			sb.append(res[i]).append(" ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb); // 출력
		br.close(); // 입력 끝
	}

	private static void Comb(int cnt, int num) {
		if (cnt == P) { // cnt 가 경찰서 갯수이면 return

			long sum = 0; // 최솟값 비교할 변수
			for (int i = 0; i < N; i++) { // 각 마을에서 경찰서 최소 거리를 저장
				long now = Long.MAX_VALUE; // 지금 마을에서 경찰서 최소거리를 저장할 변수
				for (int j = 0; j < P; j++) { // 현재 선택된 마을과 최소거리 계산
					now = Math.min(now, D[i][(int) temp[j]]); // now와 현재 마을과 경찰서가 있는 마을 거리 계산
				}
				sum += now; // sum에 최소거리 저장
			}
			if (result > sum) { // 현재 최소거리보다 지금 거리가 작다면
				result = sum; // 최소거리 갱신하고
				for (int i = 0; i < P; i++) { // 최소거리를 만족하는 마을 리스트 res배열에 저장
					res[i] = list[(int) temp[i]];
				}
			}
			return;
		}

		for (int i = num; i < N; i++) { // 차례대로 순서 상관없이 조합
			temp[cnt] = i; // temp에 해당 마을 인덱스 저장
			Comb(cnt + 1, i + 1);
		}
	}
}
