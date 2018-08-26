import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_11_S2_MULTIPLE_CHOICE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		char[] student = new char[N];

		for (int i = 0; i < N; i++) {
			student[i] = in.readLine ().charAt (0);
		}

		int total = 0;

		for (int i = 0; i < N; i++) {
			if (in.readLine ().charAt (0) == student[i]) {
				total++;
			}
		}

		System.out.println (total);
	}
}