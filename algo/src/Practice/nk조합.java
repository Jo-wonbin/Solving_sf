package Practice;

public class nk조합 {

	static boolean chk[] = new boolean[3];
	static int count = 0;

	public static int nk(int n, int k) {

		if (k == 0 || n == k) {
			return 1;
		}
		return nk(n - 1, k - 1) + nk(n - 1, k);
	}

	public static void main(String[] args) {
//		for(int i=0; i<3; i++) {
//			System.out.print(chk[i] + " ");
//		}
		System.out.println(nk(5, 3));
	}

}
