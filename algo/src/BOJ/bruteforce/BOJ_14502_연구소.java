package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, result;
	static int map[][];
	static boolean chk[][];
	static Queue<Point> q = new LinkedList<>();
	static List<Point> viruslist = new ArrayList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					viruslist.add(new Point(i, j)); // 바이러스 좌표들 저장
			}
		}

		result = Integer.MIN_VALUE;
//		wallPerm(0);
		wallComb(0, 0);

		System.out.println(result);
		br.close();
	}

	static void wallPerm(int cnt) { // 순열
		if (cnt == 3) {
			virus();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wallPerm(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	static void wallComb(int cnt, int num) { // 조합
		if (cnt == 3) {
			virus();
			return;
		}

		for (int i = num; i < N * M; i++) {
			if (map[i / M][i % M] == 0) {
				map[i / M][i % M] = 1;
				wallComb(cnt + 1, i + 1);
				map[i / M][i % M] = 0;
			}
		}
	}

	static void virus() {
		int arr[][] = copy(map);
		chk = new boolean[N][M];
		for (Point a : viruslist) {
			q.add(a);
			chk[a.x][a.y] = true;
		}

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
					continue;
				if (chk[nx][ny])
					continue;
				if (arr[nx][ny] == 1 || arr[nx][ny] == 2)
					continue;

				chk[nx][ny] = true;
				arr[nx][ny] = 2;
				q.add(new Point(nx, ny));
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0)
					cnt++;
			}
		}

		result = Math.max(result, cnt);
	}

	static int[][] copy(int[][] arr) {
		int temp[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		return temp;
	}
}
