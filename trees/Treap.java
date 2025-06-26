
import java.util.ArrayList;
import java.util.List;


class TreapNode{
    int key, priority,count;
    TreapNode left, right;
}


//can sub this for PBDS, its a modified treap that only accepts unique values
class Treap{

    TreapNode root;
    public Treap(){
        root = null;
    }

    private TreapNode rightRotate(TreapNode x){
        TreapNode y = x.left;
        TreapNode z = y.right;
        y.right = x;
        x.left = z;
        updateCount(x);
        updateCount(y);
        return y;
    }
    private TreapNode leftRotate(TreapNode x){
        TreapNode y = x.right;
        TreapNode z = y.left;
        y.left = x;
        x.right = z;
        updateCount(x);
        updateCount(y);        
        return y;
    }

    private TreapNode newNode(int key){
        TreapNode temp = new TreapNode();
        temp.count = 1;
        temp.key = key;
        temp.priority = (int)(Math.random()*10e8);
        temp.left = temp.right = null;
        return temp;
    }

    public int size(){
        return count(this.root);
    }

    public List<Integer> nodes(){
        List<Integer> res = new ArrayList<>();
        inOrderUtil(root,res);
        return res;
    }

    private  void inOrderUtil(TreapNode node, List<Integer> vals){
        if (node==null) return;
        inOrderUtil(node.left, vals);
        vals.add(node.key);
        inOrderUtil(node.right, vals);
    }

    private int count(TreapNode t){
        return (t==null)?0: t.count;
    }
    private void updateCount(TreapNode t){
        if (t!=null) t.count = 1 + count(t.left) + count(t.right);
    }

    public void insert(int key){
        //Treap with no duplicates
        if (this.contains(key)) return; 
        this.root = insertUtil(root,key);
    }

    private TreapNode insertUtil(TreapNode root, int key){
        if (root == null) return newNode(key);
        if (key <= root.key){
            root.left = insertUtil(root.left,key);
            if (root.left.priority > root.priority){
                root = rightRotate(root);
            }
        } else{
            root.right = insertUtil(root.right,key);
            if (root.right.priority > root.priority){
                root = leftRotate(root);
            }
        }
        updateCount(root);
        return root;
    }

    public void delete (int key){
        this.root = deleteUtil(root,key);
    }

    //Not testeed yet
    private TreapNode deleteUtil(TreapNode root, int key){
        if (root == null) return root;
        if (key < root.key){
            root.left = deleteUtil(root.left,key);
        } else if (key > root.key){
            root.right = deleteUtil(root.right,key);
        } else if (root.left == null){
            TreapNode temp = root.right;
            root = temp;
        } else if (root.right == null){
            TreapNode temp = root.left ;
            root = temp;
        } else if (root.left.priority < root.right.priority){
            root = leftRotate(root);
            root.left = deleteUtil(root.left,key);
        } else {
            root = rightRotate(root);
            root.right = deleteUtil(root.right,key);
        }
        if (root!=null) updateCount(root);
        return root;

    }
    //this is 0 indexed
    public int findByOrder(int k){
        // if (k<1) k =1;
        // if (k>count(root)) k = count(root);

        //-1 or otherwise appropriate 
        if (k<0 || k>=count(root)) return -1; 
        return findByOrderUtil(root, k);

    }


    private int findByOrderUtil(TreapNode root, int k){
        //-1 or otherwise appropriate 
        if (root==null) return -1;
        int left = count(root.left);
        if (k==left){
            return root.key;
        } 
        else if (k<=left) return findByOrderUtil(root.left,k);
        else return findByOrderUtil(root.right, k-left-1);
    }

    public int orderOfKey(int key){
        return orderOfKeyUtil(root, key);
    }

    private int orderOfKeyUtil(TreapNode node, int key){
        if (node == null) return 0;
        if (key < node.key){
            return orderOfKeyUtil(node.left, key);
        } else if (key > node.key){
            return count(node.left) +1 + orderOfKeyUtil(node.right, key);
        } else return count(node.left);
    }

    public boolean contains(int key){
        TreapNode res = search(root,key);
        return res!=null;
    }

    private TreapNode search(TreapNode root, int key){
        if (root == null || root.key == key) return root;
        if (root.key < key) return search(root.right,key);
        return search(root.left,key);
    }
}