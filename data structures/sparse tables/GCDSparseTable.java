//o(1) for idempotent properties
public class GCDSparseTable {

    int[][] st;
    int LOG = 0;
    int n;
    int[] logPow;

    public GCDSparseTable(int[] arr){
        n = arr.length;
        logPow = new int[n];

        for (int i=2;i<=n;i++) logPow[i] = logPow[i/2]+1;
        while((1<<LOG)<=n) LOG++;

        st = new int[n][LOG];

        for (int i=0;i<n;i++){
            st[i][0] = arr[i];
        }

        for (int j=1;j<n;j++){
            for (int i=0;i+(1<<j)-1<n;i++){
                int a = st[i][j-1];
                int b = st[i+(1<<(j-1))][j-1];
                st[i][j] = gcd(a,b);
            }
            for (int i = n-(1<<j)+1;i>0 && i<n;i++){
                st[i][j] = st[i-1][j];
            }
        }
    }

    public int query(int l,int r){
        int k = logPow[r-l+1];
        int a = st[l][k];
        int b = st[r-(1<<k)+1][k]; 
        return gcd(a,b);

    }
    private int gcd(int a, int b){
        if (b==0) return a;
        return gcd(b,a%b);
    }    
    
}
