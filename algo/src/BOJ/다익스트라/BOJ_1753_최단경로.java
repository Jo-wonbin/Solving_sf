package BOJ.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {

	static class Node implements Comparable<Node> {
		int end, weight;

		
		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static int V, M;
	static int distance[];
	static List<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		final int INFINITY = Integer.MAX_VALUE;

		distance = new int[V + 1];

		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());

			list[x].add(new Node(y, dis));
		}

		Arrays.fill(distance, INFINITY);
		find(start);

		for (int i = 1; i <= V; i++) {
			if (distance[i] != INFINITY)
				sb.append(distance[i]).append("\n");
			else
				sb.append("INF").append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	private static void find(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] v = new boolean[V + 1];
		pq.add(new Node(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int end = temp.end;

			if (v[end])
				continue;
			v[end] = true;

			for (Node node : list[end]) {
				if (distance[node.end] > distance[end] + node.weight) {
					distance[node.end] = distance[end] + node.weight;
					pq.add(new Node(node.end, distance[node.end]));
				}
			}
		}
	}
}
