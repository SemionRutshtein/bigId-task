package il.bigid.interviewtest.processing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static il.bigid.interviewtest.constants.Configuration.*;
import static il.bigid.interviewtest.constants.Configuration.LINES_BATCH;

public class ProcessingService {
    private static List<Thread> threads = new ArrayList<>();
    private static List<String> strings = new ArrayList<>();

    static {
        try {
            strings = Files.readAllLines(Path.of(PATH));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    static int totalSize = strings.size();
    static int numberOfParts = totalSize / LINES_BATCH;

    public static void execute(){
        IntStream
                .range(0, numberOfParts)
                .mapToObj(i -> new Thread(new Worker(i)))
                .forEach(thread -> {
            threads.add(thread);
            thread.start();
        });
    }
    public static void stop () {
        threads
                .stream()
                .forEach(thread -> {
            try {
                thread.join();
                System.out.println("Thread:" + Thread.currentThread().getName());
                System.out.println("Thread:" + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
