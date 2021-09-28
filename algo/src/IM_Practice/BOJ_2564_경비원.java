package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564_경비원 {

	static class Point {
		int dir, x;

		public Point(int dir, int x) {
			super();
			this.dir = dir;
			this.x = x;
		}
	}

	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		int size = Integer.parseInt(br.readLine());

		Point p[] = new Point[size];
		int dir;
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int tp = Integer.parseInt(st.nextToken());
			if (tp == 3) {
				dir = 2;
			} else if (tp == 2) {
				dir = 3;
			} else {
				dir = tp;
			}
			int x = Integer.parseInt(st.nextToken());

			p[i] = new Point(dir, x);
		}

		st = new StringTokenizer(br.readLine());
		int tp = Integer.parseInt(st.nextToken());
		if (tp == 3) {
			dir = 2;
		} else if (tp == 2) {
			dir = 3;
		} else {
			dir = tp;
		}
		Point now = new Point(dir, Integer.parseInt(st.nextToken()));

		int sum = 0;
		int temp = 0;
		for (int i = 0; i < size; i++) {
			if (p[i].dir == now.dir) {
				temp = Math.abs(p[i].x - now.x);
			} else if (Math.abs(p[i].dir - now.dir) == 2) {
				temp = Math.min(clockCycle(now, p[i], 2), reverseClock(now, p[i], 2));
			} else if (Math.abs(p[i].dir - now.dir) != 2 && p[i].dir != now.dir) {
				if (now.dir == 1) {
					if (p[i].dir == 2) {
						temp = reverseClock(now, p[i], 1);
					} else {
						temp = clockCycle(now, p[i], 1);
					}
				} else if (now.dir == 2) {
					if (p[i].dir == 3) {
						temp = reverseClock(now, p[i], 1);
					} else {
						temp = clockCycle(now, p[i], 1);
					}
				} else if (now.dir == 3) {
					if (p[i].dir == 4) {
						temp = reverseClock(now, p[i], 1);
					} else {
						temp = clockCycle(now, p[i], 1);
					}
				} else if (now.dir == 4) {
					if (p[i].dir == 1) {
						temp = reverseClock(now, p[i], 1);
					} else {
						temp = clockCycle(now, p[i], 1);
					}
				}
			}
			sum += temp;
		}

		System.out.println(sum);
		br.close();
	}

	static int clockCycle(Point start, Point des, int flag) {
		int sum = 0;

		if (flag == 1) {
			switch (start.dir) {
			case 1:
				sum += m - start.x + des.x;
				break;
			case 2:
				sum += start.x + des.x;
				break;
			case 3:
				sum += start.x + n - des.x;
				break;
			case 4:
				sum += n - start.x + m - des.x;
				break;
			}
		} else {
			switch (start.dir) {
			case 1:
				sum += (m - start.x) + n + (m - des.x);
				break;
			case 2:
				sum += start.x + m + des.x;
				break;
			case 3:
				sum += start.x + n + des.x;
				break;
			case 4:
				sum += (n - start.x) + m + (n - des.x);
				break;
			}
		}

		return sum;
	}

	static int reverseClock(Point start, Point des, int flag) {
		int sum = 0;

		if (flag == 1) {
			switch (start.dir) {
			case 1:
				sum += start.x + des.x;
				break;
			case 2:
				sum += n - start.x + des.x;
				break;
			case 3:
				sum += m - start.x + n - des.x;
				break;
			case 4:
				sum += start.x + m - des.x;
				break;
			}
		} else {
			switch (start.dir) {
			case 1:
				sum += start.x + n + des.x;
				break;
			case 2:
				sum += n - start.x + m + n - des.x;
				break;
			case 3:
				sum += m - start.x + n + m - des.x;
				break;
			case 4:
				sum += start.x + m + des.x;
				break;
			}
		}
		return sum;
	}
}
