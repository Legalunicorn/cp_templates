import java.util.*;

public class PrimeSieve {

    private boolean[] sieve(int n){
        boolean[] p = new boolean[n+1];
        Arrays.fill(p,true);
        p[0] = p[1] = false;
        for (int i =2; (long)(i*i)<=n;i++){
            if (p[i]){
                for (int j = i*i; j<=n; j+=i){
                    p[i] = false;
                }
            }
        }
        return p;

    }
    
}
