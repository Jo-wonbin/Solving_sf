package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1208_Flattern {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = 1;
		while (n <= 10) {
			String a = br.readLine();
			int dump = Integer.parseInt(a);

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int arr[] = new int[100];

			int cnt = 0;
			while (st.hasMoreTokens()) {
				arr[cnt++] = Integer.parseInt(st.nextToken());
			}

			while (dump-- > 0) {
				Arrays.sort(arr);
				//평탄화 완료되면 탈출
				if (arr[arr.length-1] - arr[0] == 0)
					break;
				arr[arr.length-1] -= 1;
				arr[0] += 1;
			}
			
			//덤프 값 다 쓰고 나온 경우 정렬이 안되어서 정렬함
			Arrays.sort(arr);
			System.out.printf("#%d %d\n", n, arr[arr.length-1] - arr[0]);
			n++;
		}
		br.close();
	}

}
