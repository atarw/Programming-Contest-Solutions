import java.io.*;
import java.util.*;

public class CCC_07_P4_WATERPARK {
  
  static List <Integer> [] list;
  static int [] cach�;
  
  public static int ways (int curr) {
    if (cach� [curr] == 0 && list [curr] != null) {
      for (int i = 0; i < list [curr].size (); i++) {
        cach� [curr] += ways (list [curr].get (i));
      }
    }
    
    return cach� [curr];
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ()), x;
    list = new ArrayList [N]; cach� = new int [N];
    
    while (true) {
      t = in.readLine ().split (" ");
      
      if (t [0].equals ("0")) {
        break;
      }
      
      x = Integer.parseInt (t [0]) - 1;
      
      if (list [x] == null) {
        list [x] = new ArrayList <Integer> ();
      }
      
      list [x].add (Integer.parseInt (t [1]) - 1);
    }
    
    cach� [N - 1] = 1;
    System.out.print (ways (0));
  }
}