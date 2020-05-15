package com.anzhelika;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Help help = new Help();
        int sizeOfList = 20_000_000;
        int maxLongWords = 20;

        long start = System.currentTimeMillis();
        List<String> list = help.getList(sizeOfList, maxLongWords);
        long end = System.currentTimeMillis();
        long time=end - start;


        long m1 = measureTimeFor_i(list);
        long m2 = measureTimeFor_each(list);
        long m3 = measureTimeStream(list);
        long m4 = measureTimeParallelStream(list);

//        System.out.println(m1);
//        System.out.println(m2);
//        System.out.println(m3);
//        System.out.println(m4);

        // save results in the database
        String sizeOfListS=String.format("%,d", sizeOfList);
        try {
       help.save(m1, m2, m3, m4, sizeOfListS ,time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static long measureTimeFor_i(List<String> list) {
        long start = System.currentTimeMillis();
        int size = list.size();
        long count = 0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).length() < 7) {
                ++count;
            }
        }
        System.out.println("Result of \"measureTimeFor_i\" - " + count);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long measureTimeFor_each(List<String> list) {
        long start = System.currentTimeMillis();
        long count = 0;
        for (String s : list) {
            if (s.length() < 7) {
                ++count;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Result of \"measureTimeFor_each\" - " + count);
        return end - start;
    }

    private static long measureTimeStream(List<String> list) {
        long start = System.currentTimeMillis();
        long count = list.stream().filter(w -> w.length() < 7).count();
        long end = System.currentTimeMillis();
        System.out.println("Result of \"measureTimeStream\" - " + count);
        return end - start;
    }

    private static long measureTimeParallelStream(List<String> list) {
        long start = System.currentTimeMillis();
        long count = list.parallelStream().filter(w -> w.length() < 7).count();
        long end = System.currentTimeMillis();
        System.out.println("Result of \"measureTimeParallelStream\" - " + count);
        return end - start;
    }
}