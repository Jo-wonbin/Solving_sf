package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {

	static int arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		arr = new int[5][9];

		for (int i = 1; i <= 4; i++) {
			String a = br.readLine();
			for (int j = 1; j <= 8; j++) {
				arr[i][j] = a.charAt(j - 1) - '0';
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken()); // 돌릴 톱니
			int dis = Integer.parseInt(st.nextToken()); // 방향 -1 : 반시계, 1 : 시계

			relay(idx, dis == 1 ? true : false);
		}

		int result = arr[1][1] + (arr[2][1] << 1) + (arr[3][1] << 2) + (arr[4][1] << 3);

		System.out.println(result);
		br.close();
	}

	private static void relay(int idx, boolean dis) {
		switch (idx) {
		case 1:
			if (check(1, 2)) { // 2 번이 돌아야하면
				if (check(2, 3)) { // 3번이 돌아야 한다면
					if (check(3, 4)) { // 4번이 돌아야한다면
						rotate(4, !dis);
					}
					rotate(3, dis);
				}
				rotate(2, !dis);
			}
			rotate(1, dis);
			break;
		case 2:
			if (check(2, 1)) {
				rotate(1, !dis);
			}
			if (check(2, 3)) {
				if (check(3, 4)) {
					rotate(4, dis);
				}
				rotate(3, !dis);
			}
			rotate(2, dis);
			break;
		case 3:
			if (check(3, 4)) {
				rotate(4, !dis);
			}
			if (check(3, 2)) {
				if (check(2, 1)) {
					rotate(1, dis);
				}
				rotate(2, !dis);
			}
			rotate(3, dis);
			break;
		case 4:
			if (check(4, 3)) {
				if (check(3, 2)) {
					if (check(2, 1)) {
						rotate(1, !dis);
					}
					rotate(2, dis);
				}
				rotate(3, !dis);
			}
			rotate(4, dis);
			break;

		}
	}

	private static boolean check(int start, int des) {
		if (start < des) {
			if (arr[start][3] != arr[des][7]) {
				return true;
			}
		} else {
			if (arr[start][7] != arr[des][3]) {
				return true;
			}
		}
		return false;
	}

	private static void rotate(int idx, boolean dis) {
		if (dis) { // 시계 방향
			int temp = arr[idx][8];
			for (int i = 8; i >= 2; i--) {
				arr[idx][i] = arr[idx][i - 1];
			}
			arr[idx][1] = temp;
		} else { // 반시계 방향
			int temp = arr[idx][1];
			for (int i = 1; i <= 7; i++) {
				arr[idx][i] = arr[idx][i + 1];
			}
			arr[idx][8] = temp;
		}
	}
}
