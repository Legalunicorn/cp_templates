public class  Main {
    public static void main(String[] args) {
        Treap treap = new Treap();
        treap.insert(5);
        treap.insert(2);
        treap.insert(8);
        treap.insert(1);
        treap.insert(4);
        
        System.out.println(treap.findByOrder(1));
        System.out.println(treap.orderOfKey(1)); // 0 (no elements less than 1)
    }
    
}
