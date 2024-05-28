package com.testovoe;

public class Task1 {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Input 2 arguments");
            return;
        }

        int n;
        int m;

        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("n and m must be integers");
            return;
        }

        if (n <= 0 || m <= 0) {
            System.out.println("n and m must be positive integers ");
        }

        if (m >= n) {
            System.out.println("m must be less than n");
        }

        StringBuilder path = new StringBuilder();
        int currentIndex = 0;
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        do {
            path.append(array[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n;
        } while (currentIndex != 0);

        System.out.println("Path: " + path);
    }
}
