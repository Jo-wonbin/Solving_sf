package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {

	static class Shark {
		int x, y, speed, dir, size;

		public Shark(int x, int y, int speed, int dir, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	static int N, M;
	static Shark[] sharkList;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int snum = Integer.parseInt(st.nextToken());

		// 상어의 정보를 각각 인덱스에 저장
		sharkList = new Shark[snum + 1];
		map = new int[N + 1][M + 1];

		for (int i = 1; i <= snum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			// 상어 리스트 저장
			sharkList[i] = new Shark(r, c, speed, dir, size);

			// 낚시터에 상어 리스트의 인덱스 저장
			map[r][c] = i;
		}

		int result = 0;
		// 낚시꾼 오른쪽으로 한 칸 이동(열 이동)
		for (int i = 1; i <= M; i++) {
			// 상어를 잡음(행 이동)
			for (int j = 1; j <= N; j++) {
				if (map[j][i] > 0) { // 낚시꾼이 상어를 발견하면
					result += sharkList[map[j][i]].size; // 상어 크기 증가
					sharkList[map[j][i]] = null; // 해당 상어 정보 삭제
					break;
				}
			}
			// 새로 상어의 위치를 저장하기 위해 재 선언
			map = new int[N + 1][M + 1];

			// 상어 이동 시작
			for (int k = 1; k <= snum; k++) {
				// 잡히거나 잡아먹힌 상어 제외
				if (sharkList[k] == null)
					continue;

				moveShark(k); // 상어 이동
			}
		}
		System.out.println(result);

		br.close();
	}

	private static void moveShark(int idx) {

		Shark now = sharkList[idx]; // 이동할 상어 정보 저장

		int row = now.x;
		int col = now.y;
		int dir = now.dir;

		if (dir == 1 || dir == 2) { // 상하 이동
			int move = now.speed % ((N - 1) * 2); // 속도와 (N-1) * 2 값이 같으면 제자리 
			while (move-- > 0) {
				if (row == 1) { // 벽을 보고 있으면 방향을 바꿔줌
					dir = 2;
				}
				if (row == N) { // 벽을 보고 있으면 방향을 바꿔줌
					dir = 1;
				}
				if (dir == 1) // 방향에 따라 이동
					row -= 1;
				else {
					row += 1;
				}
			}
		} else {
			int move = now.speed % ((M - 1) * 2);
			while (move-- > 0) {
				if (col == 1) {
					dir = 3;
				}
				if (col == M) {
					dir = 4;
				}
				if (dir == 3)
					col += 1;
				else {
					col -= 1;
				}
			}
		}

		if (map[row][col] > 0) { // 도착한 곳에 상어가 있다면
			// 상어의 크기 비교
			if (sharkList[map[row][col]].size > now.size) { // 칸에 있는 상어가 지금 상어보다 크면
				sharkList[idx] = null; // 지금 상어 삭제
			} else { // 작으면
				sharkList[map[row][col]] = null; // 칸에 있는 상어 삭제
				map[row][col] = idx; // 칸에 현재 상어 위치
				sharkList[idx] = new Shark(row, col, now.speed, dir, now.size); // 좌표 방향 갱신
			}
		} else { // 상어가 없다면
			map[row][col] = idx; // 상어 위치 저장
			sharkList[idx] = new Shark(row, col, now.speed, dir, now.size); // 좌표 방향 갱신
		}
	}
}
