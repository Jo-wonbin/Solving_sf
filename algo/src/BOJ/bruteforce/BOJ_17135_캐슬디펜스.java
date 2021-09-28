package BOJ.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {

	static class Point implements Comparable<Point> {
		int x, y, d, index;
		boolean flag;

		public Point(int x, int y, boolean flag) {
			this.x = x;
			this.y = y;
			this.flag = flag;
		}

		public Point(int y, int d, int index) {
			this.y = y;
			this.d = d;
			this.index = index;
		}

		@Override
		public int compareTo(Point o) {
			if (o.d == this.d) {
				return this.y - o.y;
			} else
				return this.d - o.d;
		}
	}

	static ArrayList<Point> enemy = new ArrayList<>();
	static int N, M, D, max;
	static Point[] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int tp = Integer.parseInt(st.nextToken());
				if (tp == 0)
					continue;
				enemy.add(new Point(i, j, true));
			}
		}
		temp = new Point[3];
		max = Integer.MIN_VALUE;
		find(0, 0, 0);

		System.out.println(max);
		br.close();
	}

	private static ArrayList<Point> copy(ArrayList<Point> al) {
		ArrayList<Point> copy = new ArrayList<Point>();
		for (Point tp : al) {
			copy.add(new Point(tp.x, tp.y, tp.flag));
		}
		return copy;
	}

	private static void find(int cnt, int num, int flag) {
		if (cnt == 3) {
			check();
			return;
		}
		for (int i = num; i < M; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			temp[cnt] = new Point(N, i, false);
			find(cnt + 1, i + 1, flag | 1 << i);
		}
	}

	private static void check() {
		ArrayList<Point> now = copy(enemy);
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int size = now.size();
		int deathCount = 0;

		boolean flag = true;
		while (flag) {
			// 궁수 3명이서 적을 쏨.
			flag = false;
			int shoot[] = new int[3];
			Arrays.fill(shoot, -1);
			for (int i = 0; i < 3; i++) {
				Point tp = temp[i];
				for (int j = 0; j < size; j++) {
					// 살아있는 병사만
					Point sol = now.get(j);
					if (!sol.flag)
						continue;
					int d = Math.abs(sol.x - tp.x) + Math.abs(sol.y - tp.y);
					// 거리 안 쪽이면 실행
					if (D < d)
						continue;
					// 우선순위 큐에 넣어서 비교
					pq.add(new Point(sol.y, d, j));
				}
				// pq에 값이 있으면 해당 병사 죽이고 카운트 +1

				if (!pq.isEmpty()) {
					int index = pq.poll().index;
					shoot[i] = index;
					pq.clear();
				}

			}

			// 쏴서 죽임
			int cnt = 0;
			for (int i = 0; i < 3; i++) {
				if (shoot[i] == -1)
					continue;
				if (!now.get(shoot[i]).flag)
					continue;
				now.get(shoot[i]).flag = false;
				cnt++;

			}
			deathCount += cnt;
			// 살아있는 병사들이 전진
			for (int i = 0; i < size; i++) {
				if (!now.get(i).flag)
					continue;
				if (now.get(i).x + 1 == N)
					now.get(i).flag = false;
				else
					now.get(i).x += 1;
			}
			// 남아있는 병사 체크
			for (int i = 0; i < size; i++) {
				if (!now.get(i).flag)
					continue;
				flag = true;
				break;
			}
		}
		max = Math.max(deathCount, max);
	}
}
