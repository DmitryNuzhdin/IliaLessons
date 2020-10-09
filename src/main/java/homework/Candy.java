package homework;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Ilia Moskalenko
 */

/* Условия задачи

You have n candies, the ith candy is of type candies[i].

You want to distribute the candies equally between a sister and a brother so that each of them gets n / 2 candies (n is even). The sister loves to collect different types of candies, so you want to give her the maximum number of different types of candies.

Return the maximum number of different types of candies you can give to the sister.*/


public class Candy {
    public static void main(String[] args) {
        Candy candy = new Candy();
        int candies1[] = {1,1,2,2,3,3};
        int candies2[] = {1,1,2,3};
        int candies3[] = {1,11};
        int candies4[] = {1,1};

        System.out.println(candy.distributeCandies(candies1));
        System.out.println(candy.distributeCandies(candies2));
        System.out.println(candy.distributeCandies(candies3));
        System.out.println(candy.distributeCandies(candies4));

    }

    public int distributeCandies(int[] candies){
        int result;
        int countDifferentCandies = (int) Arrays.stream(candies).distinct().count();
        if (candies.length/2 == countDifferentCandies ) result = countDifferentCandies;
        else if (countDifferentCandies > candies.length/2 ) result = candies.length/2;
        else result = countDifferentCandies;

        return result;
    }
}
