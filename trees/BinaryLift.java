

//LCA using binary lifting 
//Distance (using depth array and LCA
public class BinaryLift {
    int LOG = 0;
    int n;
    int[] depth;
    int[][] up;

    //only require the parent table 
    // depth is not needed, but if provided is fine as well
    // too lazy to create another method for it 
    public BinaryLift(int[] parent){
        this.n = parent.length;
        this.depth = new int[n];
        constructTable(parent);
    }

    private int lca(int a, int b){
        if (depth[a]<depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }
        int k = depth[a] - depth[b];
        for (int j=LOG-1;j>=0;j--){
            if ((k<<j&1)==1){
                a = up[a][j];
            }
        }
        if (b==a) return a;
        for (int j=LOG-1;j>=0;j--){
            if (up[a][j]!=up[b][j]){
                a = up[a][j];
                b = up[b][j];
            }
        }
        return up[a][0];
    }

    private void constructTable(int[] parent){
        while ((1<<LOG)<=n) LOG++;
        up = new int[n][LOG];

        for (int v=0;v<LOG;v++){
            up[v][0] = parent[v];
            //If depth is not yet constructed
            if (v!=0) depth[v] = depth[parent[v]]+1;
        }

        for (int j=1;j<LOG;j++){
            for (int v=0;v<n;v++){
                up[v][j] = up[up[v][j-1]][j-1];
            }
        }

    }

    private int dist(int a, int b){
        return depth[a]+depth[b] - (2*lca(a,b));
    }
    
}
