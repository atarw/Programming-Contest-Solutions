import java.io.*;
import java.util.*;

public class ERRATIC_ANTS {

	public static int best (Map <Pair, List <Pair>> map, Pair end) {
		Map <Pair, Integer> dp = new HashMap <Pair, Integer> ();

		for (Pair p : map.keySet ())
			dp.put (p, 1 << 25);

		Pair cur = new Pair (0, 0);
		Queue <Pair> q = new ArrayDeque <Pair> ();
		q.offer (cur);
		dp.put (cur, 0);

		while (!q.isEmpty ()) {
			cur = q.poll ();

			for (Pair nxt : map.get (cur)) {
				if (dp.get (nxt) > dp.get (cur) + 1) {
					dp.put (nxt, dp.get (cur) + 1);
					q.offer (nxt);
				}
			}
		}

		return dp.get (end);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();

		for (int n = 0; n < N; ++n) {
			int S = in.iscan ();
			Map <Pair, List <Pair>> map = new HashMap <Pair, List <Pair>> ();
			int cx = 0, cy = 0;
			Pair cur = new Pair (cx, cy), nxt = cur;
			map.put (cur, new ArrayList <Pair> ());

			for (int s = 0; s < S; ++s) {
				char c = in.sscan ().charAt (0);

				if (c == 'N') {
					++cy;
				}
				else if (c == 'S') {
					--cy;
				}
				else if (c == 'W') {
					--cx;
				}
				else if (c == 'E') {
					++cx;
				}
				else {
					throw new RuntimeException ();
				}

				nxt = new Pair (cx, cy);
				map.get (cur).add (nxt);

				if (!map.containsKey (nxt)) {
					map.put (nxt, new ArrayList <Pair> ());
				}

				map.get (nxt).add (cur);
				cur = nxt;
			}

			out.println (best (map, nxt));
		}

		out.close ();
	}

	private static class Pair {
		int x, y;

		@Override
		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass () != o.getClass ()) return false;

			Pair pair = (Pair) o;

			if (x != pair.x) return false;
			return y == pair.y;
		}

		@Override
		public int hashCode () {
			return this.x * 31 + this.y * 19;
		}

		public Pair (int x, int y) {
			this.x = x; this.y = y;
		}
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public INPUT (String file) throws IOException {
			this.stream = new FileInputStream (file);
		}

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			int res = 0;

			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public String sscan () throws IOException {
			int c = cscan ();
			while (space (c)) c = cscan ();

			StringBuilder res = new StringBuilder ();

			do {
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));

			return res.toString ();
		}

		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			long res = 0;

			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();

			}
			while (!space (c));

			return res * sgn;
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}

	public static class UTILITIES {

		static final double EPS = 10e-6;

		public static int lower_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int upper_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] > x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int gcd (int a, int b) {
			return b == 0 ? a : gcd (b, a % b);
		}

		public static int lcm (int a, int b) {
			return a * b / gcd (a, b);
		}

		public static int fast_pow_mod (int b, int x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;

			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
		}

		public static long choose (long n, long k) {
			k = Math.min (k, n - k);
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);

			return val;
		}

		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = (val * (n - i));

			return val;
		}
	}
}