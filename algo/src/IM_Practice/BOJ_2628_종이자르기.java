package IM_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2628_종이자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int tc = Integer.parseInt(br.readLine());

		ArrayList<Integer> garo = new ArrayList<>();
		ArrayList<Integer> sero = new ArrayList<>();
		garo.add(0);
		sero.add(0);

		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (x == 0)
				garo.add(y);
			else
				sero.add(y);
		}
		int max = Integer.MIN_VALUE;
		garo.add(N);
		sero.add(M);
		Collections.sort(garo);
		Collections.sort(sero);

		for (int i = garo.size() - 1; i >= 1; i--) {
			int tempG = garo.get(i) - garo.get(i - 1);
			for (int j = sero.size() - 1; j >= 1; j--) {
				int tempS = sero.get(j) - sero.get(j - 1);
				max = Math.max(tempG * tempS, max);
			}
		}
		System.out.println(max);
		br.close();
	}

}
