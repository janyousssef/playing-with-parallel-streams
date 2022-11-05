package jan;


import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Long.min;

public class Main {
    public static int[] getRandomNumbers(int size) {
        return ThreadLocalRandom.current().ints(size).toArray();
    }

    public static long parallelSum(int[] arr) {

        return Arrays.stream(arr).parallel().asLongStream().sum();
    }

    public static long serialSum(int[] arr) {

        return Arrays.stream(arr).asLongStream().sum();
    }


    public static void main(String[] args) {
        int size = 10_000_000; //10 million
        int[] numbers = getRandomNumbers(size);

        testParallelSum(numbers);
        testSerialSum(numbers);

    }

    private static void testSerialSum(int[] randomNumbers) {
        long sum = 0, min = Long.MAX_VALUE;
        long timeAtFunctionStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            sum = serialSum(randomNumbers);
            long end = System.nanoTime();
            min = min(min, end - start);
        }
        System.out.printf("Total time using serial sum: %d %s %n", System.nanoTime() - timeAtFunctionStart, String.valueOf(System.nanoTime() - timeAtFunctionStart).length());
        System.out.println("min time = " + min);
        System.out.println("sum using serial = " + sum);
    }

    private static void testParallelSum(int[] randomNumbers) {
        long sum = 0, min = Long.MAX_VALUE;
        long timeAtFunctionStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            sum = parallelSum(randomNumbers);
            long end = System.nanoTime();
            min = min(min, end - start);
        }
        System.out.printf("Total time using parallel sum: %d %s %n", System.nanoTime() - timeAtFunctionStart, String.valueOf(System.nanoTime() - timeAtFunctionStart).length());
        System.out.println("min time = " + min);
        System.out.println("sum using parallel = " + sum);
    }

}




