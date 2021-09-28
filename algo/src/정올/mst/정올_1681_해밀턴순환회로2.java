package 정올.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author THKim
 */
public class 정올_1681_해밀턴순환회로2 {

	static class Edge implements Comparable<Edge> {

		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
//			return this.weight - o.weight; // 간선의 부호가 모두 같을때
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static void make() {
		parents = new int[V + 1];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	// a가 속한 집합의 대표자 찾기
	private static int find(int a) {
		if (a == parents[a])
			return a; // 자신이 대표자.
		return parents[a] = find(parents[a]); // 자신이 속합 집합의 대표자를 자신의 부모로 : path compression
	}

	// 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false; // 이미 같은 집합으로 합치지 않음

		parents[bRoot] = aRoot;
		return true;
	}

	static int V;
	static ArrayList<Edge> edgeList = new ArrayList<>();;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());

		int D[][] = new int[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= V; j++) {
				int value = Integer.parseInt(st.nextToken());
				D[i][j] = value;
				if (i != j && i > 1 && j != 1) {
					edgeList.add(new Edge(i, j, value));
				}
			}
		}

		Collections.sort(edgeList); // 오름차순 정렬

		make(); // 모든 정점을 각각으로 집합으로 만들고 출발

		// 간선 하나씩 시도하며 트리 만들어 감.
		int cnt = 0, result = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			if (union(edgeList.get(i).start, edgeList.get(i).end)) {
				result += edgeList.get(i).weight;
				if(cnt == 0)
					
				if (++cnt == V - 1)
					break; // 신장트리 완성
			}
		}
		System.out.println(result);
	}
}
