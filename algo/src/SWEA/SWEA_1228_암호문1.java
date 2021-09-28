package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_1228_암호문1 {
	static LinkedList<Integer> al = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int cnt = 1;
		while (cnt <= 10) {
			int size = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < size; i++) {
				al.add(Integer.parseInt(st.nextToken()));
			}
			int n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine(), " ");
			while (n-- > 0) {
				String I = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());

				for (int i = num; i < num + count; i++) {
					al.add(i, Integer.parseInt(st.nextToken()));
				}
			}

			sb.append("#").append(cnt).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(al.poll()).append(" ");
			}
			sb.append("\n");
			cnt++;
			al.clear();
		}

		System.out.println(sb.toString());
		br.close();
	}

}
