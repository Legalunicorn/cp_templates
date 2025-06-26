
import java.util.*;
class KMP {

    //KMP : search + LPS methods 

    private List<Integer> search(String text, String pattern){
        int n = text.length();
        int m = pattern.length();
        int[] lps = new int[m];
        List<Integer> res = new ArrayList<>();
        constructLps(pattern,lps);
        int i = 0, j = 0;
        while (i<n){
            if (text.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
                if (j==m){
                    res.add(j-m);
                    j = lps[j-1];
                }
            } else{
                if (j!=0) j = lps[j-1];
                else i++;
            }
        }
        return res;


    }

    private void constructLps(String pattern, int[] lps){
        int len = 0;
        int i = 1;
        while (i < pattern.length()){
            if (pattern.charAt(i)==pattern.charAt(len)){
                lps[i++] = ++len;
            } else{
                if (len!=0){
                    len = lps[len-1];
                } else lps[i++]=0;
            }
        }
    }
    
}
