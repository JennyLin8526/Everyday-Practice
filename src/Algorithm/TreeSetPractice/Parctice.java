package Algorithm.TreeSetPractice;

import java.util.Comparator;
import java.util.TreeSet;

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


//        useComparator();
//
//        useCelling();
//
//        useFloor();

        useClone();
    }

    // Use "Comparator" to compare value -> very important
    private static void useComparator() {
        // Creating compare rules
        TreeSet<Student> students = new TreeSet<>(
                Comparator
                        .comparingInt(Student::getScore).reversed() // comparingInt : small -> large , comparingInt().reversed() : large -> small
                        .thenComparing(Student::getName)
        );

        students.add(new Student("Jenny", 100));
        students.add(new Student("Emma", 80));
        students.add(new Student("Jim", 1000));

        for (Student student : students) {
            System.out.println(student.name + " : " + student.score);
        }
    }

    //Get the closest upper target value
    private static void useCelling() {
        TreeSet<Integer> treeSets = new TreeSet<>();

        treeSets.add(10);
        treeSets.add(20);
        treeSets.add(30);

        System.out.println(treeSets.ceiling(40));

    }

    //Get the closest floor target value
    private static void useFloor() {
        TreeSet<Integer> treeSets = new TreeSet<>();

        treeSets.add(10);
        treeSets.add(20);
        treeSets.add(30);

        System.out.println(treeSets.floor(25));
    }

    // TreeSet clone is shallow copy(could not copy memory space,only copy value)
    private static void useClone() {
        TreeSet<Integer> treeSets = new TreeSet<>();

        treeSets.add(10);
        treeSets.add(20);
        treeSets.add(30);

        TreeSet<Integer> cloneSet = (TreeSet) treeSets.clone();

        treeSets.remove(30);

        for (Integer treeSet : treeSets) {
            System.out.println("Original : " + treeSet);
        }

        for (Integer treeSet : cloneSet) {
            System.out.println("Clone : " + treeSet);
        }
    }
}
