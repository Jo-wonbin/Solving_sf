package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {

	static int D[][];
	static int N, result;
	static int list[];
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());

		D = new int[N + 1][N + 1]; // 선거구에서 다른 선거구로 갈 수 있는지 여부
		list = new int[N + 1]; // 선거구의 인구 저장

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				D[i][temp] = 1; // 다른 선거구 갈 수 있으면 1로 표시
			}
		}

		boolean arr[] = new boolean[N + 1]; // 선거구를 나눌 배열

		result = -1;

		find(1, arr.clone());
		System.out.println(result);

		br.close();
	}

	private static void find(int cnt, boolean[] arr) {
		// 선거구 구분이 끝나면 두 구역으로 나뉘었는지 확인하고 bfs
		if (cnt == N + 1) {
			boolean flag = false;
			for (int i = 2; i <= N; i++) {
				if (arr[1] != arr[i]) { // 두 구역이 나뉘었다면
					flag = true;
					break;
				}
			}
			if (flag) {
				bfs(arr); // 탐색 시작
			}
			return;
		}

		// 부분 집합
		find(cnt + 1, arr.clone());
		arr[cnt] = true;
		find(cnt + 1, arr.clone());
	}

	private static void bfs(boolean arr[]) {
		int sum1 = 0; // 선거구 1의 인구수
		int sum2 = 0; // 선거구 2의 인구수
		boolean chk[] = new boolean[N + 1]; // 선거구를 모두 포함하는지 여부

		// 1 선거구
		for (int i = 1; i <= N; i++) {
			if (arr[i]) { // 선거구가 1인 것만 선택
				chk[i] = true;
				q.add(i);
				sum1 += list[i]; // 인구수 저장
				while (!q.isEmpty()) {
					int now = q.poll();

					for (int j = 1; j <= N; j++) {
						if (!arr[j]) // 선거구 2면 패스
							continue;
						if (now == j) // 지금 지역이면 패스
							continue;
						if (chk[j]) // 이미 방문했으면 체크
							continue;
						if (D[now][j] == 0) // 다른 선거지역으로 갈 수 없다면 패스
							continue;
						sum1 += list[j]; // 인구수 저장
						chk[j] = true; // 방문 체크
						q.add(j); // 큐에 넣음
					}
				}
				break;
			}
		}

		// 2 선거구
		for (int i = 1; i <= N; i++) {
			if (!arr[i]) {
				q.add(i);
				chk[i] = true;
				sum2 += list[i];
				while (!q.isEmpty()) {
					int now = q.poll();

					for (int j = 1; j <= N; j++) {
						if (arr[j])
							continue;
						if (now == j)
							continue;
						if (chk[j])
							continue;
						if (D[now][j] == 0)
							continue;
						sum2 += list[j];
						chk[j] = true;
						q.add(j);
					}
				}
				break;
			}
		}

		// 선거구를 하나라도 방문하지 못하면 return
		for (int i = 1; i <= N; i++) {
			if (chk[i] == false) {
				return;
			}
		}
		int temp = Math.abs(sum1 - sum2);
		if (result == -1) {
			result = temp;
		} else {
			result = Math.min(result, temp);
		}
	}
}
