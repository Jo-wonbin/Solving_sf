package BOJ.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스문제_arraylist {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		ArrayList<Integer> al = new ArrayList<>();
//		LinkedList<Integer> al = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			al.add(i);
		}
		sb.append("<");
		int temp = 0;
		while (!al.isEmpty()) {
			// 수열의 길이에서 현재 temp에서 k번째 위치에 있는 값 삭제
			// -1한 이유는 시작 인덱스가 0이기 때문
			temp = (temp + k - 1) % al.size();
			sb.append(al.get(temp)).append(", ");
			al.remove(temp);
		}
		sb.delete(sb.length() - 2, sb.length()).append(">");
		System.out.println(sb);
		br.close();
	}
}
