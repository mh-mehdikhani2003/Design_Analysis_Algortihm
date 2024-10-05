
import java.util.*;

public class maajun {
    static int max_ref;

    static int _lis(int arr[], int n) {
        if (n == 1)
            return 1;

        int res, max_ending_here = 1;

        for (int i = 1; i < n; i++) {
            res = _lis(arr, i);
            if (arr[i - 1] < arr[n - 1]
                    && res + 1 > max_ending_here)
                max_ending_here = res + 1;
        }
        if (max_ref < max_ending_here)
            max_ref = max_ending_here;
        return max_ending_here;
    }

    static int lis(int arr[], int n) {
        max_ref = 1;
        _lis(arr, n);
        return max_ref;
    }
    static int LCS(int[] firstArr,int[] secondArr) {
        int LenNow = 0;
        int ans =0;
        int[] temp = new int[secondArr.length];
        for (int i = 0; i < firstArr.length; i++) {
            LenNow  = 0;
            for (int j = 0; j < secondArr.length; j++) {
                if(firstArr[i]>secondArr[j]){
                    LenNow  = Math.max(LenNow ,temp[j]);
                }else if(firstArr[i]==secondArr[j]){
                    temp[j] = LenNow  +1;
                    ans = Math.max(temp[j],ans);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
       int[] first = new int[n];

        for (int i = 0; i < n; i++) {
            first[i] = scanner.nextInt();
        }
        int  m= scanner.nextInt();
        int[] second = new int[m];
        for (int i = 0; i < m; i++) {
            second[i] = scanner.nextInt();
        }


        System.out.println(LCS(first,second));

    }
}
