
import java.util.*;
//Seg tree for RMQ min/max value;


class  RMQSegTree{
    int[] tree;
    int[] input;
    
    // min query -> return max, max query -> return min;
    final static int INVALID = Integer.MAX_VALUE;
    final static int START = Integer.MAX_VALUE;

    //initial values
    public RMQSegTree(int[] input){
        int n = input.length;
        this.tree = new int[n*4];
        this.input = input;
        build(0,n-1,0);
    }

    public RMQSegTree(int n){
        this.tree = new int[n*4];
        this.input = new int[n];
        Arrays.fill(input,START);
        Arrays.fill(tree,START);

    }


    private void build(int low, int high, int pos){
        if (low==high){
            tree[pos] = input[low];
            return;
        }
        int mid = (high+low)/2;
        build(low,mid,2*pos+1);
        build(mid+1,high,2*pos+2);
        
        // Max or Min depending on use case
        tree[pos] = Math.min(tree[2*pos+1],tree[2*pos+2]);
    }

    public int query(int qlow, int qhigh){
        return q(qlow,qhigh,0,input.length-1,0);
    }

    private int q(int qlow, int qhigh, int low , int high, int pos){
        //complte overlap
        if (qlow<= low && qhigh>=high) return tree[pos];
        if (qlow > high || qhigh< low) return INVALID;
        int mid = low+(low+high)/2;
        // Again min or max
        return Math.min(
            q(qlow,qhigh,low,mid,2*pos+1),
            q(qlow,qhigh,mid+1,high,2*pos+2)
        );
        
    }
    private void update(int idx, int val){   // update index of input, new val
        updateUtil(0,0,input.length-1,idx,val);
    }

    private void updateUtil(int pos, int low, int high, int idx, int val){
        if (low==high) tree[pos]=val;
        else{
            int mid = low+(low+high)/2;
            if (idx<=mid) {
                updateUtil(pos*2+1,low,mid,idx,val);
            } else {
                updateUtil(pos*2+2,mid+1,high,idx,val);
            }
        }
        //Min or max
        tree[pos] = Math.min(tree[pos*2+1],tree[pos*2+2]);
    }
}
