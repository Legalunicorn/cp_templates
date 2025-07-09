

//
public class LogQuerySparseTable {

    int[][] st;
    int LOG = 0;
    int n;
    int[] logPow;

    public LogQuerySparseTable(int[] arr){
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
                //-----------
                //---note----
                //-----------
                st[i][j] = Math.min(a,b);
            }
            for (int i = n-(1<<j)+1;i>0 && i<n;i++){
                st[i][j] = st[i-1][j];
            }
        }
        
    }

    public int queryLog(int l, int r){
        int min =Integer.MAX_VALUE;
        for (int i=LOG;i>=0;i--){
            if ((1<<i)<+r-l+1){
                min = Math.min(min,st[i][l]);
                l += 1<<i;
            }
        }
        return min;
    }

    
}
