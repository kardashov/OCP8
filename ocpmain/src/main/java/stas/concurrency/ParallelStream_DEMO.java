package stas.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ParallelStream_DEMO {
    public int processRecord(int input) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {/*Handle interrupted exception*/}
        return input + 1;
    }

    public void processAllData(List<Integer> data) {
        data.stream().map(a -> processRecord(a)).count();
    }

    public void processAllDataParallel(List<Integer> data) {
        data.stream().parallel().map(a -> processRecord(a)).count();
    }

    public static void main(String[] args) {
        ParallelStream_DEMO calculator = new ParallelStream_DEMO();

        // Define the data
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 4000; i++)
            data.add(i);

        // Process the data
        long start = System.currentTimeMillis();
        calculator.processAllData(data);
        double time = (System.currentTimeMillis() - start) / 1000.0;

        // Report results
        System.out.println("\nTasks completed in: " + time + " seconds");

        // Process the data IN PARALLEL
        start = System.currentTimeMillis();
        calculator.processAllDataParallel(data);
        time = (System.currentTimeMillis() - start) / 1000.0;

        // Report parallel results
        System.out.println("\nTasks completed in: " + time + " seconds");

    }
}
