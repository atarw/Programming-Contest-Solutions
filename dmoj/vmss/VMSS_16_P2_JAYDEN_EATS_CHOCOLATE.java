import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VMSS_16_P2_JAYDEN_EATS_CHOCOLATE {

	static int X, Y, Z;
	static int[] cache;

	public static int number (int N) {
		int n = N - 1;

		if (N > 0 && cache[n] == 0) {
			if (N >= X) {
				cache[n] = Math.max (cache[n], 1 + number (N - X));
			}

			if (N >= Y && X != Y) {
				cache[n] = Math.max (cache[n], 1 + number (N - Y));
			}

			if (N >= Z && X != Z && Y != Z) {
				cache[n] = Math.max (cache[n], 1 + number (N - Z));
			}

			if (cache[n] == 0) {
				cache[n] = -1;
			}
		}

		return N <= 0 ? 0 : cache[n];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t = in.readLine ().split (" ");

		X = Integer.parseInt (t[0]);
		Y = Integer.parseInt (t[1]);
		Z = Integer.parseInt (t[2]);
		cache = new int[N];

		System.out.print (number (N));
	}
}