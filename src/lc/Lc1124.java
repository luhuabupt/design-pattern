package lc;

import java.util.HashMap;
import java.util.Map;

public class Lc1124 {
    class Solution {
        public int longestWPI(int[] a) {
            Map<Integer, Integer> m = new HashMap<>();

            int t = 0;
            int ans = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] > 8) {
                    t++;
                } else {
                    t--;
                }
                if (t > 0) {
                    ans = Math.max(ans, i+1);
                } else {
                    int x = m.getOrDefault(t-1, 1000000);
                    ans = Math.max(ans, i-x);
                }
                m.putIfAbsent(t, i);
            }

            return ans;
        }
    }
}
