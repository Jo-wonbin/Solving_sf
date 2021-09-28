package BOJ.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {

	static int N, M, max;
	static int dx[] = { -1, 0, 1 };
	static int dy[] = { 1, 1, 1 };
	// 파이프연결하면 flag를 false로 해서 함수를 끝냄.
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int map[][] = new int[N][M];

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			for (int j = 0; j < M; j++) {
				if (a.charAt(j) == 'x')
					map[i][j] = -1;
			}
		}

		max = 0;
		for (int i = 0; i < N; i++) {
			flag = true;
			find(1, i, 0, i + 1, map);
		}

		bw.write(max + "");
		br.close();
		bw.flush();
		bw.close();
	}

	static void find(int cnt, int x, int y, int index, int[][] map) {
		if (cnt == M) {
			flag = false;
			max++;
			return;
		}

		for (int h = 0; h < 3; h++) {
			int nx = x + dx[h];
			int ny = y + dy[h];

			if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
				continue;
			if (map[nx][ny] != 0)
				continue;
			if (flag) {
				map[nx][ny] = index;
				find(cnt + 1, nx, ny, index, map);
			} else
				return;
		}
	}
}
