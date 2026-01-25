package Alogtirhm.Practice;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TreeSet is a collection class that stores unique element in a sorted order.
 * TreeSet is not synchronized,it synchronized using Collections.synchronizedSet().
 * TreeSet implements NavigableSet, which extends SortedSet, which extends Set.
 */
public class Parctice {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Test 1");
        treeSet.add("Test 2");
        treeSet.add("Test 3");
        System.out.println(treeSet);

        // Contains
        System.out.println(treeSet.contains("Test 3"));

        // Get first of element
        System.out.println("Get first of tree set element: " + treeSet.first());

        // Get last of element
        System.out.println("Get last of tree set element: " + treeSet.last());

        // Remove first of element use pollFirst()
        treeSet.pollFirst();

        // Remove first of element use pollLast()
        treeSet.pollLast();

        // Remove specific element
        treeSet.remove("Test 2");
        System.out.println(treeSet);


        useComparator();
    }

    // Use "Comparator" to compare value -> very important
    private static void useComparator() {
        // Creating compare rules
        TreeSet<Student> students = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                if (a.score != b.score) {
                    return b.score - a.score;
                }
                return a.name.compareTo(b.name); // Second sequence -> use name to sorted
            }
        }
        );

        students.add(new Student("Jenny", 100));
        students.add(new Student("Emma", 80));
        students.add(new Student("Jim", 1000));

        for (Student student : students) {
            System.out.println(student.name + " : " + student.score);
        }

    }
}
