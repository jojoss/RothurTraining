package com.example;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Integer> ids = List.of(1, 2, 3);
        Employee emp = new Employee("Jake", "Sun", "JakSun@example.com", 123, false, ids);
        Employee emp2 = new Employee("Jake", "Sun", "JakSun@example.com", 123, false, ids);

        Set<Employee> set = new HashSet<>();
        set.add(emp);
        set.add(emp2);

        String s = "A";
        //String s1 = String.valueOf(1);
        String s1 = new String("A");
        String s2 = "A";

        Integer i1 =  Integer.valueOf(1);
        Integer i2 = new Integer(1);
        Integer i3 = new Integer(1);

        // Integer -> static group number -> 0 - 255

        System.out.println(i1 == i2);

        System.out.println(s == s1);
        System.out.println(s == s2);

        // arr vs list
        



        System.out.println(set.size());
    }
}