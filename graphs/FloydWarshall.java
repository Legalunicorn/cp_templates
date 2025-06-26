import java.util.*;

public class FloydWarshall {

    public long[][] getAllPaths(int n, int[][] edges){ //u,v, weight
        long[][] dist = new long[n][n];
        for (int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        for (int i=0;i<n;i++){
            dist[i][i] = 0;
        }

        for (int[] e: edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            dist[u][v] = w;
        }

        for (int k=0;k<n;k++){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    dist[i][j] = Math.min(dist[j][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }
        return dist;
    }
    
}
