package Practice;

import java.util.Scanner;

public class dp1 {

	public static void main(String[] args) {
		long[] top = new long[100];
		
		top[1] = 2;
		top[2] = 3;
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for(int i=3; i<=n; i++) {
			top[i] = top[i-1] + top[i-2];
		}
	}

}
