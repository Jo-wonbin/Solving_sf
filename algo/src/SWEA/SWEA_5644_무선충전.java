package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {

	static class BC {
		int x, y, r, charge;

		public BC(int x, int y, int r, int charge) {
			super();
			this.x = x;
			this.y = y;
			this.r = r;
			this.charge = charge;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	//BC 정보 저장 배열
	static BC[] bc;
	static int result, size, num;
	//A, B 좌표 따라갈 q
	static Queue<Point> qA = new LinkedList<>();
	static Queue<Point> qB = new LinkedList<>();
	//방향 배열
	static int dx[] = { 0, -1, 0, 1, 0 };
	static int dy[] = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			size = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			//A와 B 이동 경로 저장 배열
			int a[] = new int[size];
			int b[] = new int[size];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < size; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < size; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			bc = new BC[num];
			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				bc[i] = new BC(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			//시작 좌표 넣기
			qA.add(new Point(1, 1));
			qB.add(new Point(10, 10));

			result = 0;
			find(a, b);
			sb.append("#").append(k).append(" ").append(result).append("\n");
			qA.clear();
			qB.clear();
		}
		System.out.println(sb);
		br.close();
	}

	static int check(Point A, Point B) {
		int checkA[] = new int[num];
		int checkB[] = new int[num];

		for (int i = 0; i < num; i++) {
			int DA = Math.abs(A.x - bc[i].x) + Math.abs(A.y - bc[i].y);
			int DB = Math.abs(B.x - bc[i].x) + Math.abs(B.y - bc[i].y);
			//범위에 있으면 충전값 넣어줌
			if (DA <= bc[i].r) {
				checkA[i] = bc[i].charge;
			}
			if (DB <= bc[i].r) {
				checkB[i] = bc[i].charge;
			}
		}
		
		int sum = 0;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				//둘다 0일 때 패스
				if (checkA[i] == 0 && checkB[j] == 0)
					continue;
				//한 쪽이 0이고 다른 하나 값을 가진 경우
				if (checkA[i] > 0 && checkB[j] == 0) {
					sum = Math.max(sum, checkA[i]);
				} 
				//한 쪽이 0이고 다른 하나 값을 가진 경우
				else if (checkA[i] == 0 && checkB[j] > 0) {
					sum = Math.max(sum, checkB[j]);
				} 
				//둘 다 값을 가진 경우
				else if (checkA[i] > 0 && checkB[j] > 0) {
					//다른 bc를 쓸 때
					if (i != j) {
						sum = Math.max(sum, checkA[i] + checkB[j]);
					} 
					//같은 bc를 쓸 때
					else {
						sum = Math.max(sum, (checkA[i] + checkB[j]) / 2);
					}
				}
			}
		}
		return sum;

	}

	static void find(int a[], int b[]) {
		int cnt = 0;
		while (cnt <= size) {
			Point A = qA.poll();
			Point B = qB.poll();

			result += check(A, B);

			//마지막에 넣어준 큐의 값을 처리해줌, cnt == size면 에러발생
			if (cnt == size)
				break;
			
			qA.add(new Point(A.x + dx[a[cnt]], A.y + dy[a[cnt]]));
			qB.add(new Point(B.x + dx[b[cnt]], B.y + dy[b[cnt]]));

			cnt++;
		}
	}
}
