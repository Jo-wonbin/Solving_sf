package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA_1873_상호의배틀필드 {
	static class tank {
		int x, y;
		
		public tank(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int H, W;
	static char[][] map;
	static char[] chk;
	static tank now;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String a = br.readLine();
		int N = Integer.parseInt(a);

		int cnt = 1;
		while (cnt <= N) {
			st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String c = br.readLine();
				map[i] = c.toCharArray();
			}

			String b = br.readLine();
			int num = Integer.parseInt(b);

			chk = new char[num];
			String d = br.readLine();
			for(int i=0; i<chk.length; i++) {
				chk[i] = d.charAt(i);				
			}

			//탈출 조건 다시보기
			Loop1: for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
						now = new tank(i, j);
						start(num);
						break Loop1;
					}
				}
			}
			System.out.printf("#%d ", cnt);
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			cnt++;
		}

		br.close();
	}

	public static void start(int num) {
		for (int i = 0; i < num; i++) {
			char dir = chk[i];

			if (dir == 'U') {
				map[now.x][now.y] = '^';

				if (now.x - 1 < 0)
					continue;

				if (map[now.x - 1][now.y] == '.') {
					map[now.x - 1][now.y] = '^';
					map[now.x][now.y] = '.';
					now.x -= 1;
				}
			} else if (dir == 'D') {
				map[now.x][now.y] = 'v';

				if (now.x + 1 > H - 1)
					continue;

				if (map[now.x + 1][now.y] == '.') {
					map[now.x + 1][now.y] = 'v';
					map[now.x][now.y] = '.';
					now.x += 1;
				}
			} else if (dir == 'L') {
				map[now.x][now.y] = '<';
				if (now.y - 1 < 0)
					continue;

				if (map[now.x][now.y - 1] == '.') {
					map[now.x][now.y - 1] = '<';
					map[now.x][now.y] = '.';
					now.y -= 1;
				}
			} else if (dir == 'R') {
				map[now.x][now.y] = '>';
				if (now.y + 1 > W - 1)
					continue;
				if (map[now.x][now.y + 1] == '.') {

					map[now.x][now.y + 1] = '>';
					map[now.x][now.y] = '.';
					now.y += 1;
				}
			} else if (dir == 'S') {
				char temp = map[now.x][now.y];
				if (temp == '^') {
					for (int j = now.x; j >= 0; j--) {
						if (map[j][now.y] == '#')
							break;
						if (map[j][now.y] == '*') {
							map[j][now.y] = '.';
							break;
						}
					}
				} else if (temp == 'v') {
					for (int j = now.x; j < H; j++) {
						if (map[j][now.y] == '#')
							break;
						if (map[j][now.y] == '*') {
							map[j][now.y] = '.';
							break;
						}
					}
				} else if (temp == '<') {
					for (int j = now.y; j >= 0; j--) {
						if (map[now.x][j] == '#')
							break;
						if (map[now.x][j] == '*') {
							map[now.x][j] = '.';
							break;
						}
					}
				} else if (temp == '>') {
					for (int j = now.y; j < W; j++) {
						if (map[now.x][j] == '#')
							break;
						if (map[now.x][j] == '*') {
							map[now.x][j] = '.';
							break;
						}
					}
				}
			}
		}
	}
}
