package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17141_연구소2 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, result;
	static boolean flag = false;
	static int map[][];
	static int chk[][];
	static Point[] temp;
	static Queue<Point> q = new LinkedList<>();
	static List<Point> viruslist = new ArrayList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					viruslist.add(new Point(i, j)); // 바이러스 좌표들 저장
			}
		}
		temp = new Point[M];

		result = Integer.MAX_VALUE;
		Comb(0, 0);

		if (flag)
			System.out.println(result);
		else
			System.out.println(-1);
		br.close();

	}

	static void Comb(int cnt, int num) {
		if (cnt == M) {
			virus();
			return;
		}

		for (int i = num; i < viruslist.size(); i++) {
			temp[cnt] = viruslist.get(i);
			Comb(cnt + 1, i + 1);
		}

	}

	static void virus() {
		int arr[][] = copy(map);
		chk = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 1) {
					chk[i][j] = -1;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			q.add(temp[i]);
			chk[temp[i].x][temp[i].y] = 0;
		}

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					continue;
				if (chk[nx][ny] > -1)
					continue;
				if (arr[nx][ny] == 1)
					continue;

				chk[nx][ny] = chk[now.x][now.y] + 1;
				q.add(new Point(nx, ny));
			}
		}

		int cnt = Integer.MIN_VALUE;
		boolean ok = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (chk[i][j] == -1) {
					ok = false;
					break;
				}
				if (cnt < chk[i][j]) {
					cnt = chk[i][j];
				}
			}
		}
		if (ok) {
			flag = true;
			result = Math.min(cnt, result);
		}
	}

	static int[][] copy(int[][] arr) {
		int temp[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		return temp;
	}
}
