package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2605_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int arr[] = new int[N];
		if (N > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				;
			}
		}
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		if (N > 1) {
			int cnt = 2;
			for (int i = 1; i < N; i++) {
				if (arr[i] == 0) {
					list.add(cnt);
				} else {
					if (arr[i] >= list.size())
						list.add(0, cnt);
					else
						list.add(list.size() - arr[i], cnt);
				}
				cnt++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int a : list) {
			sb.append(a).append(" ");
		}
		sb.setLength(sb.length() - 1);
		if (N == 0)
			System.out.println(0);
		else
			System.out.println(sb);
		br.close();
	}

}
