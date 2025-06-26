
import java.util.*;
//Sum or swap with multiplication works fine
class SumSegTree{
    int[] tree;
    int[] input;
    // sum: 0, multiplication: 1;
    final static int INVALID = 0;
    final static int START = 0; //same valid as invalid but for semantics diff naming

    public SumSegTree(int[] input){ //input initial values 
        int n = input.length;
        this.tree = new int[n*4];
        this.input = input;
        build(0,n-1,0);
    }

    public SumSegTree(int n){ //No initial value but there is a size
        this.input = new int[n];
        Arrays.fill(input,START);
        this.tree = new int[n*4];
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
        
        // + or *
        tree[pos] = tree[2*pos+1] +  tree[2*pos+2];
    }

    public int query(int qlow, int qhigh){
        return q(qlow,qhigh,0,input.length-1,0);
    }

    private int q(int qlow, int qhigh, int low , int high, int pos){
        if (qlow<= low && qhigh>=high) return tree[pos];
        //0 for sum, 1 for multiplication 
        if (qlow > high || qhigh< low) return INVALID;
        int mid = low+(low+high)/2;
        // + or *
        return (
            q(qlow,qhigh,low,mid,2*pos+1) + 
            q(qlow,qhigh,mid+1,high,2*pos+2)
        );

        
    }
    // update index of input, new val
    private void update(int idx, int val){
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
        //+ or *
        tree[pos] = tree[pos*2+1]  +  tree[pos*2+2];
    }
}
