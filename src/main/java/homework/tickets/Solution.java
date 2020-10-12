package homework.tickets;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    interface F {
        int apply(int day);
    }

    class BasicF implements F {
        boolean[] days = new boolean[400];
        int[] costs;
        F f;

        public BasicF(int[] days, int[] costs, F f) {
            this.costs = costs;
            for (int day : days) {
                this.days[day] = true;
            }
            this.f = f;
        }

        @Override
        public int apply(int day) {
            if (days[day]){
                int dayCost = costs[0] + f.apply(day + 1);
                int weekCost = costs[1] + f.apply(day + 7);
                int monthCost = costs[2] + f.apply(day + 30);
                return Math.min(dayCost, Math.min(weekCost, monthCost));
            } else {
                return f.apply(day + 1);
            }
        }
    }

    class LimitingF implements F {
        F f;

        public LimitingF(F f) {
            this.f = f;
        }

        @Override
        public int apply(int day) {
            if (day > 365)
                return 0;
            else
                return f.apply(day);
        }
    }

    class CachingF implements F {
        F f;
        Map<Integer, Integer> cache = new HashMap<>();

        public CachingF(F f) {
            this.f = f;
        }


        @Override
        public int apply(int day) {
            Integer cached = cache.get(day);
            if (cached == null) {
                cached = f.apply(day);
                cache.put(day, cached);
            }
            return cached;
        }
    }

    class DynamicProxyF implements F {
        F f = null;

        public void setF(F f) {
            this.f = f;
        }

        @Override
        public int apply(int day) {
            return f.apply(day);
        }
    }


    int solution(int[] days, int[] costs){
        DynamicProxyF proxy = new DynamicProxyF();
        F simple = new BasicF(days, costs, proxy);
        F limiting = new LimitingF(simple);
        F caching = new CachingF(limiting);
        proxy.setF(caching);

        return proxy.apply(1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.solution(new int[]{1,4,6,7,8,20}, new int[]{2,7,15});
        System.out.println(ans);
    }
}
