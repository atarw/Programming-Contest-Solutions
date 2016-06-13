import java.io.*;
import java.util.*;

public class CCC_09_P4_SHOP_AND_SHIP {
  
  static int [] cache;
  static short [][] matrix;
  static short [] cities;
  static final long start = System.currentTimeMillis ();
  
  public static void addEdge (int S, int E, short W) {
    if (matrix [S][E] == 0 || W < matrix [S][E]) {
      matrix [S][E] = W;
      matrix [E][S] = W;
    }
  }
  
  public static int traverse (int D) {
    //System.out.println ("STARTING TRAVERSE METHOD: " + ((System.currentTimeMillis () - start) / 1000.0));
    Deque <Integer> queue = new ArrayDeque <Integer> ();
    boolean [] visited = new boolean [matrix.length];
    
    int curr, min = cities [D] == 0 ? Integer.MAX_VALUE : cities [D];
    queue.offer (D);
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      visited [curr] = true;
      
      if (cache [curr] > min) {
        continue;
      }
      
      for (int i = 0; i < matrix.length; i++) {
        if (D != i && matrix [curr][i] != 0 && (cache [i] == 0 || cache [i] > cache [curr] + matrix [curr][i])) {
          cache [i] = cache [curr] + matrix [curr][i];
          
          if (cities [i] != 0) {
            min = Math.min (min, cache [i] + cities [i]);
          }
          
          if (min > cache [i] && !visited [i]) {
            queue.offer (i);
            visited [i] = true;
          }
        }
      }
    }
    
    return min;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    //BufferedReader in = new BufferedReader (new FileReader ("s4.5.in"));
    int N = Integer.parseInt (in.readLine ());
    int T = Integer.parseInt (in.readLine ());
    
    String [] t;
    cache = new int [N];
    matrix = new short [N][N];
    cities = new short [N];
    
    while (T --> 0) {
      t = in.readLine ().split (" ");
      addEdge (Integer.parseInt (t [0]) - 1, Integer.parseInt (t [1]) - 1, Short.parseShort (t [2]));
    }
    
    //System.out.println ("FINISHED READING GRAPH: " + ((System.currentTimeMillis () - start) / 1000.0));
    
    int K = Integer.parseInt (in.readLine ());
    
    while (K --> 0) {
      t = in.readLine ().split (" ");
      cities [Integer.parseInt (t [0]) - 1] = Short.parseShort (t [1]);
    }
    
    System.out.println (traverse (Integer.parseInt (in.readLine ()) - 1));
    //System.out.println ("SOLVED: " + ((System.currentTimeMillis () - start) / 1000.0));
    in.close ();
  }
}