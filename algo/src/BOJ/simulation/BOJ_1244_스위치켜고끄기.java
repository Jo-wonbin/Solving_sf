package BOJ.simulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1244_스위치켜고끄기 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int switchNum = Integer.parseInt(br.readLine());
		int num[] = new int[switchNum + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= switchNum; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int studentNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int idx = 1;
			// 남자
			if (sx == 1) {
				int cnt = 2;
				int sum = n;
				while (sum <= switchNum) {
					if (num[sum] == 0) {
						num[sum] = 1;
					} else {
						num[sum] = 0;
					}
					sum = n * cnt;
					cnt++;
				}
			}
			// 여자
			else {
				if (num[n] == 0) {
					num[n] = 1;
				} else {
					num[n] = 0;
				}
				while (true) {
					if (n + idx > switchNum || n - idx < 1) {
						idx = 1;
						break;
					}
					if (num[n + idx] != num[n - idx]) {
						idx = 1;
						break;
					} else {
						if (num[n + idx] == 0) {
							num[n + idx] = 1;
							num[n - idx] = 1;
						} else {
							num[n + idx] = 0;
							num[n - idx] = 0;
						}
						idx++;
					}
				}
			}
		}
		for (int i = 1; i <= switchNum; i++) {
			if (i % 20 == 1 && i > 20) {
				System.out.println();
			}
			System.out.printf("%d ", num[i]);
		}
		br.close();
	}

}
