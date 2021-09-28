package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {

	static class Point implements Comparable<Point> {
		int x, y, size, count;

		public Point(int x, int y, int size, int count) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.count = count;
		}

		@Override
		public int compareTo(Point o) {
			if (this.count > o.count) {
				return 1;
			} else if (this.count == o.count) {
				if (this.x > o.x) {
					return 1;
				} else if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return -1;
				}
			} else {
				return this.count - o.count;
			}
		}
	}

	static int N, result, temp;
	static int[][] map;
	static boolean[][] chk;
	// 상어 위치 저장 point
	static Point sharkPoint;
	static Queue<Point> q = new LinkedList<>();
	static int dx[] = { -1, 0, 0, 1 };
	static int dy[] = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkPoint = new Point(i, j, 2, 0);
				}
			}
		}

		result = 0;
		//상어크기
		int sharkSize = 2;
		//현재 먹은 물고기 수
		int eatFishCount = 0;
		while (true) {
			chk = new boolean[N][N];
			chk[sharkPoint.x][sharkPoint.y] = true;
			map[sharkPoint.x][sharkPoint.y] = 0;

			// 물고기를 상어크기만큼 잡아먹었으면 상어크기 + 1
			if (eatFishCount == sharkSize) {
				eatFishCount = 0;
				q.add(new Point(sharkPoint.x, sharkPoint.y, ++sharkSize, 0));
			} else {
				q.add(new Point(sharkPoint.x, sharkPoint.y, sharkSize, 0));
			}
			// 더 이상 잡아먹을 물고기 없는지 확인
			if (!bfs()) {
				break;
			}
			eatFishCount++;
			result += sharkPoint.count;
			q.clear();
		}
		System.out.println(result);
		br.close();
	}

	private static boolean bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean flag = false;
		temp = 0;
		while (!q.isEmpty()) {
			Point now = q.poll();

			// 현재 물고기 잡아먹은 위치보다 크다면 멈춤
			if (temp != 0 && now.count > temp)
				break;

			for (int h = 0; h < 4; h++) {
				int nx = now.x + dx[h];
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					continue;
				if (chk[nx][ny])
					continue;
				if (map[nx][ny] > now.size)
					continue;
				// 물고기가 없거나 상어와 물고기 크기가 같으면 통과
				if (map[nx][ny] == 0 || map[nx][ny] == now.size) {
					q.add(new Point(nx, ny, now.size, now.count + 1));
					chk[nx][ny] = true;
					continue;
				}

				// 잡아먹을 수 있으면 pq에 넣음
				if (map[nx][ny] < now.size) {
					pq.add(new Point(nx, ny, now.size, now.count + 1));
					if (temp == 0) {
						temp = now.count + 1;
					} else {
						temp = Math.min(now.count + 1, temp);
					}
					chk[nx][ny] = true;
					flag = true;
				}
			}
		}
		if (flag) {
			// 제일 위쪽에서 제일 왼쪽 물고기 잡아먹음
			sharkPoint = pq.poll();
			map[sharkPoint.x][sharkPoint.y] = 9;
		}
		return flag;
	}
}
