public class Gcd {

    public int gcd(int a, int b){
        if (b==0) return a;
        return gcd(b, a%b) ;
    }
    

    // private int gcd(int a, int b){
    //     if (a<b){
    //         int temp  = a;
    //         a = b;
    //         b = temp;
    //     }
    //     return gcdUtil(a,b);
    // }
    // private int gcdUtil(int a, int b){
    //     if (b==0) return a;
    //     return gcdUtil(b, a % b);
    // }
}
