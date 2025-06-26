public class Binom {
    
    // nCr
    private int binom(int n, int k){
        int res = 1 ;
        for (int i=1;i<=k;i++){
            res = res * (n-i+1)/i;
        }
        return res;

    }
}
