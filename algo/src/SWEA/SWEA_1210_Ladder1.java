package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class location {
	int x;
	int y;

	location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_1210_Ladder1 {

	static Queue<location> q = new LinkedList<>();
	static int dx[] = { 0, 0, -1 }; // 우좌상
	static int dy[] = { 1, -1, 0 };
	static int result = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int cnt = 1;
		while (cnt <= 10) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			int map[][] = new int[100][100];
			boolean check[][] = new boolean[100][100];

			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) {
						q.add(new location(i, j)); // 도착점 위치 저장
						check[i][j] = true;
					}
				}
			}

			bfs(map, check);

			bw.write("#" + n + " " + result + "\n");
			result = 0;
			q.clear();
			cnt++;
		}

		br.close();
		bw.flush();
		bw.close();
	}

	static void bfs(int map[][], boolean check[][]) {
		while (!q.isEmpty()) {

			location now = q.poll();

			//x 좌표가 0이면
			if (now.x == 0) {
				result = now.y;
				return;
			}

			for (int h = 0; h < 3; h++) {
				int nx = now.x + dx[h]; // 좌우상 검사
				int ny = now.y + dy[h];

				if (nx < 0 || ny < 0 || nx > 99 || ny > 99)
					continue;
				if (check[nx][ny])
					continue;
				if (map[nx][ny] == 0)
					continue;

				// 위로 가는게 아니고 좌, 우에 길이 있는 경우 위로 가는 길을 지움
				if (h != 2 && map[nx][ny] == 1) {
					check[nx][ny] = true;
					q.offer(new location(nx, ny));
					break;
				}

				check[nx][ny] = true;
				q.offer(new location(nx, ny));

			}
		}
	}

}