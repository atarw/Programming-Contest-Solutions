import java.io.*;

public class P2 {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    int score1 = 0;
    int score2 = 0;
    
    String [] tokens = (in.readLine ()).split (" ");
    String [] tokens2 = (in.readLine ()).split (" ");
    
    for (int i = 0; i < N; i++) {
      int A = Integer.parseInt (tokens [i]);
      int  B = Integer.parseInt (tokens2 [i]);
      
      if (A > B) {
        score1++;
      }
      else if (A < B) {
        score2++;
      }
    }
    
    System.out.println (score1 + " " + score2);
    System.out.println (score1 > score2 ? "Xyene" : score1 < score2 ? "FatalEagle" : "Tie");
  }
}