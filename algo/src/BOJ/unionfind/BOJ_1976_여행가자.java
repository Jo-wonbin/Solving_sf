package BOJ.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자 {

	static int V;
	static int parents[];

	private static void make() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		int[][] map = new int[V + 1][V + 1];

		make();

		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= V; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					union(i, j);
				}
			}
		}

		int road[] = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}

		String msg = "YES";
		for (int i = 0; i < N-1; i++) {
			if(find(road[i]) != find(road[i+1])) {
				msg = "NO";
				break;
			}
		}
		System.out.println(msg);
		br.close();
	}

}
