package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cnt = 10;
		while (cnt-- > 0) {
			int num = Integer.parseInt(br.readLine());

			Queue<Integer> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}

			Loop1: while (true) {
				int count = 1;
				for (int i = 0; i < 5; i++) {
					int temp = q.poll();
					if (temp - count <= 0) {
						q.add(0);
						break Loop1;
					}
					temp -= count;
					count++;
					q.add(temp);

				}
			}

			System.out.printf("#%d ", num);
			for (int i = 0; i < 8; i++) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}

		br.close();
	}

}
