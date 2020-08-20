package com.martimlima.javaprojects.data_structures.arrays.closest_neighbours;

class ClosestNeighbours {

    public static void main(String[] args) {

        int[] myArray = {0, 5, 1209, 6, 2, 111, 112, 33};
        int[] result = findClosest(myArray);

        // print the elements of the resulting array
        System.out.println("The closest neighbours are: " + result[0] + "," + result[1]);

    }

    private static int[] findClosest(int[] numbers) {

        int difference;
        int minDifference = Math.abs((numbers[0] - numbers[1]));
        int[] neighbours = new int[2];

        // hint: remember Math.abs();
        for(int i = 0; i < numbers.length - 1; i++) {

            difference = Math.abs((numbers[i] - numbers[i + 1]));

            if(difference < minDifference) {
                minDifference = difference;
                neighbours[0] = numbers[i];
                neighbours[1] = numbers[i + 1];
            }
        }

        return neighbours;
    }
}
