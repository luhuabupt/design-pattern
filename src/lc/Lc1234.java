package lc;

public class Lc1234 {

    class Solution {
        public int balancedString(String s) {
            int n = s.length();
            int[] c = new int['Z' + 1];

            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)]++;
            }

            if (c['Q'] == n / 4 && c['W'] == n / 4 && c['E'] == n / 4 && c['R'] == n / 4) {
                return 0;
            }

            for (int i = 0; i < 4; i++) {
                if (c[i] > n / 4) {
                    c[i] -= n / 4;
                } else {
                    c[i] = 0;
                }
            }

            int ans = n;
            int[] t = new int['Z' + 1];
            int l = 0;
            for (int i = 0; i < n; i++) {
                t[s.charAt(i)]++;

                while (l <= i && chk(c, t)) {
                    ans = Math.min(ans, i - l + 1);
                    t[s.charAt(l)]--;
                    l++;
                }
            }

            return ans;
        }

        private boolean chk(int[] t, int[] c) {
            for (int j = 0; j <= 'Z'; j++) {
                if (t[j] < c[j]) {
                    return false;
                }
            }
            return true;
        }
    }

}
