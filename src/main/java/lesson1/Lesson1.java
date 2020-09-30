package lesson1;

import java.util.*;

public class Lesson1 {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

//        arrayList.get(1); // O(1)
//        arrayList.add(1); // O(1) O(n) ?
//        arrayList.size(); // O(1)
//        arrayList.remove(0); // O(n)
//
//        linkedList.get(1); // O(n)
//        linkedList.add(1); // O(1)
//        linkedList.size(); // O(1)
//        linkedList.remove(0); // O(1)
//        linkedList.remove(100); // O(n)

        Queue<Integer> queue = new LinkedList<>();
        LinkedList<Integer> linkedList1 = new LinkedList<>();

        queue.poll();

//        O(1)
//        O(ln n)
//        O(n)
//        O(n * ln n)
//        O(n^2)
//        O(n^a)
//        O(e^n)
//        O(n^n)

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        Map<Integer, String> map = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();

        Set<Integer> set = new HashSet<>();

        map.put(1, "1"); //O(1) ?
        map.get(1);      //O(1)
    }
}
