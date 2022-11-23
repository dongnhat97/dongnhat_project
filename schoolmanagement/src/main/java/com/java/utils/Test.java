package com.java.utils;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        List<String> lines = Arrays.asList("a", "abc", "b");

        List<String> result = lines.stream()
                .filter(line -> "a".contains(line))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}