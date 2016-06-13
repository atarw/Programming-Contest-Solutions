import java.io.*;

public class CCC_14_VOTE_COUNT {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String votes = in.readLine ();
    
    int A = 0, B = 0;
    
    for (int i = 0; i < N; i++) {
      if (votes.charAt (i) == 'A') {
        A++;
      }
      else {
        B++;
      }
    }
    
    System.out.println (A > B ? "A" : A == B ? "Tie" : "B");
  }
}