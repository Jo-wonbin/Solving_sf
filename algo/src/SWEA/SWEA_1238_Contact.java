package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact {

	static class Next {
		int num, count;

		public Next(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

	}

	static Queue<Next> q = new LinkedList<>();
	static int N, start, result, max;
	static ArrayList<Integer>[] al;
	static int chk[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int k = 1; k <= 10; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			al = new ArrayList[101];
			for (int i = 1; i < 101; i++) {

				al[i] = new ArrayList<Integer>();

			}
			chk = new int[101];
			Arrays.fill(chk, -1);
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				al[from].add(to);
			}

			chk[start] = 0;
			q.add(new Next(start, 0));

			bfs();

			result = Integer.MIN_VALUE;
			max = Integer.MIN_VALUE;
			for (int i = 1; i < 101; i++) {
				if (max == chk[i]) {
					result = Math.max(i, result);
				} else if (max < chk[i]) {
					result = i;
					max = chk[i];
				}
			}

			sb.append("#").append(k).append(" ").append(result).append("\n");
			q.clear();
		}

		System.out.println(sb);
		br.close();
	}

	static void bfs() {
		while (!q.isEmpty()) {

			Next temp = q.poll();

			for (int tp : al[temp.num]) {
				if (chk[tp] > 0)
					continue;
				chk[tp] = temp.count + 1;
				q.add(new Next(tp, temp.count + 1));
			}
		}
	}
}
