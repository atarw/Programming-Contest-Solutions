import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class VMSS_16_P3_JAYDEN_STUDIES_TREES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		Graph g = new Graph ();
		String[] t;

		for (int n = 1; n < N; n++) {
			t = in.readLine ().split (" ");
			g.addEdge (Integer.parseInt (t[0]), Integer.parseInt (t[1]));
		}

		int s = g.furthest (1, true);
		//System.out.println (s);
		System.out.print (g.furthest (s, false));
	}

	private static class Graph {

		static Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>> ();

		public int furthest (int S, boolean returnNode) {
			Queue<Integer> queue = new ArrayDeque<Integer> ();
			int[] cache = new int[map.size ()];

			int curr, node = S, max = Integer.MIN_VALUE;

			queue.offer (S);

			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				if (max < cache[curr - 1]) {
					max = cache[curr - 1];
					node = curr;
				}

				for (int i : map.get (curr)) {
					if (cache[i - 1] == 0) {
						cache[i - 1] = cache[curr - 1] + 1;
						queue.offer (i);
					}
				}
			}

			if (returnNode) return node;
			return max;
		}

		public void addEdge (int S, int E) {
			if (!map.containsKey (S)) {
				map.put (S, new HashSet<Integer> ());
			}

			if (!map.containsKey (E)) {
				map.put (E, new HashSet<Integer> ());
			}

			map.get (S).add (E);
			map.get (E).add (S);
		}
	}
}