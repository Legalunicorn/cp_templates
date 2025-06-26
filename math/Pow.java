class Pow {
    //binary expo 
    private long pow (long a, long b){
        long res = 1;
        while(b>0){
            if ((b&1)==1) res *= a;
            a = a * a;
            b >>= 1;

        }
        return res;
    }

    //With a MOD value
    private long pow (long a, long b, long mod){
        a %= mod;
        long res = 1;
        while (b>0){
            if ((b&1)==1) res = res * a % mod;
            a = a * a % mod; 
            b >>= 1;
        }
        return res;
    }
}
