package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ilia Moskalenko
 */
public class FizzBuzz {
    public static void main(String[] args) {
        FizzBuzzer fizzBuzzer = n -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }

            List<String> changedList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % 3 == 0 && list.get(i) % 5 == 0) changedList.add("FizzBuzz");
                else if (list.get(i) % 3 == 0) changedList.add("Fizz");
                else if (list.get(i) % 5 == 0) changedList.add("Buzz");
                else changedList.add(list.get(i) + "");
            }
            return changedList;
        };

        System.out.println(fizzBuzzer.fizzBuzz(15));

    }
}
