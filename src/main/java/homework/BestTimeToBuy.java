package homework;

import java.util.Arrays;

/**
 * @author Ilia Moskalenko
 */

/* Условия задачи

Say you have an array for which the ith element is the price of a given stock on day i.

        If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

        Note that you cannot sell a stock before you buy one.*/

public class BestTimeToBuy {
    public static void main(String[] args) {
        BestTimeToBuy bestTimeToBuy = new BestTimeToBuy();
        int[] prices = {7,1,5,3,6,4};
        int[] prices1 = {7,6,4,3,1};
        int[] prices2 = {2,4,1};
        System.out.println(bestTimeToBuy.maxProfit(prices));
        System.out.println(bestTimeToBuy.maxProfit(prices1));
        System.out.println(bestTimeToBuy.maxProfit(prices2));

    }

    public int maxProfit(int[] prices){

        int lowest = Integer.MAX_VALUE;
        int highestRange = 0;

        for (int price : prices) {
            if (price < lowest) {
                lowest = price;
            } else if (price - lowest > highestRange) {
                highestRange = price - lowest;
            }
        }
        return highestRange;

       /* int bestPrice = 0;
        for (int i =0; i < prices.length; i++){
            for(int j = i; j < prices.length; j++){
                if((prices[j] - prices[i]) > 0){
                    int price = prices[j] - prices[i];
                    if(price > bestPrice) bestPrice = price;
                }
            }
        }

        return bestPrice;*/

    }
}
