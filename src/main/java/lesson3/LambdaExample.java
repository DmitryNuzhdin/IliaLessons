package lesson3;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaExample {
    public static void main(String[] args) {
        //Operator operator = (a, b) -> a + b;
        Operator operator = Integer::sum;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<String> transformed = list.stream()
                .filter(i -> i % 2 == 0)
                .limit(2)
                .map(integer -> "integer: " + integer)
                .flatMap(s -> list.stream().map(integer -> s + " -- " + integer))
                .limit(7)
                .collect(Collectors.toList());

        transformed.forEach(System.out::println);

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        map.forEach((key, value) -> System.out.println(key + " : " + value));
        map.keySet().stream()
                .map(key -> new Pair<String, String>(key, map.get(key)))
                .forEach(System.out::println);

       // System.out.println(operator.apply(3, 5));
    }

    static class Pair<F, S> {
        final F first;
        final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}
