package Algorithm.HashMapPractice;

import java.util.HashMap;
import java.util.Map;

public class Practice {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Jamie", 25);
        map.put("Peter", 30);
        map.put("Mark", 35);
        map.put("Admin", 99);

        // Iterate through the HashMap and print each key-value pair
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Age: " + entry.getValue());
        }

        // Clone
        HashMap<String, Integer> shallowCloneMap = (HashMap<String, Integer>) map.clone();

        // Remove element by using key
        map.put("Admin", 100);

        System.out.println("------------------");
        for (Map.Entry<String, Integer> entry : shallowCloneMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
