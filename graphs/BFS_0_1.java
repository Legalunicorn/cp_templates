
// 0-1 Bfs is O(|E|)
public class BFS_0_1 {

    public BFS_0_1(int n,List<List<int[]>> g){
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        //0-> weight, 1-> node
        Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        int start = 0;
        pq.add(new int[]{0,start});
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int u = top[1];
            for (int[] e: g.get(u)){
                int w = e[0];
                int v = e[1];

                if (dist[v]+w<dist[u]){
                    dist[v] = w+dist[u];
                    pq.add(new int[]{dist[v],v});
                }

            }
        }

    }

    
    
}


