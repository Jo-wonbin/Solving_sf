package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6808_인영이와규영이의카드게임 {

	static boolean chk[];
	static int temp[];
	static int gyu[];
	static int in[];
	static int win, lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		for (int k = 1; k <= tc; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			gyu = new int[9];
			//규영이 카드 순서 배열
			for (int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
			}
			
			//인영이 카드를 오름차순으로 저장
			in = new int[9];
			int cnt = 0;
			for (int i = 1; i <= 18; i++) {
				boolean flag = true;
				for (int j = 0; j < 9; j++) {
					if (i == gyu[j]) {
						flag = false;
						break;
					}
				}
				if (flag)
					in[cnt++] = i;
			}

			win = lose = 0;
			//인영이 카드 순서 배열
			temp = new int[9];
			//중복 체크 배열
			chk = new boolean[9];
			find(0);
			sb.append("#").append(k).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	static void find(int cnt) {
		if (cnt == 9) {
			int gp = 0;
			int ip = 0;
			for (int i = 0; i < 9; i++) {
				if (gyu[i] > temp[i]) {
					gp += gyu[i] + temp[i];
				} else {
					ip += gyu[i] + temp[i];
				}
			}
			if (gp > ip) {
				win++;
			} else if (ip > gp) {
				lose++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (chk[i])
				continue;
			chk[i] = true;
			temp[cnt] = in[i];
			find(cnt + 1);
			chk[i] = false;
		}
	}
}
