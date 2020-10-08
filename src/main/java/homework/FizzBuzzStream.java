package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Ilia Moskalenko
 */
public class FizzBuzzStream {
    public static void main(String[] args) {
        FizzBuzzStream fizzBuzzStream = new FizzBuzzStream();
        System.out.println(fizzBuzzStream.fizzBuzz(15));

    }

    public List<String> fizzBuzz(int n){
       List<String> list = IntStream.rangeClosed(1,n)
                .mapToObj(x -> {
                    if (x % 3 == 0 && x % 5 == 0)  return  "FizzBuzz";
                    else if (x% 3 == 0)  return  "Fizz";
                    else if (x % 5 == 0)  return "Buzz";
                    else return (x+"");
                }).collect(Collectors.toList());

        return list;
    }
}
