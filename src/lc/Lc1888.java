package lc;

public class Lc1888 {
    class Solution {
        public int minFlips(String s)  throws  RuntimeException{
            int n = s.length();
            int[] a1 = new int[n];
            int[] a0 = new int[n];
            int[] b0 = new int[n];
            int[] b1 = new int[n];

            int o = 1;
            int z = 0;
            int to = 0;
            int tz = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) - '0' != o) {
                    to++;
                }
                if (s.charAt(i) - '0' != z) {
                    tz++;
                }
                a0[i] = tz;
                a1[i] = to;
                o ^= 1;
                z ^= 1;
            }

            o = 1;
            z = 0;
            to = 0;
            tz = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) - '0' != o) {
                    to++;
                }
                if (s.charAt(i) - '0' != z) {
                    tz++;
                }
                b0[i] = tz;
                b1[i] = to;
                o ^= 1;
                z ^= 1;
            }

            int ans = n;
            for (int i = 0; i < n-1;i++) {
                ans = Math.min(ans, a0[i] + b1[i+1]);
                ans = Math.min(ans, a1[i] + b0[i+1]);
            }

            return ans;
        }
    }
}
