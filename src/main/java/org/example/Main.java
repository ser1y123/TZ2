package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        in.close();
        try {
            List<Integer> list = readFile(fileName);
            System.out.println("MIN " + _min(list));
            System.out.println("MAX " + _max(list));
            System.out.println("SUM " + _sum(list));
            System.out.println("MULT " + _mult(list));
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            throw new RuntimeException(e);
        }
    }
    public static List<Integer> readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        List<Integer> list = new ArrayList<>();
        while (reader.hasNext()) list.add(reader.nextInt());
        reader.close();
        return list;
    }
    public static int _min(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        for (int i : list) if (i < min) min = i;
        return min;
    }

    public static int _max(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int i : list) if (i > max) max = i;
        return max;
    }

    public static long _sum(List<Integer> list) {
        long sum = 0;
        for (int i : list) sum += i;
        return sum;
    }

    public static long _mult(List<Integer> list) {
        long mult = 1;
        for (int i : list) mult *= i;
        return mult;
    }
}