package com.sim.management.entity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("aa", "bb", "cc", "AA");
//        List<String> nameLst = names.stream()
//                .map(String::toUpperCase)
//                .collect(Collectors.toList());
//        System.out.println(nameLst);
        Map<String, Long> namesCount = names.stream().filter(x -> Collections.frequency(names, x) > 1).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(namesCount);
        List<Integer> elements = Arrays.asList(1, 23456334, null, 4, 5, 8);

//        for (Integer value : elements) {
//            if (value != null) {
//                String s = Integer.toString(value);
//                if (s.length() >= 5)
//                    System.out.println(value);
//            }
//
//
//        }
        elements.stream().filter(s -> s != null && String.valueOf(s).length() >= 5).forEach(System.out::println);

    List<String> fruitslist=Arrays.asList("Apple","Banana","Mango","Orange","Grapes");
    fruitslist.stream().filter(fruits ->fruits.contains("an")&&fruits.length()>5)
            .map(fruit ->fruit.toUpperCase(Locale.ROOT)).forEach(System.out::println);
    }

}
