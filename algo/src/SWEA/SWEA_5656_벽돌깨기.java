package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {

	static class Point {
		int x, y, count;

		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

	static int dropNum, N, M, result;
	static int map[][];
	static int temp[];
	static Queue<Point> q = new LinkedList<>();
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int tc = Integer.parseInt(st.nextToken());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			dropNum = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			map = new int[N + 1][M + 1];
			temp = new int[dropNum];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			dfs(0);

			sb.append("#").append(k).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	private static void dfs(int cnt) {
		if (cnt == dropNum) {
			int copy[][] = copyMap(map);
			for (int i = 0; i < dropNum; i++) {
				bfs(temp[i], copy);
				down(copy);
			}
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (copy[i][j] > 0)
						sum++;
				}
			}
			result = Math.min(result, sum);
			return;
		}

		for (int i = 1; i <= M; i++) {
			temp[cnt] = i;
			dfs(cnt + 1);
		}
	}

	private static void bfs(int drop, int arr[][]) {
		boolean chk[][] = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			if (arr[i][drop] != 0) {
				q.add(new Point(i, drop, arr[i][drop]));
				chk[i][drop] = true;
				arr[i][drop] = 0;
				break;
			}
		}

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int h = 0; h < 4; h++) {
				int nx = now.x;
				int ny = now.y;

				int cnt = now.count - 1;
				while (cnt-- > 0) {
					nx += dx[h];
					ny += dy[h];
					if (nx < 1 || ny < 1 || nx > N || ny > M)
						break;
					if (arr[nx][ny] == 0)
						continue;
					if (chk[nx][ny])
						continue;
					q.add(new Point(nx, ny, arr[nx][ny]));
					arr[nx][ny] = 0;
					chk[nx][ny] = true;
				}
			}
		}
	}

	private static void down(int arr[][]) {
		for (int i = 1; i <= M; i++) {
			int temp[] = new int[N + 1];
			int cnt = 0;
			for (int j = N; j >= 1; j--) {
				if (arr[j][i] != 0) {
					temp[cnt++] = arr[j][i];
					arr[j][i] = 0;
				}
			}
			int count = 0;
			while (count < cnt) {
				arr[N - count][i] = temp[count];
				count++;
			}
		}
	}

	private static int[][] copyMap(int map[][]) {
		int temp[][] = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}
}
