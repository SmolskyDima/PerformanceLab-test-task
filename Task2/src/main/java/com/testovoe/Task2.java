package com.testovoe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class Task2 {

    private static final int MIN_POINTS = 1;
    private static final int MAX_POINTS = 100;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Input 2 arguments: java Task2 <circle_file> <points_file>");
            System.exit(1);
        }

        BigDecimal centerX = BigDecimal.ZERO;
        BigDecimal centerY = BigDecimal.ZERO;
        BigDecimal radius = BigDecimal.ZERO;

        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]))) {
            String[] centerCoordinates = circleReader.readLine().split("\\s+");
            centerX = new BigDecimal(centerCoordinates[0]);
            centerY = new BigDecimal(centerCoordinates[1]);
            radius = new BigDecimal(circleReader.readLine());

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error reading circle data: " + e.getMessage());
            System.exit(1);
        }

        try (BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {
            int pointsCount = 0;
            String line;
            while ((line = pointsReader.readLine()) != null) {
                pointsCount++;

                if (pointsCount > MAX_POINTS) {
                    throw new IllegalArgumentException("Number of points must be between 1 and 100");
                }
                String[] coordinates = line.split("\\s+");
                BigDecimal x = new BigDecimal(coordinates[0]);
                BigDecimal y = new BigDecimal(coordinates[1]);

                int position = getPosition(centerX, centerY, radius, x, y);
                System.out.println(position);
            }

            if (pointsCount < MIN_POINTS) {
                throw new IllegalArgumentException("Number of points is less than minimum limit");
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error reading points data: " + e.getMessage());
        }
    }

    private static int getPosition(BigDecimal centerX, BigDecimal centerY,
                                   BigDecimal radius, BigDecimal x, BigDecimal y) {
        BigDecimal distanceSquared = x.subtract(centerX).pow(2).add(y.subtract(centerY).pow(2));
        BigDecimal radiusSquared = radius.pow(2);

        int result = distanceSquared.compareTo(radiusSquared);
        if (result < 0) {
            return 1;
        } else if (result > 0) {
            return 2;
        } else {
            return 0;
        }
    }
}