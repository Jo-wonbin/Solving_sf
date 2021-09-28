package BOJ.recursive;

import java.util.Scanner;

public class BOJ_17478_재귀함수가뭔가요 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		chat(N, 0);
		sc.close();
	}

	public static void chat(int n, int index) {
		underbar(index);
		System.out.println("\"재귀함수가 뭔가요?\"");

		if (n == 0) {
			underbar(index);
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			underbar(index);
			System.out.println("라고 답변하였지.");
			return;
		}

		underbar(index);
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		underbar(index);
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		underbar(index);
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		chat(n - 1, index + 1);
		underbar(index);
		System.out.println("라고 답변하였지.");

	}

	public static void underbar(int index) {
		for (int i = 0; i < index; i++) {
			System.out.print("____");
		}
	}
}
