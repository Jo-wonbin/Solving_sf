package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트 {

	static class Pair {
		int del, cal;

		public Pair(int del, int cal) {
			this.del = del;
			this.cal = cal;
		}
	}

	static ArrayList<Pair> al = new ArrayList<>();
	static boolean check[];
	static int size, calMax, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			size = Integer.parseInt(st.nextToken());
			calMax = Integer.parseInt(st.nextToken());

			check = new boolean[size];
			answer = 0;
			for (int i = 0; i < size; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				al.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			find(0, 0, 0, 0);

			sb.append("#").append(k).append(" ").append(answer).append("\n");
			al.clear();
		}
		System.out.println(sb);
		br.close();
	}

	static void find(int num, int cnt, int del, int cal) {

		if (cal > calMax) {
			return;
		}else {
			if (answer < del) {
				answer = del;
			}
		}
		if (cnt == size) {
			return;
		}

		int alsize = al.size();
		for (int i = num; i < alsize; i++) {
			if (check[i])
				continue;
			check[i] = true;
			Pair p = al.get(i);
			del += p.del;
			cal += p.cal;
			find(i + 1, cnt + 1, del, cal);
			del -= p.del;
			cal -= p.cal;
			check[i] = false;
		}
	}
}
