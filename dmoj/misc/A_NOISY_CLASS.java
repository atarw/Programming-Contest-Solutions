import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class A_NOISY_CLASS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ()), M = Integer.parseInt (in.readLine ());
		String[] t;
		Graph g = new Graph (N);

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			g.addEdge (Integer.parseInt (t[0]), Integer.parseInt (t[1]));
		}

		System.out.print (g.order () ? 'Y' : 'N');
	}

	private static class Graph {

		static boolean[][] matrix;
		static int[] indegree;

		public Graph (int N) {
			matrix = new boolean[N + 1][N + 1];
			indegree = new int[N];
		}

		public void addEdge (int S, int E) {
			if (!matrix[S][E]) {
				indegree[E - 1]++;
			}

			matrix[S][E] = true;
		}

		public boolean order () {
			for (int n = matrix.length - 1; n > 0; n--) {
				if (successor () == -1) {
					return false;
				}
			}

			return true;
		}

		public int successor () {
			for (int i = 0; i < indegree.length; i++) {
				if (indegree[i] == 0) {
					indegree[i]--;

					for (int x = 1; x < matrix[0].length; x++) {
						if (matrix[i + 1][x]) {
							matrix[i + 1][x] = false;
							indegree[x - 1]--;
						}
					}
					return i + 1;
				}
			}

			return -1;
		}
	}
}